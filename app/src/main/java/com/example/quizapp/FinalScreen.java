package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalScreen extends AppCompatActivity {
    TextView nameView;
    TextView yourScore;
    TextView score;
    String name;
    String totalScore;
    String total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);
        nameView = (TextView)findViewById(R.id.name) ;
        yourScore = (TextView) findViewById(R.id.yourscore);
        score = (TextView) findViewById(R.id.score);

        GetValue();


        nameView.setText("Congratulations " + name +" !");
        yourScore.setText("YOUR SCORE");
        score.setText(totalScore +"/"+total);
    }

    public  void  GetValue()
    {
        Bundle getValue = getIntent().getExtras();
        name = getValue.getString("name");
        totalScore = getValue.getString("score");
        total = getValue.getString("total");
    }

    public void Finish(View view)
    {
       finishAffinity();
    }
    public void Back(View view)
    {
        finish();
    }
}