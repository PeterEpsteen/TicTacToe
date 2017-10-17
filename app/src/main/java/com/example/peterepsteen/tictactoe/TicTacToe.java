package com.example.peterepsteen.tictactoe;

/**
 * Created by PeterEpsteen on 10/5/2017.
 */

public class TicTacToe {
    public static final int SIDE = 3;
    private int turn;
    private int[][] game;

    public TicTacToe() {
        game = new int[SIDE][SIDE];
        resetGame();
    }

    public int play(int row, int col) {
        int oldTurn = turn;
        if (game[row][col] == 0) {
            game[row][col] = oldTurn;
            if (turn == 1)
                turn = 2;
            else if (turn == 2)
                turn = 1;
            return oldTurn;
        } else
            return 0;

    }


    public int whoWon() {
        if (checkRows() != 0)
            return checkRows();
        else if (checkColumns() != 0)
            return checkColumns();
        else if (checkDiagnols() != 0)
            return checkDiagnols();
        else
            return 0;
    }

    protected int checkRows() {
        for(int row = 0; row < SIDE; row++) {
            if(game[row][0] != 0 && game[row][0] == game[row][1] &&  game[row][1] == game[row][2] )
                return game[row][0];

        }
        return 0;
    }

    protected int checkColumns() {
        for (int col = 0; col < SIDE; col++) {
            if (game[0][col] != 0 && game[0][col] == game[1][col] && game[1][col] == game[2][col])
                return game[0][col];
           }
        return 0;
    }

    public int checkDiagnols() {
        if (game[0][0] != 0 && game[0][0] == game[1][1] && game[1][1] == game[2][2])
            return game[0][0];
        else if (game[0][2] != 0 && game[0][2] == game[1][1] && game[1][1] == game[2][0])
            return game[0][2];
        else
            return 0;
    }

    public boolean canNotPlay() {
        for (int row = 0; row<SIDE; row++){
            for(int col=0; col<SIDE; col++){
                if (game[row][col] == 0) return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return canNotPlay() || whoWon() > 0;
    }

    public void resetGame() {
        turn = 1;
        for(int row = 0; row < SIDE; row++){
            for(int col = 0; col < SIDE; col++){
                game[row][col] = 0;
            }
        }
    }

    public String getResult(){
        if(whoWon() == 1)
            return "Player 1 won!";
        else if(whoWon() == 2)
            return "Player 2 won!";
        else
            return "Player " + turn + " plays...";
    }
}
