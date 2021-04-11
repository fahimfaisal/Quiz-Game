package com.example.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionActivity extends AppCompatActivity {
    TextView question;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    String selection;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answer1 = new ArrayList<>();
    ArrayList<String> answer2 = new ArrayList<>();
    ArrayList<String> answer3 = new ArrayList<>();
    ArrayList<String> correct = new ArrayList<>();
    int progress = 0;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        question = (TextView) findViewById(R.id.question) ;
        option1 = (Button) findViewById(R.id.answer1);
        option2 = (Button) findViewById(R.id.answer2);
        option3 = (Button) findViewById(R.id.answer3);
        option4 = (Button) findViewById(R.id.answer4);

        LoadJson();
        Run();




    }

    public void LoadJson(){
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("questions");
            for (int i =0; i<jsonArray.length(); i++)
            {
                JSONObject data = jsonArray.getJSONObject(i);

                questions.add(data.getString("question"));

                answer1.add(data.getString("A"));
                answer2.add(data.getString("B"));
                answer3.add(data.getString("C"));
                correct.add(data.getString("correct"));

            }
        }catch (
                JSONException e){
            e.printStackTrace();
        }


    }

    public void Run()
    {
        RenderQuestion(progress);



    }




    public String JsonDataFromAsset()
    {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("questions.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json= new String(bufferData, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return  null;
        }

        return  json;
    }

    public void CheckAnswer()
    {
        if (selection.equals(correct.get(progress)))
        {
            progress++;
            score++;
        }
        else{

            if (correct.get(progress).equals("A"))
            {
                option1.setBackgroundColor(Color.RED);
            }
            else if (correct.get(progress).equals("B"))
            {
                option2.setBackgroundColor(Color.RED);

            }
            else if(correct.get(progress).equals("C"))
            {
                option3.setBackgroundColor(Color.RED);
            }
            progress++;
        }



    }

    public void ButtonReset()
    {
        option1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
            R.color.purple_700));
        option2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                R.color.purple_700));
        option3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                R.color.purple_700));

    }

    public void RenderQuestion(int place)
    {

        ButtonReset();
        question.setText(questions.get(place));
        option1.setText(answer1.get(place));
        option2.setText(answer2.get(place));
        option3.setText(answer3.get(place));

    }




    public void CheckA(View view){
        ButtonReset();
        option1.setBackgroundColor(Color.BLACK);
        selection= "A";



    }

    public void CheckB(View view){
        ButtonReset();
        option2.setBackgroundColor(Color.BLACK);
        selection= "B";

    }

    public void CheckC(View view){
        ButtonReset();
        option3.setBackgroundColor(Color.BLACK);

        selection= "C";
    }



    public void Submit(View view)
    {

        CheckAnswer();

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                RenderQuestion(progress);
            }
        }, 3000);









    }




}