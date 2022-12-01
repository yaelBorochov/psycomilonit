package com.example.game;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "username";//Shared variables for SharedPreferences-username
    private static final String KEY_NAME = "key_username";

    private boolean flag;//Set boolean to check if the user has already entered his name
    private TextView edit_imput;
    private TextView txt_write_name;
    private Button btn_save_nave;
    private Button but_next;
    private TextView txt_user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag=false;
        txt_write_name = findViewById(R.id.txt_write_name);
        btn_save_nave = findViewById(R.id.btn_save_nave);
        but_next = findViewById(R.id.but_next);
        edit_imput = findViewById(R.id.edit_imput);
        txt_user_name = findViewById(R.id.txt_user_name);
        txt_user_name.setVisibility(View.INVISIBLE);


        displayName();//Method we defined below
        btn_save_nave.setOnClickListener(new View.OnClickListener() {//When the user clicks on "Save Name" it saves it in the method- saveName();
            @Override
            public void onClick(View view) {
                saveName();
            }
        });


        if(flag==true){//If the user entered his name you can go to the next page
            but_next.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    Intent activityChangeIntent = new Intent(MainActivity.this, Main2Activity.class);//Go to a new MainActivity
                    startActivity(activityChangeIntent);
                    finish();
                }
            });
        }
    }

    private void displayName() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);//Using the method SharedPreferences
        String name = sp.getString(KEY_NAME, null);
        if (name != null&&!name.isEmpty()) {//If the user typed his name you will enter a loop
            flag=true;
            txt_user_name.setText("שלום "+name);
            txt_user_name.setVisibility(View.VISIBLE);
            btn_save_nave.setVisibility(View.INVISIBLE);
            edit_imput.setVisibility(View.INVISIBLE);
            txt_write_name.setVisibility(View.INVISIBLE);
        }

    }
    private void saveName ()
    {
        String name= edit_imput.getText().toString().trim();
        if(name.isEmpty()){//If the name is empty it will send an error message- The first time the user logs in it happens
            edit_imput.setError("אופססססס אין שם ");
            edit_imput.requestFocus();

            if(!name.isEmpty()){//If already entered there then log in
                but_next.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {

                        Intent activityChangeIntent = new Intent(MainActivity.this, Main2Activity.class);////Go to a new MainActivity
                        startActivity(activityChangeIntent);
                        finish();
                    }
                });
            }
        }

        if(!name.isEmpty())//The second time the user logs in it happens -when the name isn't empty
        {
            but_next.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {

                    Intent activityChangeIntent = new Intent(MainActivity.this, Main2Activity.class);//Go to a new MainActivity
                    startActivity(activityChangeIntent);
                    finish();
                }
            });

        }

        SharedPreferences sp= getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        SharedPreferences.Editor e=sp.edit();
        e.putString(KEY_NAME,name);
        e.apply();
        edit_imput.setText(name);//Enter my username
        displayName();
    }

}

