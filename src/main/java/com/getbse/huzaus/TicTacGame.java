package com.getbse.huzaus;

import java.util.Arrays;

public class TicTacGame {

    private final int xSize;
    private final int ySize;
    private char lastPlayer = O;
    private final static char X = 'X';
    private final static char O = 'O';


    private final char[][] field;

    public TicTacGame(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        field = new char[ySize][xSize];
    }

    public String play(int x, int y) {
        validate(x, y);
        if (field[y][x] != 0) {
            throw new IllegalArgumentException();
        }
        switchPlayer();
        field[y][x] = lastPlayer;
        if (checkWinCondition(x, y)) {
            return "win";
        }
        if (checkDraftCondition()) {
            return "draft";
        }
        return "keep playing";
    }

    private boolean checkDraftCondition() {
        for (int i = 1; i < ySize; i++) {
            for (int j = 1; j < xSize; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWinCondition(int x, int y) {
        return checkVerticalWin(x) || checkHorizontalWin(y)
                || checkRightDiagonalWin() || checkLeftDiagonalWin();
    }

    private boolean checkHorizontalWin(int y) {
        for (int i = 0; i < xSize; i++) {
            if (lastPlayer != field[y][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkVerticalWin(int x) {
        for (int i = 0; i < ySize; i++) {
            if (lastPlayer != field[i][x]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRightDiagonalWin() {
        for (int i = 0; i < xSize; i++) {
            if (lastPlayer != field[i][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLeftDiagonalWin() {
        for (int i = 0; i < xSize; i++) {
            if (lastPlayer != field[xSize - i - 1][i]) {
                return false;
            }
        }
        return true;
    }

    private void switchPlayer() {
        if (lastPlayer == X) {
            lastPlayer = O;
        } else {
            lastPlayer = X;
        }
    }

    private void validate(int x, int y) {
        if (x < 0 || x > (xSize - 1)) {
            throw new IllegalArgumentException();
        }
        if (y < 0 || y > (ySize - 1)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "TicTacGame{" +
                "xSize=" + xSize +
                ", ySize=" + ySize +
                ", lastPlayer=" + lastPlayer + ",\n" +
                Arrays.toString(field[0]) + "\n" +
                Arrays.toString(field[1]) + "\n" +
                Arrays.toString(field[2]) + "\n" +
                '}';
    }
}
