package com.example.asus.gamekuis;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView CountLabel;
    private TextView QuestionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;


    private String rightAnswer;
    private int RightAnswerCount =0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 10 ;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] ={
            {"Batik yang motifnya dibuat dengan hanya menggunakan tangan disebu?","Batik tulis","Batik cap","Batik pekalongan","Batik ikat"},
            {"Lagu kebangsaan negara Indonesia?","Indonesia Raya","Ibu Kita Kartini","Halo Halo Bandung","Padamu Negeri"},
            {"Ibu kota negara Indonesia?","Jakarta","Manila","New Delhi","Kuala Lumpur"},
            {"Lagu daerah asal papua?","Apuse","Mojang Priangan","Ondel ondel","Es lilin"},
            {"Tari tradisional berasal dari Cirebon?","Tari Topeng","Saman","Tari Piring","Tari Tor tor"},
            {"Presiden pertama Indonesia?","Soekarno","Soeharta","Joko Widodo","Sunjaya"},
            {"Ada berapa provinsi di negara Indonesia?","34","22","33","32"},
            {"Indonesia Merdeka pada tanggal?","17 Agustus 1945","20 februari 1999","12 desember 1951","11 agustus 1945"},
            {"Tari saman berasal dari?","Aceh","Pekanbaru","Padang","Bandung"},
            {"Lambang negara Indonesia","Garuda Pancasila","UUD 1945","Pancasila","Merah Putih"},
            {"Ibu kota jawa barat","Bandung","Jakarta","Surabaya","Palembang"},
            {"Makanan khas Yogyakarta","Gudeg","rendang","Empal Gentong","Rumba"},
            {"Warna bendera negara indonesia","Merah Putih","Hijau Kunimng","Merah Biru","Merah Kuning Hijau"},
            {"Presiden Indonesia saat ini","Jokowi","Soeharto","Megawati","Soekarno"},
            {"Salah satu jenis teater yang berasal dari Jawa Tengah disebut","Ketoprak","Barong","Lenong","Tarling"},
            {"Jenis alat musik nonvokal kordofon adalah","sasando","serunai","bonang","gendang"},
            {"Ibu kota Jawa Timur","Surabaya","Makassar","Palembang","Padang"},
            {"Cinta tanah air disebut juga ","nasionalisme","patriotisme","chauvinisme","internasionalisme"},
            {"Makanan khas Padang","Rendang","pempek","Rawon","papeda"},
            {"Seorang pahlawan wanita yang berasal dari Aceh adalah","Cut Nyak Dhien","RA Kartini","RA Kartini","Nyi Ahmad Dahlar"}




    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        CountLabel = (TextView)findViewById(R.id.CountLabel);
        QuestionLabel = (TextView)findViewById(R.id.QuestionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

//        int QuizCategory = getIntent().getIntExtra("QUIZ_CATEGORY" ,0);
//        Log.v("CATEGORY_TAG",QuizCategory +" ");


        //Create Quiz Array from DataQuiz
        for (int i = 0; i < quizData.length; i++){

            //persiapan Array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Soal
            tmpArray.add(quizData[i][1]); //jawaban benar
            tmpArray.add(quizData[i][2]); //pilihan1
            tmpArray.add(quizData[i][3]); //pilihan2
            tmpArray.add(quizData[i][4]); //pilihan3

            //add tempArray to QuizArray
            quizArray.add(tmpArray);


        }
        showNextQuiz();

    }
    public void showNextQuiz(){
        //Update QuizCountTAbel

        CountLabel.setText("Pertanyaan ke :" + quizCount);
        //Generate random number betweem 0 and 14  (quizArray size-1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        //pick one quiz set
            ArrayList<String> quiz = quizArray.get(randomNum);


        //set Question and Right Answer
        //array format:{"Country","Right Answer","Choice1","Choice2","Choice3"}
        QuestionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);


        //remove "country" from quiz and shuffle choices

        quiz.remove(0);
        Collections.shuffle(quiz);
        //set choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray

        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view){
        //Get Pushed button
        MediaPlayer mySong;
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)){

            //Benar

            alertTitle = "Benar Sekali!!";
            RightAnswerCount++;
            mySong = MediaPlayer.create(MainActivity.this, R.raw.benar);


        }else {
            //Wrong
            alertTitle ="Aduh Salah..";
            mySong = MediaPlayer.create(MainActivity.this,R.raw.salah);


        }
        mySong.start();
        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Jawabannya :" + rightAnswer);
        builder.setPositiveButton("Lanjut", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT){
                    //show result
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", RightAnswerCount);
                    startActivity(intent);
                }else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
            builder.setCancelable(false);
            builder.show();

    }



}

