package com.example.tictac;

import android.widget.EdgeEffect;
import android.widget.TextView;
import android.content.res.Resources;

import java.util.jar.Attributes;

class GameLogic {
     private int[][] gameBoard;

     private int player = 2;//следим за последовательностью игроков

     private TextView playerDisplay;
     private String[] NameUser = new String[1];

     GameLogic() {//массив ноликов и крестиков
         gameBoard = new int[3][3];
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 gameBoard[i][j] = 0;
             }
         }
     }

     public boolean updateGameBoard(int row, int col) {//
         if (gameBoard[row - 1][col - 1] == 0) {
             gameBoard[row - 1][col - 1] = player;

         if (player == 1) {
             playerDisplay.setText(NameUser[0]+"(нолик)");
             //playerDisplay.setTextColor(resources.getColor(R.color.Ocolor));
         }
         else {
             playerDisplay.setText(NameUser[1]+"(крестик)");
         }
             return true;
         } else {
             return false;
         }
     }


    public void reset_board() {
         for(int i = 0; i< 3;i++)
         {for (int j = 0; j < 3; j++) {
              gameBoard[i][j] = 0;
         }
         }
         playerDisplay.setText(NameUser[0]+"(нолик)");
         player = 2;
    }

    public int[][] getGameBoard(){
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }



    public void setPlayerDisplay(TextView playerDisplay, String[] nameUser){
         this.playerDisplay=playerDisplay;
         this.NameUser=nameUser;
         if (NameUser[0].trim().equals("")){
             NameUser[0]="Первый игрок";
         }
        if (NameUser[1].trim().equals("")){
            NameUser[1]="Второй игрок";
        }
    }
}
