package com.example.asus.gamekuis;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class HalamanDepan extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_depan);

        button = (Button) findViewById(R.id.buttonawal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartActivity();
            }
        });

    }
    public void openStartActivity(){
        Intent intent = new Intent(this,StartActivity.class);
        startActivity(intent);

    }

    

}
