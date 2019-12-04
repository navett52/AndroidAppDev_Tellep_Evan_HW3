package com.example.evanstictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mGameStatus;
    Button[] mGameButtons = new Button[9];
    TicTacToeGame mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameStatus = findViewById(R.id.gameStatus);

        for (int i = 0; i < mGameButtons.length; i++) {
            int buttonId = getResources().getIdentifier("button" + i, "id", getPackageName());
            mGameButtons[i] = findViewById(buttonId);
            mGameButtons[i].setOnClickListener(onPlayButtonClicked);
        }

        Button newGame = findViewById(R.id.newGame);
        newGame.setOnClickListener(onNewGameButtonClicked);

        mGame = new TicTacToeGame();
    }

    private View.OnClickListener onPlayButtonClicked = (v) -> {

        // Safely cast the view to a button.
        Button gameButton = null;
        if (v instanceof Button) {
            gameButton = (Button) v;
        }

        // Map the button to a button in our array.
        int index = -1;
        for (int i = 0; i < mGameButtons.length; i++) {
            if (gameButton.getId() == mGameButtons[i].getId()) {
                index = i;
            }
        }

        // If game is being played and the current space hasn't been set yet, do moves.
        boolean stillPlaying = true;
        if (mGame.playGame && mGame.mBoard[index] == '-' && stillPlaying) {
            mGame.setPlayerMove(index);
            gameButton.setTextColor(getResources().getColor(R.color.player));
            stillPlaying = !isGameOver(1);

            if (stillPlaying) {
                int move = mGame.getComputerMove();
                mGameButtons[move].setTextColor(getResources().getColor(R.color.computer));
                stillPlaying = !isGameOver(0);
            }
        }
    };

    private View.OnClickListener onNewGameButtonClicked = (v) -> {
        mGame = new TicTacToeGame();
        for (Button square : mGameButtons) {
            square.setText("");
        }
    };

    private boolean isGameOver(int player) {
        for (int i = 0; i < mGameButtons.length; i++) {
            if (mGame.mBoard[i] != '-') {
                mGameButtons[i].setText(String.valueOf(mGame.mBoard[i]));
            }
        }

        if (mGame.checkForWinner() == 1) {
            mGame.playGame = false;
            if (player == 0) {
                mGameStatus.setText(getString(R.string.lose));
            } else {
                mGameStatus.setText(getString(R.string.win));
            }
            return true;
        } else if (mGame.checkForWinner() == 0) {
            mGame.playGame = false;
            mGameStatus.setText(getString(R.string.tie));
            return true;
        } else {
            mGameStatus.setText(getString(R.string.keepPlaying));
            return false;
        }
    }

}
