class RailFenceCipher {
    private final int rows;

    RailFenceCipher(final int rows) {
        this.rows = rows;
    }

    String getEncryptedData(final String message) {
        final int messageLen = message.length();
        final char[][] board = new char[rows][messageLen];
        int row = 0;
        int dir = 1;

        for (int i = 0; i < messageLen; i++) {
            board[row][i] = message.charAt(i);
            row += dir;
            if (row == 0 || row == rows - 1) {
                dir *= -1;
            }
        }

        final StringBuilder sb = new StringBuilder();
        for (final char[] r : board) {
            for (final char ch : r) {
                if (Character.isLetter(ch)) {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    String getDecryptedData(final String message) {
        final int messageLen = message.length();
        final char[][] board = new char[rows][messageLen];
        int row = 0;
        int dir = 1;

        for (int i = 0; i < messageLen; i++) {
            board[row][i] = '*';
            row += dir;
            if (row == 0 || row == rows - 1) {
                dir *= -1;
            }
        }

        int messageIndex = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < messageLen; c++) {
                if (board[r][c] == '*' && messageIndex < messageLen) {
                    board[r][c] = message.charAt(messageIndex++);
                }
            }
        }

        final StringBuilder sb = new StringBuilder();
        row = 0;
        dir = 1;
        for (int i = 0; i < messageLen; i++) {
            sb.append(board[row][i]);
            row += dir;
            if (row == 0 || row == rows - 1) {
                dir *= -1;
            }
        }

        return sb.toString();
    }
}
