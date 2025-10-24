package com.example.homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button buttonExercise1 = findViewById(R.id.buttonExercise1);
        Button buttonExercise2 = findViewById(R.id.buttonExercise2);
        Button buttonExercise3 = findViewById(R.id.buttonExercise3);
        Button buttonExercise4 = findViewById(R.id.buttonExercise4);
        Button buttonExercise5 = findViewById(R.id.buttonExercise5);

        buttonExercise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });

        buttonExercise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, BmiActivity.class));
            }
        });

        buttonExercise3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, CalculatorActivity.class));
            }
        });

        buttonExercise4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, ProfileActivity.class));
            }
        });

        buttonExercise5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, Lab2Activity.class));
            }
        });
    }
}
