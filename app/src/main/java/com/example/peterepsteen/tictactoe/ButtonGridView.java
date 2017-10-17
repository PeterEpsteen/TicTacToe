package com.example.peterepsteen.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.StringBuilderPrinter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

/**
 * Created by PeterEpsteen on 10/12/2017.
 */

class ButtonGridView extends GridLayout {
    private Button[][] buttons;
    TextView label;
    TicTacToe game = new TicTacToe();
    int side;


    public ButtonGridView(Activity context, View.OnClickListener bch, int side) {
        super(context);

        //create layout manager as gridlayout
        this.setColumnCount(TicTacToe.SIDE);
        this.setRowCount(TicTacToe.SIDE + 1);

        //get width of screen
        Point size = new Point(); //has x and y cords
        context.getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x/TicTacToe.SIDE;

        //Create buttons and add to grid layout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        for (int row = 0; row < TicTacToe.SIDE; row++) {
            for (int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col] = new Button(context);
                buttons[row][col].setOnClickListener(bch);  //subscribe to buttonclick
                buttons[row][col].setTextSize((int) (w * .2));
                this.addView(buttons[row][col], w, w);

            }
        }

        //create "Play" or who won label
        label = new TextView(context);
        GridLayout.Spec rowSpec = GridLayout.spec(3, 1);
        GridLayout.Spec colSpec = GridLayout.spec(0,3);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);
        label.setLayoutParams(params);
        label.setHeight(w);
        label.setWidth(w*3);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(getResources().getColor(R.color.green));
        label.setTextSize((float) (w*.1));
        label.setText(game.getResult());
        this.addView(label);

    }

    public void setButtonText(String text, int row, int col){
        buttons[row][col].setText(text);
    }

    public void setStatus(String text) {
        label.setText(text);
    }

    public void EnableButtons(boolean enabled){
        for(int row = 0; row < TicTacToe.SIDE; row++){
            for(int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }

    public void resetButtons(){
        for(int row = 0; row < TicTacToe.SIDE; row++){
            for(int col = 0; col < TicTacToe.SIDE; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public boolean isButtonThere(View v, int row, int col) {
        return v == buttons[row][col];
    }
}
