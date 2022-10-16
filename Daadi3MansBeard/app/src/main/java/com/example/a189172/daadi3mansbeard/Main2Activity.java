package com.example.a189172.daadi3mansbeard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

    public class Main2Activity extends AppCompatActivity
    {
        public static final String WINNER = "com.example.a189172.daadi3mansbeard.extra.winner";
        public static String winnerStr = " ";
        int blacktapped =0;
        //player represetation
        //firstplayer --0--yellow
        //secondplayer --1--blue
        int s=0;
        int x=1;
        int r0=0;
        int r1=10;
        int r2=10;
        int r3=10;
        int firstplayercount=0;
        int secondplayercount=0;
        Boolean gameActive = true;
        int activePlayer = 0;

        int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
        //state meanings
        //empty cell--2
        //filled cell--player representation
        //moving hint--3--black

        int from_cell = 0;

        int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},{0,3,6}, {1,4,7}, {2,5,8},{0,4,8}, {2,4,6}};
        public void playerTap(View view)
        {
            if (firstplayercount < 3 || secondplayercount < 3)
            {
                ImageView img = (ImageView) view;
                int tappedImage = Integer.parseInt(img.getTag().toString());


                if (!gameActive) {
                    gameReset(view);
                }
                if (gameState[tappedImage] == 2 && (firstplayercount < 3 || secondplayercount < 3))
                {
                    gameState[tappedImage] = activePlayer;
                    ++x;
                    if (x % 2 == 0) {
                        firstplayercount++;
                    } else {
                        secondplayercount++;
                    }
                    img.setTranslationY(-1000f);
                    if (activePlayer == 0) {
                        img.setImageResource(R.drawable.yellow);
                        activePlayer = 1;
                        TextView status = findViewById(R.id.status);
                        gameState[tappedImage]=0;
                        status.setText("blue's turn");
                    } else {
                        img.setImageResource(R.drawable.blue);
                        activePlayer = 0;
                        TextView status = findViewById(R.id.status);
                        gameState[tappedImage]=1;
                        status.setText("yellow's turn");
                    }
                    img.animate().translationYBy(1000f).setDuration(300);
                }
                // Check if any player has won
                for (int[] winPosition : winPositions) {
                    if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                            gameState[winPosition[1]] == gameState[winPosition[2]] &&
                            gameState[winPosition[0]] != 2) {
                        // Somebody has won! - Find out who!

                        gameActive = false;
                        if (gameState[winPosition[0]] == 0) {
                            winnerStr = "yellow has won";

                        } else {
                            winnerStr= "Blue has won";
                        }
                        // Update the status bar for winner announcement
                        TextView status = findViewById(R.id.status);
                        status.setText(winnerStr);
                        String winPlayer = status.getText().toString();
                        Intent intent = new Intent(this, Main3Activity.class);
                        intent.putExtra(WINNER,winPlayer);
                        startActivity(intent);
                        firstplayercount = 0;
                        secondplayercount = 0;
                        blacktapped=0;
                    }
                }
            }
            else if ((firstplayercount == 3) && (secondplayercount == 3))
            {
                ImageView img = (ImageView) view;
                int tappedImage = Integer.parseInt(img.getTag().toString());
                if (!gameActive) {
                    gameReset(view);
                }
                if(blacktapped>0&&gameState[tappedImage]!=3)
                {
                    for(int j=0;j<9;j++)
                    {
                        s = gameState[j];
                        if (s == 3&&j==0)
                        {
                            ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==1)
                        {
                            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==2)
                        {
                            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==3)
                        {
                            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==4)
                        {
                            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==5)
                        {
                            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==6)
                        {
                            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==7)
                        {
                            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
                            gameState[j] = 2;
                        }
                        if (s == 3&&j==8)
                        {
                            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
                            gameState[j] = 2;
                        }
                    }
                }


                if (gameState[tappedImage] != 3 && (gameState[tappedImage] == activePlayer ))
                {
                    switch (tappedImage)
                    {
                        case 0:
                            if (gameState[1] == 2) {
                                ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.black);
                                gameState[1] = 3;
                                r1 = 1;
                            }
                            if (gameState[3] == 2) {
                                ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.black);
                                r2 = 3;
                                gameState[3] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r3 = 4;
                                gameState[4] = 3;
                            }
                            from_cell = 1;
                            break;

                        case 1:
                            if (gameState[0] == 2) {
                                ((ImageView) findViewById(R.id.imageView0)).setImageResource(R.drawable.black);
                                r1 = 0;
                                gameState[0] = 3;
                            }
                            if (gameState[2] == 2) {
                                ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.black);
                                r2 = 2;
                                gameState[2] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r3 = 4;
                                gameState[4] = 3;
                            }
                            from_cell = 2;
                            break;

                        case 2:
                            if (gameState[1] == 2) {
                                ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.black);
                                r1 = 1;
                                gameState[1] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r2 = 4;
                                gameState[4] = 3;
                            }
                            if (gameState[5] == 2) {
                                ((ImageView) findViewById(R.id.imageView5)).setImageResource(R.drawable.black);
                                r3 = 5;
                                gameState[5] = 3;
                            }
                            from_cell = 3;
                            break;

                        case 3:
                            if (gameState[0] == 2) {
                                ((ImageView) findViewById(R.id.imageView0)).setImageResource(R.drawable.black);
                                r1 = 0;
                                gameState[0] = 3;
                            }
                            if (gameState[6] == 2) {
                                ((ImageView) findViewById(R.id.imageView6)).setImageResource(R.drawable.black);
                                r2 = 6;
                                gameState[6] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r3 = 4;
                                gameState[4] = 3;
                            }
                            from_cell = 4;
                            break;

                        case 4:
                            for (int i = 0; i < 9; i++) {
                                int s = gameState[i];
                                if (s == 2&&i==0)
                                {
                                    ((ImageView) findViewById(R.id.imageView0)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==1)
                                {
                                    ((ImageView) findViewById(R.id.imageView1)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==2)
                                {
                                    ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==3)
                                {
                                    ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==4)
                                {
                                    ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==5)
                                {
                                    ((ImageView) findViewById(R.id.imageView5)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==6)
                                {
                                    ((ImageView) findViewById(R.id.imageView6)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==7)
                                {
                                    ((ImageView) findViewById(R.id.imageView7)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                                if (s == 2&&i==8)
                                {
                                    ((ImageView) findViewById(R.id.imageView8)).setImageResource(R.drawable.black);
                                    r0 = 1;
                                    gameState[i] = 3;
                                }
                            }
                            from_cell = 5;
                            break;

                        case 5:
                            if (gameState[2] == 2) {
                                ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.black);
                                r1 = 2;
                                gameState[2] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r2 = 4;
                                gameState[4] = 3;
                            }
                            if (gameState[8] == 2) {
                                ((ImageView) findViewById(R.id.imageView8)).setImageResource(R.drawable.black);
                                r3 = 8;
                                gameState[8] = 3;
                            }
                            from_cell = 6;
                            break;

                        case 6:
                            if (gameState[7] == 2) {
                                ((ImageView) findViewById(R.id.imageView7)).setImageResource(R.drawable.black);
                                r1 = 7;
                                gameState[7] = 3;
                            }
                            if (gameState[3] == 2) {
                                ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.black);
                                r2 = 3;
                                gameState[3] = 3;
                            }
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r3 = 4;
                                gameState[4] = 3;
                            }
                            from_cell = 7;
                            break;

                        case 7:
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r1 = 4;
                                gameState[4] = 3;
                            }
                            if (gameState[6] == 2) {
                                ((ImageView) findViewById(R.id.imageView6)).setImageResource(R.drawable.black);
                                r2 = 6;
                                gameState[6] = 3;
                            }
                            if (gameState[8] == 2) {
                                ((ImageView) findViewById(R.id.imageView8)).setImageResource(R.drawable.black);
                                r3 = 8;
                                gameState[8] = 3;
                            }
                            from_cell = 8;
                            break;

                        case 8:
                            if (gameState[4] == 2) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(R.drawable.black);
                                r1 = 4;
                                gameState[4] = 3;
                            }
                            if (gameState[5] == 2) {
                                ((ImageView) findViewById(R.id.imageView5)).setImageResource(R.drawable.black);
                                r2 = 5;
                                gameState[5] = 3;
                            }
                            if (gameState[7] == 2) {
                                ((ImageView) findViewById(R.id.imageView7)).setImageResource(R.drawable.black);
                                r3 = 7;
                                gameState[7] = 3;
                            }
                            from_cell = 9;
                            break;
                    }
                    blacktapped=1;
                }
                else if (gameState[tappedImage] == 3&&from_cell!=0)
                {
                    for (int k = 0; k < 9; k++)
                    {
                        if (gameState[k] == 3)
                        {
                            if (k == 0) {
                                ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 1) {
                                ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 2) {
                                ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 3) {
                                ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 4) {
                                ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 5) {
                                ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 6) {
                                ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 7) {
                                ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
                                gameState[k]=2;
                            }
                            if (k == 8) {
                                ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
                                gameState[k]=2;
                            }
                        }
                    }
                    if(from_cell==1)
                    {
                        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==2)
                    {
                        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==3)
                    {
                        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==4)
                    {
                        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==5)
                    {
                        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==6)
                    {
                        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==7)
                    {
                        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==8)
                    {
                        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }
                    else if(from_cell==9)
                    {
                        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
                        gameState[from_cell-1]=2;
                        from_cell=0;
                    }



                    //((ImageView)findViewById(R.id.imageView+(from_cell-1))).setImageResource(0);
                    if (activePlayer == 0) {
                        img.setImageResource(R.drawable.yellow);
                        gameState[tappedImage]=0;
                        activePlayer = 1;
                        TextView status = findViewById(R.id.status);
                        //status.setText(gameState[0]+""+gameState[1]+" "+gameState[2]+" "+gameState[3]+" "+gameState[4]+" "+gameState[5]+" "+gameState[6]+" "+gameState[7]+""+gameState[8]);

                        status.setText("Blue's turn");
                    } else {
                        img.setImageResource(R.drawable.blue);
                        gameState[tappedImage]=1;
                        activePlayer = 0;
                        TextView status = findViewById(R.id.status);
                        status.setText("yellow's turn");
                        //status.setText(gameState[0]+""+gameState[1]+" "+gameState[2]+" "+gameState[3]+" "+gameState[4]+" "+gameState[5]+" "+gameState[6]+" "+gameState[7]+""+gameState[8]);

                    }
                    for (int[] winPosition : winPositions)
                    {
                        if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                                gameState[winPosition[0]] != 2)
                        {
                            // Somebody has won! - Find out who!
                            String winnerStr;
                            gameActive = false;
                            if (gameState[winPosition[0]] == 0) {
                                winnerStr = "yellow has won";

                            } else {
                                winnerStr = "Blue has won";
                            }
                            // Update the status bar for winner announcement
                            TextView status = findViewById(R.id.status);
                            status.setText(winnerStr);
                            //status.setText(gameState[0]+""+gameState[1]+" "+gameState[2]+" "+gameState[3]+" "+gameState[4]+" "+gameState[5]+" "+gameState[6]+" "+gameState[7]+""+gameState[8]);
                            String winPlayer = status.getText().toString();
                            Intent intent = new Intent(this, Main3Activity.class);
                            intent.putExtra(WINNER,winPlayer);
                            startActivity(intent);
                            blacktapped=0;
                        }
                    }
                    from_cell = 0;
                }

            }
        }
        public void gameReset (View view)
        {
            gameActive = true;
            activePlayer = 0;
            x = 0;
            for (int i = 0; i < gameState.length; i++)
            {
                gameState[i] = 2;
            }
            ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

            TextView status = findViewById(R.id.status);
            status.setText("yellow's Turn - Tap to play");
            firstplayercount=0;
            secondplayercount=0;
            blacktapped=0;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
