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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_screen);
        nameView = (TextView)findViewById(R.id.name) ;
        yourScore = (TextView) findViewById(R.id.yourscore);
        score = (TextView) findViewById(R.id.score);

        Intent nameIntent = getIntent();
        String name = nameIntent.getStringExtra("name");

        nameView.setText("Congratulations !!! " + name);

    }

    public  void  getValue()
    {

    }

    public void Finish(View view)
    {
       finishAffinity();
    }
    public void Back(View view)
    {
        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        finish();

      /*  Intent returnIntent = new Intent();
        returnIntent.putExtra("result",result);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();*/
    }
}