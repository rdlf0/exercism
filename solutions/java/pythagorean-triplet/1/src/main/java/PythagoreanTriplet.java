import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

class PythagoreanTriplet {
    private final int a;
    private final int b;
    private final int c;

    PythagoreanTriplet(final int a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof final PythagoreanTriplet that)) return false;
        return a == that.a && b == that.b && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PythagoreanTriplet.class.getSimpleName() + "[", "]")
                .add("a=" + a)
                .add("b=" + b)
                .add("c=" + c)
                .toString();
    }

    static class TripletListBuilder {
        private int sum = 0;
        private int maxFactor = Integer.MAX_VALUE;

        TripletListBuilder thatSumTo(final int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(final int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {
            final List<PythagoreanTriplet> triplets = new ArrayList<>();
            final int limit = Math.min(sum / 3, maxFactor);
            for (int a = 1; a <= limit; a++) {
                final int numerator = (sum * sum) - (2 * sum * a);
                final int denominator = 2 * (sum - a);

                if (numerator % denominator == 0) {
                    final int b = numerator / denominator;
                    final int c = sum - a - b;

                    if (b > a && b <= maxFactor && c <= maxFactor) {
                        triplets.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }

            return triplets;
        }
    }
}
