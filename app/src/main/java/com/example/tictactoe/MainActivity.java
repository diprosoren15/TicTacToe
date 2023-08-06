package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //state means
//    0-X
//    1-O
//    2-null
    int[][] winningposis = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}};
    public void playerTap(View v) {
        ImageView img = (ImageView) v;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (!gameActive){
            gameReset(v);
            return;
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.statusBar);
                status.setText("Your turn player 2. Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.statusBar);
                status.setText("Your turn player 1. Tap to play");
            }
            img.animate().translationYBy(1000f);
        }
        gameActive = checkForWin(winningposis,gameState,gameActive);
    }
    public boolean checkForWin(int[][] winningposis, int[] gameState, boolean gameActive){
        for (int[] winningpos : winningposis) {

            if (gameState[winningpos[0]] == gameState[winningpos[1]] && gameState[winningpos[1]]== gameState[winningpos[2]] && gameState[winningpos[0]] != 2) {
                String winner = "";
                gameActive = false;
                if (gameState[winningpos[0]] == 0) {
                    winner = "Player 1 has won";
                } else winner = "Player 2 has won";
                TextView status = findViewById(R.id.statusBar);
                status.setText(winner);
            }
        }
        return gameActive;
    }
    public void gameDraw(View v){
        int len = gameState.length - 1;
        while (len>=0){
            if(gameState[len] != 2){
                len--;
            }
            if (len==0 && gameState[0]!=2){
                TextView status = findViewById(R.id.statusBar);
                status.setText("Match Drawn");
            }
        }
        gameReset(v);
        return;
    }
    public void gameReset(View v){
        gameActive = true;
        activePlayer = 0;
        for (int i=0;i< gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}