package com.example.evanstictactoe;

import java.util.Random;

public class TicTacToeGame {

    public char[] mBoard = new char[9];
    boolean playGame = false;
    Random random = new Random();

    public TicTacToeGame() {
        newGame();
    }

    public void newGame() {
        for (int i = 0; i < mBoard.length; i++) {
            mBoard[i] = '-';
        }
        playGame = true;
    }

    public void setPlayerMove(int index) {
        mBoard[index] = 'X';
    }

    public int getComputerMove() {
        boolean madeMove = false;
        int move = -1;
        while (!madeMove) {
            move = random.nextInt(9);
            if (mBoard[move] == '-') {
                mBoard[move] = 'O';
                madeMove = true;
            }
        }
        return move;
    }

    public int checkForWinner() {

        if (checkWins('X') || checkWins('O')) {
            return 1;
        }

        boolean stillMoves = false;
        for (char space : mBoard) {
            if (space == '-') {
                stillMoves = true;
                break;
            }
        }

        if (!stillMoves) {
            return 0;
        }

        return -1;
    }

    private boolean checkWins(char player) {
        if (mBoard[0] == player && mBoard[1] == player && mBoard[2] == player) {
            return true;
        } else if (mBoard[3] == player && mBoard[4] == player && mBoard[5] == player) {
            return true;
        } else if (mBoard[6] == player && mBoard[7] == player && mBoard[8] == player) {
            return true;
        } else if (mBoard[0] == player && mBoard[3] == player && mBoard[6] == player) {
            return true;
        } else if (mBoard[1] == player && mBoard[4] == player && mBoard[7] == player) {
            return true;
        } else if (mBoard[2] == player && mBoard[5] == player && mBoard[8] == player) {
            return true;
        } else if (mBoard[0] == player && mBoard[4] == player && mBoard[8] == player) {
            return true;
        } else if (mBoard[2] == player && mBoard[4] == player && mBoard[6] == player) {
            return true;
        } else {
            return false;
        }
    }
}
