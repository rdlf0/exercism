import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.IntFunction;

class TwoBucket {
    private final int b1Cap;
    private final int b2Cap;
    private final int desiredLiters;
    private final State forbiddenState;
    private final State startState;

    TwoBucket(
            final int bucketOneCap,
            final int bucketTwoCap,
            final int desiredLiters,
            final String startBucket) {
        this.b1Cap = bucketOneCap;
        this.b2Cap = bucketTwoCap;
        this.desiredLiters = desiredLiters;

        if (startBucket.equals("one")) {
            forbiddenState = new State(0, bucketTwoCap);
            startState = new State(bucketOneCap, 0);
        } else {
            forbiddenState = new State(bucketOneCap, 0);
            startState = new State(0, bucketTwoCap);
        }
    }

    Result getResult() {
        final Set<State> visited = new HashSet<>();
        visited.add(new State(0, 0));
        final Queue<State> queue = new ArrayDeque<>();
        queue.offer(startState);

        int moves = 1;
        while (!queue.isEmpty()) {
            final int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                final State current = queue.poll();
                if (current == null) {
                    break;
                }

                visited.add(current);

                final int b1Amount = current.bucketOneAmount();
                final int b2Amount = current.bucketTwoAmount();

                if (b1Amount == desiredLiters) {
                    return new Result(moves, "one", b2Amount);
                } else if (b2Amount == desiredLiters) {
                    return new Result(moves, "two", b1Amount);
                }

                // Fill in bucket one
                this.fillIn(b1Amount, b2Amount, b1Cap, State::new, visited, queue);
                // Fill in bucket two
                this.fillIn(b2Amount, b1Amount, b2Cap, (a, b) -> new State(b, a), visited, queue);
                // Empty bucket one
                this.empty(b1Amount, b2Amount, a -> new State(0, a), visited, queue);
                // Empty bucket two
                this.empty(b2Amount, b1Amount, a -> new State(a, 0), visited, queue);
            }
            moves++;
        }

        throw new UnreachableGoalException();
    }

    private void fillIn(
            final int bucketToFillInCurrentAmount,
            final int otherBucketAmount,
            final int bucketToFillInCap,
            final BiFunction<Integer, Integer, State> stateCreator,
            final Set<State> visited,
            final Queue<State> queue) {
        if (bucketToFillInCurrentAmount >= bucketToFillInCap) {
            return;
        }

        // Top up from outside
        final State fillIn = stateCreator.apply(bucketToFillInCap, otherBucketAmount);
        if (!visited.contains(fillIn) && !fillIn.equals(forbiddenState)) {
            queue.offer(fillIn);
        }

        // Pour from the other bucket
        if (otherBucketAmount <= 0) {
            return;
        }

        final int bucketToFillInAvailable = bucketToFillInCap - bucketToFillInCurrentAmount;
        final int bucketToFillInToTake = Math.min(bucketToFillInAvailable, otherBucketAmount);
        final int otherBucketRemain = Math.max(0, otherBucketAmount - bucketToFillInToTake);
        final int bucketToFillInTotal = bucketToFillInCurrentAmount + bucketToFillInToTake;
        final State bucketTakesFromOtherBucket =
                stateCreator.apply(bucketToFillInTotal, otherBucketRemain);

        if (!visited.contains(bucketTakesFromOtherBucket)
                && !bucketTakesFromOtherBucket.equals(forbiddenState)) {
            queue.offer(bucketTakesFromOtherBucket);
        }
    }

    private void empty(
            final int toEmptyAmount,
            final int otherAmount,
            final IntFunction<State> stateCreator,
            final Set<State> visited,
            final Queue<State> queue) {
        if (toEmptyAmount <= 0) {
            return;
        }

        final State emptyFirst = stateCreator.apply(otherAmount);
        if (!visited.contains(emptyFirst) && !emptyFirst.equals(forbiddenState)) {
            queue.offer(emptyFirst);
        }
    }

    private record State(int bucketOneAmount, int bucketTwoAmount) {}
}
