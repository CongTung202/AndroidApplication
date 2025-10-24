package com.example.homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BmiActivity extends AppCompatActivity {

    private EditText editTextName, editTextHeight, editTextWeight;
    private Button buttonCalculateBmi;
    private TextView textViewBmiResult, textViewDiagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        editTextName = findViewById(R.id.editTextName);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        buttonCalculateBmi = findViewById(R.id.buttonCalculateBmi);
        textViewBmiResult = findViewById(R.id.textViewBmiResult);
        textViewDiagnosis = findViewById(R.id.textViewDiagnosis);

        buttonCalculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });
    }

    private void calculateBmi() {
        String name = editTextName.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();
        String weightStr = editTextWeight.getText().toString().trim();

        if (name.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float height = Float.parseFloat(heightStr);
            float weight = Float.parseFloat(weightStr);

            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            float bmi = weight / (height * height);

            textViewBmiResult.setText(String.format("BMI= %.1f", bmi));

            String diagnosis;
            if (bmi < 18) {
                diagnosis = "Người gầy";
            } else if (bmi < 25) {
                diagnosis = "Bạn bình thường";
            } else if (bmi < 30) {
                diagnosis = "Béo phì độ I";
            } else if (bmi < 35) {
                diagnosis = "Béo phì độ II";
            } else {
                diagnosis = "Béo phì độ III";
            }
            textViewDiagnosis.setText("Chẩn đoán: " + diagnosis);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Chiều cao hoặc cân nặng không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
