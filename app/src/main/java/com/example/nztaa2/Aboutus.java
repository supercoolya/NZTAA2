package com.example.nztaa2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Aboutus extends AppCompatActivity {
    private Button btnBack ,btndir;
    private CardView aiscv, findmecv;
    private ImageView ivFb,ivIg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        btnBack = (Button) findViewById(R.id.btnBack2);
        aiscv = (CardView) findViewById(R.id.aiscv);




        aiscv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://ais.ac.nz/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ivFb = (ImageView) findViewById(R.id.ivFb);
        ivFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.facebook.com/StudyAIS/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        ivIg = (ImageView) findViewById(R.id.ivIg);
        ivIg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/study_ais/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        btndir = (Button) findViewById(R.id.btndir);

        btndir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.google.com/maps/dir//Auckland+Institute+Of+Studies+28A+Linwood+Avenue,+Mount+Albert,+Auckland+1025/@-36.8735066,174.6514404,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x6d0d470498839c35:0x8a5a0f2fce67fdcf!2m2!1d174.7214807!2d-36.8735271"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


            }
        });



        findmecv = (CardView) findViewById(R.id.findmecv);
        findmecv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openmaps();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();

            }
        });


    }

    private void openmaps() {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}