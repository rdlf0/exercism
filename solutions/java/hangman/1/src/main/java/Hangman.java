import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Hangman {
    private static final Part[] PARTS = Part.values();

    private final Game game = new Game();

    Observable<Output> play(final Observable<String> words, final Observable<String> letters) {
        return words.map(game::reset).mergeWith(letters.map(game::guess));
    }

    private static class Game {
        private final Set<String> guesses = new HashSet<>();
        private final Set<String> misses = new HashSet<>();
        private final List<Part> parts = new ArrayList<>();
        private String secret;
        private String discovered;
        private Status status;

        private Output reset(final String secret) {
            this.secret = secret;
            discovered = "_".repeat(secret.length());
            status = Status.PLAYING;
            guesses.clear();
            misses.clear();
            parts.clear();
            return this.toOutput();
        }

        private Output guess(final String letter) {
            if (guesses.contains(letter) || misses.contains(letter)) {
                throw new IllegalArgumentException(
                        "Letter %s was already played".formatted(letter));
            }

            boolean found = false;
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < secret.length(); i++) {
                if (letter.charAt(0) == secret.charAt(i)) {
                    sb.append(secret.charAt(i));
                    found = true;
                } else {
                    sb.append(discovered.charAt(i));
                }
            }
            discovered = sb.toString();

            if (found) {
                guesses.add(letter);
                if (!discovered.contains("_")) {
                    status = Status.WIN;
                }
            } else {
                misses.add(letter);
                parts.add(PARTS[parts.size()]);
                if (parts.size() == PARTS.length) {
                    status = Status.LOSS;
                }
            }

            return this.toOutput();
        }

        private Output toOutput() {
            return new Output(
                    secret,
                    discovered,
                    new HashSet<>(guesses),
                    new HashSet<>(misses),
                    new ArrayList<>(parts),
                    status);
        }
    }
}
