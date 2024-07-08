package com.aswdc_wordcross;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aswdc_wordcross.activity.ActivityStartScreen;

public class ActivitySplashScreen extends AppCompatActivity {

    int splashTimeOut = 5000; //for Splash screen timer
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ActivitySplashScreen.this, ActivityStartScreen.class);
                startActivity(intent);
                finish(); //for close activity
            }
        }, splashTimeOut);
    }


}
