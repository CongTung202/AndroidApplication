package com.example.homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNumberA, editTextNumberB;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        editTextNumberA = findViewById(R.id.editTextNumberA);
        editTextNumberB = findViewById(R.id.editTextNumberB);
        textViewResult = findViewById(R.id.textViewResult);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String numAStr = editTextNumberA.getText().toString();
        String numBStr = editTextNumberB.getText().toString();

        if (numAStr.isEmpty() || numBStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ hai số a và b", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double numA = Double.parseDouble(numAStr);
            double numB = Double.parseDouble(numBStr);
            double result = 0;
            String operation = "";

            int id = v.getId();
            if (id == R.id.buttonAdd) {
                result = numA + numB;
                operation = "a + b = ";
            } else if (id == R.id.buttonSubtract) {
                result = numA - numB;
                operation = "a - b = ";
            } else if (id == R.id.buttonMultiply) {
                result = numA * numB;
                operation = "a * b = ";
            } else if (id == R.id.buttonDivide) {
                if (numB == 0) {
                    Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = numA / numB;
                operation = "a / b = ";
            }

            textViewResult.setText(operation + result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Định dạng số không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
