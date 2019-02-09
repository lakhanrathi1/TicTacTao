package com.example.lakhan.tictactao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winingpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int player = 0;
    boolean gameisactive = true;
    boolean iswin = false;
    ImageView counter;
    GridLayout gridLayout;

    public void autoplay(int tapped){

        Toast.makeText(getApplicationContext(),"" + tapped,Toast.LENGTH_SHORT).show();

//        ImageView imageView = (ImageView)findViewById

        counter.setTranslationY(-1000f);
        if(gamestate[tapped]==2 && gameisactive) {

            gamestate[tapped] = player;
            counter.setTranslationY(-1000f);

            if (player == 0) {

                ((ImageView)gridLayout.getChildAt(tapped)).setImageResource(R.drawable.tic3);
                player = 1;

            } else {

                ((ImageView)gridLayout.getChildAt(tapped)).setImageResource(R.drawable.tic2);
                player = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(300);
            for (int[] winingpostion : winingpositions) {

                if (gamestate[winingpostion[0]] == gamestate[winingpostion[1]] && gamestate[winingpostion[1]] == gamestate[winingpostion[2]] && gamestate[winingpostion[0]] != 2) {
                    System.out.println(gamestate[winingpostion[0]]);
                    gameisactive=false;
                    String winneris = "Player 1";
                    if (winingpostion[0] == 1) {
                        winneris = "Player 2";
                    }


                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
                    layout.setVisibility(View.VISIBLE);
                    TextView win = (TextView) findViewById(R.id.winner);
                    win.setText(winneris + " has won");
                    //iswin=true;
                }else {
                    iswin= true;
                    for (int counterstate: gamestate){

                        if (counterstate==2)
                            iswin=false;
                    }

                }

                if(iswin){
                    TextView win = (TextView) findViewById(R.id.winner);
                    win.setText("Its draw");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
                    layout.setVisibility(View.VISIBLE);


                }

            }

        }

    }


    public void dropIn(View view){
        counter = (ImageView) view;
        int tapped=Integer.parseInt(counter.getTag().toString());
        autoplay(tapped);
        int x;
//        int random = getRandom();
//        while (gamestate[random]!= 2)
//            random = getRandom();
//        autoplay(random);

        if (gamestate[4]==2)
            x=4;
//            autoplay(4);
        else if (gamestate[0]==2)
            x=0;
        else if (gamestate[2]==2)
            x=2;
        else if (gamestate[6]==2)
            x=6;
        else if (gamestate[8]==2)
            x=8;

        else{
             x = getRandom();
        while (gamestate[x]!= 2)
            x = getRandom();
//        autoplay(random);


        }

        autoplay(x);


    }

    private int getRandom() {
        Random random  = new Random();
        return random.nextInt(9);
    }

    public void playAgain(View view){
           gameisactive = true;

           LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);
           layout.setVisibility(view.INVISIBLE);
          // int[] gamestate = {2,2,2,2,2,2,2,2,2};
           //int[][] winingpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
            player = 0;
           for (int i=0;i<9;i++){

               gamestate[i]=2;
           }
//           GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
           for(int i=0;i<gridLayout.getChildCount();i++){

               ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
           }

       }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);

    }
}
