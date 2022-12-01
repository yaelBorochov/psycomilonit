package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Main4Activity extends AppCompatActivity {

    private TextView txt_win;
    private TextView txt_wrong;
    private TextView txt_grade;
    private TextView but_exit;
    private Dictionary dictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        txt_win = findViewById(R.id.txt_win);
        txt_wrong = findViewById(R.id.txt_wrong);
        txt_grade = findViewById(R.id.txt_grade);
        but_exit = findViewById(R.id.but_exit);
        dictionary=new Dictionary();


        txt_win.setText("הנצחונות שלך : " + Main3Activity.getCorrectAnswer());//Bring me the score saved in Main3Activity
        txt_wrong.setText("הטעויות שלך : " + Main3Activity.getWongAnswer());

    if (Main3Activity.getCorrectAnswer() + Main3Activity.getWongAnswer() != 0) {//If "error" + "truth" are not equal 0 enter
        double d = (100 / (Main3Activity.getCorrectAnswer() + Main3Activity.getWongAnswer()) * (Main3Activity.getCorrectAnswer()));
        int value = (int) Math.round(d);//Save the score and make it int
        txt_grade.setText("" + value);
    }

    if (Main3Activity.getCorrectAnswer() != 0 && Main3Activity.getWongAnswer() == 0) {//If the "error" is equal to 0, make the score 100
        txt_grade.setText("" + 100);
    }

    if ((Main3Activity.getCorrectAnswer() == 0 && Main3Activity.getWongAnswer() == 0)||Main3Activity.getCorrectAnswer() == 0 && Main3Activity.getWongAnswer() != 0)
        txt_grade.setText("" + 0);//If the user did not receive any points at all or everything is wrong, he will be given a score of 0


         findViewById(R.id.but_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}


