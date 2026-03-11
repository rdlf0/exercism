class Markdown {

    private static final String HEADIING_MARKUP = "<h%d>%s</h%d>";
    private static final String LIST_ITEM_MARKUP = "<li>%s</li>";
    private static final String PARAGRAPH_MARKUP = "<p>%s</p>";
    private static final String STRONG_PATTERN = "__(.+)__";
    private static final String STRONG_MARKUP = "<strong>$1</strong>";
    private static final String EM_PATTERN = "_(.+)_";
    private static final String EM_MARKUP = "<em>$1</em>";

    private boolean activeList = false;

    String parse(final String markdown) {
        final StringBuilder result = new StringBuilder();
        final String[] lines = markdown.split("\n");

        for (final String line : lines) {
            switch (line.charAt(0)) {
                case '#':
                    result.append(this.parseHeading(line));
                    break;
                case '*':
                    result.append(this.parseListItem(line));
                    break;
                default:
                    result.append(this.parseParagraph(line));
            }
        }

        if (activeList) {
            result.append("</ul>");
        }

        return result.toString();
    }

    private String parseHeading(final String markdown) {
        int count = 0;
        while (markdown.charAt(count) == '#') {
            count++;
        }

        final String heading =
                String.format(HEADIING_MARKUP, count, markdown.substring(count + 1), count);

        if (activeList) {
            activeList = false;
            return String.format("</ul>%s", heading);
        }

        return heading;
    }

    private String parseListItem(final String markdown) {
        final String skipAsterisk = markdown.substring(2);
        final String listItemString = applyAdditionalMarkup(skipAsterisk);
        final String listItem = String.format(LIST_ITEM_MARKUP, listItemString);

        if (!activeList) {
            activeList = true;
            return String.format("<ul>%s", listItem);
        }

        return listItem;
    }

    private String parseParagraph(final String markdown) {
        final String paragraph = String.format(PARAGRAPH_MARKUP, applyAdditionalMarkup(markdown));

        if (activeList) {
            activeList = false;
            return String.format("</ul>%s", paragraph);
        }

        return paragraph;
    }

    private String applyAdditionalMarkup(final String markdown) {
        return markdown
                .replaceAll(STRONG_PATTERN, STRONG_MARKUP)
                .replaceAll(EM_PATTERN, EM_MARKUP);
    }
}
