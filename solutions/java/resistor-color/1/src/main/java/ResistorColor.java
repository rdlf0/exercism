import java.util.Arrays;

class ResistorColor {
    private static final String[] colorsArray = {
            "black",
            "brown",
            "red",
            "orange",
            "yellow",
            "green",
            "blue",
            "violet",
            "grey",
            "white"
    };

    int colorCode(String color) {
        return Arrays.asList(colorsArray).indexOf(color);
    }

    String[] colors() {
        return colorsArray;
    }
}
