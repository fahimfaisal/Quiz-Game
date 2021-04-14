package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        private Button button;
        EditText name;
        String open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.startbutton);
        name = (EditText) findViewById(R.id.input);




    }



        public void start(View view){

        if (name.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Enter Your Name",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent questionIntent = new Intent(this, QuestionActivity.class);
            questionIntent.putExtra("name", name.getText().toString());
            startActivity(questionIntent);



        }

    }

}