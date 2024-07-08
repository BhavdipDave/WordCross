package com.aswdc_wordcross.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.aswdc_wordcross.R;


public class ActivityCategorySelect extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryselect);

        Button button3 = (Button) findViewById(R.id.threeltrbtn);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCategorySelect.this, ActivityThreeLetter.class);
                startActivity(intent);
            }
        });

        Button button1 = (Button) findViewById(R.id.fourltrbtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCategorySelect.this, ActivityFourLetter.class);
                startActivity(intent);

            }
        });

        Button button2 = (Button) findViewById(R.id.fiveltrbtn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCategorySelect.this, ActivityFiveLetter.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
    }
}
