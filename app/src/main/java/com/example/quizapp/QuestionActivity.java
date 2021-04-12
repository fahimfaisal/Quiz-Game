package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
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
import android.content.Intent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class QuestionActivity extends AppCompatActivity {
    TextView question;
    TextView progressview;
    Button option1;
    Button option2;
    Intent myIntent;
    Button option3;
    Button submit;
    String selection;
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<String> answer1 = new ArrayList<>();
    ArrayList<String> answer2 = new ArrayList<>();
    ArrayList<String> answer3 = new ArrayList<>();
    ArrayList<String> correct = new ArrayList<>();
    int progress = 0;
    int quesNo= 1;
    int score = 0;
    String playerName;
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
        submit = (Button) findViewById(R.id.submit);
        progressview = (TextView) findViewById(R.id.progress);

        LoadJson();
        Run();






    }
    public  void nextpage()
    {
        myIntent = new Intent(this, FinalScreen.class);
        SendData();
        startActivity(myIntent);
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

    public void SendData()
    {
        Bundle b = new Bundle();
        Intent intent = getIntent();
        playerName = intent.getStringExtra("name");


        b.putString("name",playerName);
        b.putString("score", String.valueOf(score));
        b.putString("total", String.valueOf(questions.size()));
        myIntent.putExtras(b);
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

    public void change(int color)
    {
        if (selection.equals("A"))
        {
            option1.setBackgroundColor(color);
        }
        else if (selection.equals("B"))
        {
            option2.setBackgroundColor(color);

        }
        else if(selection.equals("C"))
        {
            option3.setBackgroundColor(color);
        }

    }

    public void CheckAnswer()
    {
        if (selection.equals(correct.get(progress)))
        {
            score++;

            if (selection.equals("A"))
            {
                option1.setBackgroundColor(Color.GREEN);
            }
            else if (selection.equals("B"))
            {
                option2.setBackgroundColor(Color.GREEN);

            }
            else if(selection.equals("C"))
            {
                option3.setBackgroundColor(Color.GREEN);
            }


        }
        else{


            if (selection.equals("A"))
            {
                option1.setBackgroundColor(Color.RED);
            }
            else if (selection.equals("B"))
            {
                option2.setBackgroundColor(Color.RED);

            }
            else if(selection.equals("C"))
            {
                option3.setBackgroundColor(Color.RED);
            }









            if (correct.get(progress).equals("A"))
            {
                option1.setBackgroundColor(Color.GREEN);
            }
            else if (correct.get(progress).equals("B"))
            {
                option2.setBackgroundColor(Color.GREEN);

            }
            else if(correct.get(progress).equals("C"))
            {
                option3.setBackgroundColor(Color.GREEN);
            }

        }

        progress++;
        quesNo++;
        selection = null;


    }

    public void ButtonReset()
    {
        progressview.setTextColor(Color.BLACK);
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
        progressview.setText("Question " + quesNo + " out of " + questions.size());



        question.setText(questions.get(place));
        option1.setText(answer1.get(place));
        option2.setText(answer2.get(place));
        option3.setText(answer3.get(place));

    }


    public void AvoidSelection()
    {
        selection = null;
    }

    public void CheckA(View view){
        ButtonReset();
        option1.setBackgroundColor(Color.rgb(255,200,0));
        selection= "A";



    }

    public void CheckB(View view){
        ButtonReset();
        option2.setBackgroundColor(Color.rgb(255,200,0));
        selection= "B";

    }

    public void CheckC(View view){
        ButtonReset();
        option3.setBackgroundColor(Color.rgb(255,200,0));

        selection= "C";
    }



    public void Submit(View view)
    {

        if (selection == null)
        {
            progressview.setText("Please select an option");
            progressview.setTextColor(Color.RED);
        }
        else
        {
            CheckAnswer();

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (progress+1>questions.size())
                    {
                        nextpage();
                        finish();
                    }
                    else{
                        RenderQuestion(progress);
                    }

                }
            }, 1000);

        }




    }

}