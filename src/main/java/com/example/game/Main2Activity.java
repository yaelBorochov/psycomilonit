package com.example.game;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {


    private Button click_low;
    private Button click_medium;
    private Button click_high;
    private Button but_instructions;
    private static int endex;//Defining variables whose function is to specify which level the user has selected


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        click_low = findViewById(R.id.click_low);
        click_medium = findViewById(R.id.click_medium);
        click_high = findViewById(R.id.click_high);
        but_instructions = findViewById(R.id.but_instructions);

        but_instructions.setOnClickListener(new View.OnClickListener() {//Instructions page in the game
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),PopActivity.class);
                startActivity(i);
            }
        });





        click_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endex=0;//low level
                Intent activityChangeIntent = new Intent(Main2Activity.this, Main3Activity.class);//Go to a new MainActivity
                startActivity(activityChangeIntent);
                finish();


            }
        });


        click_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endex=1;//medium level
                Intent activityChangeIntent = new Intent(Main2Activity.this, Main3Activity.class);//Go to a new MainActivity
                startActivity(activityChangeIntent);
                finish();

            }
        });

        click_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endex=2;// high level
                Intent activityChangeIntent = new Intent(Main2Activity.this, Main3Activity.class);//Go to a new MainActivity
                startActivity(activityChangeIntent);
                finish();


            }
        });
    }
    public static int getEndex(){//Send me the variable to the department dictionary
        int data =endex;
        return data;
    }

}
