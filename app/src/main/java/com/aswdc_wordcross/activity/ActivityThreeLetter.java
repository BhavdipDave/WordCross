package com.aswdc_wordcross.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aswdc_wordcross.Bean.Bean_ThreeLetters;
import com.aswdc_wordcross.DBHelper.DatabaseActivityThreeLetter;
import com.aswdc_wordcross.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActivityThreeLetter extends AppCompatActivity implements View.OnClickListener {

    Button[] buttons = new Button[3];
    TextView[] textViews = new TextView[5];
    public String matchString = "";
    String wordline = "";
    int a = 0, score = 0;
    Bean_ThreeLetters br;
    TextView tvFeedback;
    DatabaseActivityThreeLetter dbr;
    private String TAG;
    private TextView scoreLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeletter);

        isStoragePermissionGranted();

        buttons[0] = (Button) findViewById(R.id.Button10);
        buttons[0].setOnClickListener(this);
        buttons[1] = (Button) findViewById(R.id.Button11);
        buttons[1].setOnClickListener(this);
        buttons[2] = (Button) findViewById(R.id.Button12);
        buttons[2].setOnClickListener(this);

        textViews[0] = (TextView) findViewById(R.id.txtview10);
        textViews[1] = (TextView) findViewById(R.id.txtview11);
        textViews[2] = (TextView) findViewById(R.id.txtview12);

        tvFeedback = findViewById(R.id.main_tv_feedback);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);


        dbr = new DatabaseActivityThreeLetter(this);
        br = new Bean_ThreeLetters();


        a = dbr.getAllwordscount();
        tvFeedback.setVisibility(View.INVISIBLE);
        dataSetCheck();
    }

    public static String shuffleString(String word_linebyline) {
        List<String> letters = Arrays.asList(word_linebyline.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        shuffled = "";
        for (String letter : letters) {
            shuffled += letter;
        }
        return shuffled;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        Button button = (Button) v;
        for (int i = 0; i < 3; i++) {
            if (textViews[i].getTag() == null) {
                textViews[i].setText(button.getText().toString());
                textViews[i].setTag(i);
                matchString = matchString + button.getText().toString();


                if (matchString.equals(wordline)) {

                    tvFeedback.setVisibility(View.VISIBLE);
                    tvFeedback.setText("Congratulations");
                    tvFeedback.setTextColor(getColor(R.color.green));
                    scoreLabel.setText("SCORE: " + score);
                    score+=10;
                    if (score >= 0){
                        scoreLabel.setText("SCORE: " + score);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            for (int m = 0; m < 3; m++) {
                                textViews[m].setTag(null);
                                textViews[m].setText("");
                                matchString = "";
                            }
                            dataSetCheck();
                            tvFeedback.setVisibility(View.GONE);
                        }
                    }, 1000);

                } else if (matchString.length() == 3) {
                    tvFeedback.setVisibility(View.VISIBLE);
                    tvFeedback.setText("Opps! Try Again");
                    tvFeedback.setTextColor(getColor(R.color.red));
                    if(score>0) {
                            score -= 2;
                            scoreLabel.setText("SCORE: " + score);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            for (int m = 0; m < 3; m++) {
                                textViews[m].setTag(null);
                                textViews[m].setText("");
                                matchString = "";
                            }
                            tvFeedback.setVisibility(View.GONE);
                        }
                    }, 1000);


                }
                break;
            }
        }
    }

    void dataSetCheck() {
        String shuffleWord = "";
        Random r = new Random();
        int i1 = r.nextInt(a - 1) + 1;

        wordline = dbr.getWord(i1, ActivityThreeLetter.this);
        shuffleWord = shuffleString(wordline);

        for (int m = 0; m < 3; m++) {
            char text = shuffleWord.charAt(m);
            String textall = Character.toString(text);
            buttons[m].setText(textall);
            textViews[m].setText("");
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
