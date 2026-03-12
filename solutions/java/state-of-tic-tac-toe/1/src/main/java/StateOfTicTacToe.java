class StateOfTicTacToe {
    public GameState determineState(final String[] board) {
        int countX = 0;
        int countO = 0;
        int countWinningRows = 0;

        // Check rows
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row].charAt(col) == 'X') {
                    countX++;
                } else if (board[row].charAt(col) == 'O') {
                    countO++;
                }
            }

            if (board[row].charAt(0) == board[row].charAt(1)
                    && board[row].charAt(1) == board[row].charAt(2)
                    && board[row].charAt(0) != ' ') {
                countWinningRows++;
            }
        }

        if (countO > countX) {
            throw new IllegalArgumentException("Wrong turn order: O started");
        }

        if (countX > countO + 1) {
            throw new IllegalArgumentException("Wrong turn order: X went twice");
        }

        if (countWinningRows > 1) {
            throw new IllegalArgumentException(
                    "Impossible board: game should have ended after the game was won");
        } else if (countWinningRows == 1) {
            return GameState.WIN;
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0].charAt(col) == board[1].charAt(col)
                    && board[1].charAt(col) == board[2].charAt(col)
                    && board[0].charAt(col) != ' ') {
                return GameState.WIN;
            }
        }

        // Check falling diagonal
        if (board[0].charAt(0) == board[1].charAt(1)
                && board[1].charAt(1) == board[2].charAt(2)
                && board[0].charAt(0) != ' ') {
            return GameState.WIN;
        }

        // Check rising diagonal
        if (board[2].charAt(0) == board[1].charAt(1)
                && board[1].charAt(1) == board[0].charAt(2)
                && board[2].charAt(0) != ' ') {
            return GameState.WIN;
        }

        return countX + countO == 9 ? GameState.DRAW : GameState.ONGOING;
    }
}
