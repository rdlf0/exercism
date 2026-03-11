import java.util.stream.IntStream;

class ResistorColorDuo {
    int value(final String[] colors) {
        if (colors.length < 2) {
            throw new IllegalArgumentException();
        }

        return IntStream.range(0, 2)
                .map(i -> this.getColorValue(colors[i]) * ((i + 9) % 10 + 1))
                .sum();
    }

    private int getColorValue(final String color) {
        switch (color) {
            case "black":
                return 0;
            case "brown":
                return 1;
            case "red":
                return 2;
            case "orange":
                return 3;
            case "yellow":
                return 4;
            case "green":
                return 5;
            case "blue":
                return 6;
            case "violet":
                return 7;
            case "grey":
                return 8;
            case "white":
                return 9;
            default:
                return 0;
        }
    }
}
