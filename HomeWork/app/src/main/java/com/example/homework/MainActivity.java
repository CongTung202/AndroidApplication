package com.example.homework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextFahrenheit;
    private EditText editTextCelsius;
    private Button buttonConvertToCelsius;
    private Button buttonConvertToFahrenheit;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFahrenheit = findViewById(R.id.editTextFahrenheit);
        editTextCelsius = findViewById(R.id.editTextCelsius);
        buttonConvertToCelsius = findViewById(R.id.buttonConvertToCelsius);
        buttonConvertToFahrenheit = findViewById(R.id.buttonConvertToFahrenheit);
        buttonClear = findViewById(R.id.buttonClear);

        buttonConvertToCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToCelsius();
            }
        });

        buttonConvertToFahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToFahrenheit();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }

    private void convertToCelsius() {
        String fahrenheitStr = editTextFahrenheit.getText().toString();
        if (fahrenheitStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập độ F", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double fahrenheit = Double.parseDouble(fahrenheitStr);
            double celsius = (fahrenheit - 32) * 5.0 / 9.0;
            editTextCelsius.setText(String.format("%.2f", celsius));
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Định dạng số không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertToFahrenheit() {
        String celsiusStr = editTextCelsius.getText().toString();
        if (celsiusStr.isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập độ C", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double celsius = Double.parseDouble(celsiusStr);
            double fahrenheit = (celsius * 9.0 / 5.0) + 32;
            editTextFahrenheit.setText(String.format("%.2f", fahrenheit));
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Định dạng số không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextFahrenheit.setText("");
        editTextCelsius.setText("");
    }
}
