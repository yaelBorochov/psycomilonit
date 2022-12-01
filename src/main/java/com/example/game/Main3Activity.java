package com.example.game;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;


public class Main3Activity extends AppCompatActivity {
    private static int wrongAnswer;//Defining variables to save the correct and incorrect answer
    private static int correctAnswer;

    private TextView txt_dictionary;
    private Button but_word1;
    private Button but_word2;
    private Button but_word3;
    private Button but_word4;
    private Button but_stop;
    Dictionary dictionary;




    private int screenWidth;
    private ImageView arrowLeft;
    private float arrowLeftX;
    private float arrowLeftY;
    private Handler handler = new Handler();
    private Timer timer ;//Useing timer to set time
    private Timer coler;
    private boolean yesOrNo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt_dictionary = findViewById(R.id.txt_dictionary);
        but_word1 = findViewById(R.id.but_word1);
        but_word2 = findViewById(R.id.but_word2);
        but_word3 = findViewById(R.id.but_word3);
        but_word4 = findViewById(R.id.but_word4);
        but_stop = findViewById(R.id.but_close);
        dictionary = new Dictionary();
        timer=new Timer();
        coler=new Timer();
        yesOrNo=true;



       fun(dictionary);
        arrowLeft = (ImageView) findViewById(R.id.arrowLeft);//Image ID

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        arrowLeft.setX(screenWidth + 80.0f);//Define the position of the arrow
        arrowLeft.setY(-80.0f);

        but_word1.setBackgroundColor(Color.BLUE);//Set colors for buttons
        but_word2.setBackgroundColor(Color.BLUE);
        but_word3.setBackgroundColor(Color.BLUE);
        but_word4.setBackgroundColor(Color.BLUE);



        but_word1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String click = but_word1.getText().toString();
                if (yesOrNo ==true) {
                    if (dictionary.report(click) == false) {//Checks if it's true or false and changes color accordingly
                        but_word1.setBackgroundColor(Color.RED);
                    } else {
                        but_word1.setBackgroundColor(Color.GREEN);
                    }
                    timerGood();
                    timer.cancel();//Reset all data to select a word again
                    arrowLeft.setX(screenWidth + 80.0f);
                    arrowLeft.setY(-80.0f);
                    yesOrNo=false;
                }
            }
        });

            but_word2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String click = but_word2.getText().toString();
                    if (yesOrNo == true) {
                        if (dictionary.report(click) == false) {
                            but_word2.setBackgroundColor(Color.RED);
                        } else {
                            but_word2.setBackgroundColor(Color.GREEN);
                        }
                        timerGood();
                        timer.cancel();
                        arrowLeft.setX(screenWidth + 80.0f);
                        arrowLeft.setY(-80.0f);
                        yesOrNo=false;
                    }

                }
            });

            but_word3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String click = but_word3.getText().toString();
                    if (yesOrNo == true) {
                        if (dictionary.report(click) == false) {
                            but_word3.setBackgroundColor(Color.RED);
                        } else {
                            but_word3.setBackgroundColor(Color.GREEN);
                        }
                        timerGood();
                        timer.cancel();
                        arrowLeft.setX(screenWidth + 80.0f);
                        arrowLeft.setY(-80.0f);
                        yesOrNo=false;
                    }
                }
            });

            but_word4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String click = but_word4.getText().toString();
                    if (yesOrNo == true) {
                        if (dictionary.report(click) == false) {
                            but_word4.setBackgroundColor(Color.RED);
                        } else {
                            but_word4.setBackgroundColor(Color.GREEN);
                        }
                        timerGood();
                        timer.cancel();
                        arrowLeft.setX(screenWidth + 80.0f);
                        arrowLeft.setY(-80.0f);
                        yesOrNo=false;
                    }
                }
            });


            but_stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {//Click to end the game
                    wrongAnswer=dictionary.getWrong();//Save how many times you have done good and not good
                    correctAnswer=dictionary.getRight();
                    Intent activityChangeIntent = new Intent(Main3Activity.this, Main4Activity.class);
                    startActivity(activityChangeIntent);
                    finish();

                }
            });


        }
    public static int getWongAnswer(){//Set a method to save the score and send to Main4Activity

        int data = wrongAnswer;
        return data;
    }
    public static int getCorrectAnswer(){


        int data = correctAnswer;
        return data;
    }

    public void fun(Dictionary dictionary) {
        String[][] words = dictionary.getNext();//Gets an array of 4 words
        String[][] oneWords = dictionary.getOneWords();//Gets an array of 1 words
        MovesAnArrow();
        txt_dictionary.setText(oneWords[0][0]);
        but_word1.setText(words[0][1]);
        but_word2.setText(words[1][1]);
        but_word3.setText(words[2][1]);
        but_word4.setText(words[3][1]);
        yesOrNo=true;
    }

    public void changePos(){//Moves the arrow and sets variables each time
        arrowLeftX+=10;
      if (arrowLeft.getX()>screenWidth){
            arrowLeftX=-50.0f;
            arrowLeftY=300.0f;
        }
        if(arrowLeft.getX()==screenWidth){//If the arrow reaches the end of the page you will reset everything again
            int i=dictionary.getWrong();
            dictionary.setWrong(i+1);//Add another mistake to me and send
            timer.cancel();//Data Reset
            arrowLeft.setX(screenWidth + 80.0f);
            arrowLeft.setY(-80.0f);
            fun(dictionary);

        }

        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);



    }
    public void MovesAnArrow(){//A timer that runs the arrow for me according to the seconds we set
        timer.purge();
        timer=new Timer();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();

                    }
                });
            }


        }, 0, 150);//10s
    }

    public void timerWait(){
   new Handler().postDelayed(new Runnable() {//it waits a few seconds and then runs the methods inside it
       @Override
       public void run() {
           but_word1.setBackgroundColor(Color.BLUE);
           but_word2.setBackgroundColor(Color.BLUE);
           but_word3.setBackgroundColor(Color.BLUE);
           but_word4.setBackgroundColor(Color.BLUE);
           fun(dictionary);
       }
   },1000);
}
    public void timerGood(){//Ask where the correct answer is and paint it green
        new Handler().postDelayed(new Runnable() {//it waits a few seconds and then runs the methods inside it
            @Override
            public void run() {
               if(but_word1.getText().equals(dictionary.getOneWords()[0][1]))
                   but_word1.setBackgroundColor(Color.GREEN);
                if(but_word2.getText().equals(dictionary.getOneWords()[0][1]))
                    but_word2.setBackgroundColor(Color.GREEN);
                if(but_word3.getText().equals(dictionary.getOneWords()[0][1]))
                    but_word3.setBackgroundColor(Color.GREEN);
                if(but_word4.getText().equals(dictionary.getOneWords()[0][1]))
                    but_word4.setBackgroundColor(Color.GREEN);
                timerWait();
            }
        },500);
    }

}
