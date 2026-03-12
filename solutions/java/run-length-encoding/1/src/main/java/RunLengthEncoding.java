class RunLengthEncoding {
    String encode(final String data) {
        final StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        while (right < data.length()) {
            final char leftChar = data.charAt(left);
            while (right < data.length() && data.charAt(right) == leftChar) {
                right++;
            }
            if (right - left > 1) {
                sb.append(right - left);
            }
            sb.append(leftChar);
            left = right;
        }

        return sb.toString();
    }

    String decode(final String data) {
        final StringBuilder sb = new StringBuilder();
        int repeat = 0;
        for (int i = 0; i < data.length(); i++) {
            final char ch = data.charAt(i);
            if (Character.isDigit(ch)) {
                repeat = repeat * 10 + (ch - '0');
                continue;
            }

            if (repeat == 0) {
                sb.append(ch);
            } else {
                sb.repeat(ch, repeat);
                repeat = 0;
            }
        }

        return sb.toString();
    }
}
