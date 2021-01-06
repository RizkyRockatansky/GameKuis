package com.example.asus.gamekuis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button button;
    MediaPlayer mySong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        button = (Button) findViewById(R.id.tentang);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutActivity();
            }
        });

    }
    public void openAboutActivity(){
        Intent intent = new Intent(this,AboutActivity.class);
        startActivity(intent);

    }




    public void startQuiz(View view){

        int QuizCategory =0; //All

        switch (view.getId()) {
            case R.id.asia:
               QuizCategory = 1;
                break;


        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.putExtra("QUIZ_CATEGORY", QuizCategory);
        startActivity(intent);

    }
    public void play(View view){
        if(mySong == null){
            mySong = MediaPlayer.create(this,R.raw.musiktarik);
        }
        mySong.start();
    }

    public void pause(View view){
        if (mySong !=null){
            mySong.pause();
        }
    }

    }



