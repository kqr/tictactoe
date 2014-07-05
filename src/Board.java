

public class Board {
    private int turn = 0;
    private boolean gameOver = false;
    private int winner = -1;
    private int[] grid = new int[9];
    private int[] history = new int[9];

    public Board() {
        for (int i = 0; i < 9; i++) {
            grid[i] = -1;
            history[i] = -1;
        }
    }

    public void take(int square) throws AlreadyTakenException {
        if (!gameOver) {
            if (!taken(square)) {
                grid[square] = turn;
                history[turn] = square;
            } else {
                throw new AlreadyTakenException();
            }

            checkVictory();

            if (turn < 8) {
                turn++;
            } else {
                gameOver = true;
            }
        }
    }

    public boolean gameEnded() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }

    public boolean humanTurn() {
        return TicTacToe.getHumans()[turn % 2];
    }

    public int nextPlayer() {
        return turn % 2;
    }

    public int owns(int i) {
        assert taken(i);
        return grid[i] % 2;
    }

    public boolean taken(int i) {
        return grid[i] >= 0;
    }

    private void checkVictory() {
        boolean[][] owned = new boolean[2][9];

        for (int i = 0; i < grid.length; i++) {
            owned[TicTacToe.CROSS][i] = false;
            owned[TicTacToe.CIRCLE][i] = false;
            if (taken(i)) {
                owned[owns(i)][i] = true;
            }
        }

        for (int p = 0; p < 2; p++) {
            boolean hasWon = false;

            for (int i = 0; i < 3; i++) {
                hasWon |= owned[p][3*i] && owned[p][3*i + 1] && owned[p][3*i + 2];
                hasWon |= owned[p][i] && owned[p][i + 3] && owned[p][i + 6];
            }
            hasWon |= owned[p][0] && owned[p][4] && owned[p][8];
            hasWon |= owned[p][2] && owned[p][4] && owned[p][6];

            if (hasWon) {
                winner = p;
                gameOver = true;
            }
        }
    }

    public Board copy() {
        Board board = new Board();

        for (int i : history) {
            if (i == -1) {
                break;
            }
            try {
                board.take(i);
            } catch (AlreadyTakenException e) {
                throw new RuntimeException(e);
            }
        }

        return board;
    }
}
