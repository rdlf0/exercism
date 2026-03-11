public class BeerSong {

    private static final String LINE1 = "%1$d bottle%2$s of beer on the wall, %1$d bottle%2$s of beer.\n";
    private static final String LINE2 = "Take %s down and pass it around, %s bottle%s of beer on the wall.\n\n";
    private static final String LINE_NO_MORE = "No more bottles of beer on the wall, no more bottles of beer.\n";
    private static final String LINE_GO_BUY = "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n";

    public String sing(int iterator, int versesCount) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < versesCount; i++) {
            if (iterator == 0) {
                sb.append(LINE_NO_MORE);
                sb.append(LINE_GO_BUY);
            } else if (iterator == 1) {
                sb.append(String.format(LINE1, iterator, ""));
                sb.append(String.format(LINE2, "it", "no more", "s"));
            } else {
                sb.append(String.format(LINE1, iterator, "s"));
                sb.append(String.format(LINE2, "one", String.valueOf(iterator - 1), iterator == 2 ? "" : "s"));
            }

            iterator--;
        }

        return sb.toString();
    }

    public String singSong() {
        return this.sing(99, 100);
    }

}
