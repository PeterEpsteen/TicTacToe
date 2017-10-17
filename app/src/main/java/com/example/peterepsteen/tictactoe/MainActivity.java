package com.example.peterepsteen.tictactoe;

import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.peterepsteen.tictactoe.R.color.colorAccent;

public class MainActivity extends AppCompatActivity {

    TicTacToe game = new TicTacToe();
    ButtonGridView buttonGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        buildGuiByCode();
    }

    private void buildGuiByCode() {
        ButtonClickHandler bch = new ButtonClickHandler();
        buttonGridView = new ButtonGridView(this, bch, TicTacToe.SIDE);
        buttonGridView.setStatus(game.getResult());

        setContentView(buttonGridView);
    }

    private void Update(int row, int col){
        int turn = game.play(row, col);
        if(turn == 1)
            buttonGridView.setButtonText("X", row, col);
        else if(turn == 2)
            buttonGridView.setButtonText("O", row, col);
        buttonGridView.setStatus(game.getResult());
        if (game.isGameOver()) {
            buttonGridView.EnableButtons(false);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Tic Tac Toe");
            builder.setMessage("Restart the game?");
            PlayAgain playAgain = new PlayAgain();
            builder.setPositiveButton("Yes", playAgain);
            builder.setNegativeButton("No", playAgain);
            builder.show();
        }

    }


    private class ButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //labeling outerloop to break
            Outerloop:
            for(int row = 0; row < TicTacToe.SIDE; row++) {
                for(int col = 0; col < TicTacToe.SIDE; col++){
                    if (buttonGridView.isButtonThere(v, row, col)) {
                        Update(row, col);
                        break Outerloop;
                    }
                }
            }

        }
    }

    private class PlayAgain implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i){
            if (i == -1) {
                buttonGridView.resetButtons();
                game.resetGame();
                buttonGridView.EnableButtons(true);
                buttonGridView.setStatus(game.getResult());
            }
            else if (i == -2) {
                MainActivity.this.finish();
            }
        }
    }


}
