package com.example.nztaa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView htcv = (CardView) findViewById(R.id.hometitlecv);


        CardView tqcv = (CardView) findViewById(R.id.tqcv);
        CardView aboutuscv = (CardView) findViewById(R.id.aboutuscv);

        aboutuscv.setOnClickListener(this);
        tqcv.setOnClickListener(this);
        htcv.setOnClickListener(this);



        }

    @Override
    public void onClick(View v) {
        Intent intent ;


        switch (v.getId()){

            case R.id.tqcv:
                intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                break;




            case R.id.aboutuscv:
                intent = new Intent(this,Aboutus.class);
                startActivity(intent);
                break;





            case R.id.hometitlecv:

                Uri uri = Uri.parse("https://online.nzta.govt.nz/licence-test/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
        }
    }
    }
