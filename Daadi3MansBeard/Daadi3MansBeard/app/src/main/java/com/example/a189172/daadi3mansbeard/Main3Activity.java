package com.example.a189172.daadi3mansbeard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
        TextView textView3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3);
            textView3 = findViewById(R.id.textView2);
            Intent intent = getIntent();
            String winner = intent.getStringExtra(Main2Activity.WINNER);
            textView3.setText(winner);
            if(winner.contentEquals("yellow has won"))
            {
                textView3.setTextColor(Color.parseColor("#FFFF00"));
            }
            else {
                textView3.setTextColor(Color.parseColor("#0000FF"));
            }
        }
}
