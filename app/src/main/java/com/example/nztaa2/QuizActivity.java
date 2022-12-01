package com.example.nztaa2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nztaa2.Models.QuizAnswer;
import com.example.nztaa2.Models.QuizQuestion;
import com.example.nztaa2.Utils.AppSettings;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView currentQuizNumberText;
    private TextView editTextQuiz;
    private TextView editTextAnswerA;
    private TextView editTextAnswerB;
    private TextView editTextAnswerC;
    private TextView editTextAnswerD;
    private TextView editTextTimeCount;
    private Button lButton;
    private Button rButton;
    private Button endButton;
    private ArrayList<QuizQuestion> quizQuestionArrayList;
    private ArrayList<QuizAnswer> quizAnswerArrayList;

    private int currentNumber = 1;
    private int currentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        currentQuizNumberText = findViewById(R.id.currentQuizNumberText);
        editTextQuiz = findViewById(R.id.editTextQuiz);
        editTextAnswerA = findViewById(R.id.editTextAnswerA);
        editTextAnswerB = findViewById(R.id.editTextAnswerB);
        editTextAnswerC = findViewById(R.id.editTextAnswerC);
        editTextAnswerD = findViewById(R.id.editTextAnswerD);
        editTextTimeCount = findViewById(R.id.editTextTimeCount);
        lButton = findViewById(R.id.buttonPrevious);
        rButton = findViewById(R.id.buttonNext);
        endButton = findViewById(R.id.buttonEnd);

        QuizLists quizLists = new QuizLists();
        quizLists.execute(AppSettings.APP_URL_ADDRESS + "All_Quizs.php");

        while (true) {
            if (quizQuestionArrayList!=null&&quizQuestionArrayList.size()==35) {
                editTextTimeCount.setText("30:00");
                currentQuizNumberText.setText(String.valueOf(currentNumber));
                editTextQuiz.setText(quizQuestionArrayList.get(currentIndex).getQuestion());
                AnswerLists answerLists = new AnswerLists();
                answerLists.execute(AppSettings.APP_URL_ADDRESS + "SearchAnswersByID.php?quizQuestionId="+quizQuestionArrayList.get(currentIndex).getId());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    if (quizAnswerArrayList!=null&&quizAnswerArrayList.size()==4){
                        editTextAnswerA.setText(quizAnswerArrayList.get(0).getAnswer());
                        editTextAnswerB.setText(quizAnswerArrayList.get(1).getAnswer());
                        editTextAnswerC.setText(quizAnswerArrayList.get(2).getAnswer());
                        editTextAnswerD.setText(quizAnswerArrayList.get(3).getAnswer());
                    }
                    break;
                }
                break;
            }
        }
        lButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentIndex>=1){
                    currentQuizNumberText.setText(String.valueOf(--currentNumber));
                    editTextQuiz.setText(quizQuestionArrayList.get(--currentIndex).getQuestion());
                    AnswerLists answerLists = new AnswerLists();
                    answerLists.execute(AppSettings.APP_URL_ADDRESS + "SearchAnswersByID.php?quizQuestionId="+quizQuestionArrayList.get(currentIndex).getId());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        if (quizAnswerArrayList!=null&&quizAnswerArrayList.size()==4){
                            editTextAnswerA.setText(quizAnswerArrayList.get(0).getAnswer());
                            editTextAnswerB.setText(quizAnswerArrayList.get(1).getAnswer());
                            editTextAnswerC.setText(quizAnswerArrayList.get(2).getAnswer());
                            editTextAnswerD.setText(quizAnswerArrayList.get(3).getAnswer());
                        }
                        break;
                    }
                }
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentNumber<35){
                    currentQuizNumberText.setText(String.valueOf(++currentNumber));
                    editTextQuiz.setText(quizQuestionArrayList.get(++currentIndex).getQuestion());
                    AnswerLists answerLists = new AnswerLists();
                    answerLists.execute(AppSettings.APP_URL_ADDRESS + "SearchAnswersByID.php?quizQuestionId="+quizQuestionArrayList.get(currentIndex).getId());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (true){
                        if (quizAnswerArrayList!=null&&quizAnswerArrayList.size()==4){
                            editTextAnswerA.setText(quizAnswerArrayList.get(0).getAnswer());
                            editTextAnswerB.setText(quizAnswerArrayList.get(1).getAnswer());
                            editTextAnswerC.setText(quizAnswerArrayList.get(2).getAnswer());
                            editTextAnswerD.setText(quizAnswerArrayList.get(3).getAnswer());
                        }
                        break;
                    }
                }
            }
        });


    }

    class QuizLists extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String[] urlAddress) {
            quizQuestionArrayList = new ArrayList<>();
            try {
                //step1: Create the obj of URL
                URL url = new URL(urlAddress[0]);
                //step2: Open the connection
                URLConnection connection = url.openConnection();
                //Step3 and 4: used for output stream
                //step5: Use Input Stream to fetch the data
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while((line = bufferedReader.readLine())!=null){
                    line = line.trim().substring(0,line.trim().length()-2)+"]";
                    JSONArray jsonArray = new JSONArray(line);
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // JSON数组里面的具体-JSON对象
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        quizQuestionArrayList.add(gson.fromJson(jsonObject.toString(),QuizQuestion.class));
                    }
                    Log.d("Quizs fetched",line.toString());
                }
            } catch (MalformedURLException e) {
                Log.d("Error found",e.getMessage().toString());
            } catch (IOException e) {
                Log.d("IOException found",e.getMessage().toString());
            }catch(Exception e){
                Log.d("Exception found",e.getMessage().toString());
            }
            return "";
        }
    }

    class AnswerLists extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String[] urlAddress) {
            quizAnswerArrayList = new ArrayList<>();
            try {
                //step1: Create the obj of URL
                URL url = new URL(urlAddress[0]);
                //step2: Open the connection
                URLConnection connection = url.openConnection();
                //Step3 and 4: used for output stream
                //step5: Use Input Stream to fetch the data
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while((line = bufferedReader.readLine())!=null){
                    line = line.trim().substring(0,line.trim().length()-2)+"]";
                    JSONArray jsonArray = new JSONArray(line);
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        // JSON数组里面的具体-JSON对象
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        quizAnswerArrayList.add(gson.fromJson(jsonObject.toString(),QuizAnswer.class));
                    }
                    Log.d("Quizs fetched",line.toString());
                }
            } catch (MalformedURLException e) {
                Log.d("Error found",e.getMessage().toString());
            } catch (IOException e) {
                Log.d("IOException found",e.getMessage().toString());
            }catch(Exception e){
                Log.d("Exception found",e.getMessage().toString());
            }
            return "";
        }
    }




}