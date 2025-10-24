package com.example.simplecontroller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA,edtB,edtKQ;
    Button btnGiai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addControls();
        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int a = Integer.parseInt(edtA.getText().toString());
                    int b = Integer.parseInt(edtB.getText().toString());
                    if (a == 0) {
                        if (b == 0) {
                            edtKQ.setText("Vô số nghiệm");
                        } else {
                            edtKQ.setText("Vô nghiệm");
                        }
                    } else {
                        double x = (double) -b / a;
                        edtKQ.setText("" + x);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Lỗi: Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        private void addControls() {
            edtA = (EditText) findViewById(R.id.edtA);
            edtB = (EditText) findViewById(R.id.edtB);
            edtKQ = (EditText) findViewById(R.id.edtKQ);
            btnGiai = (Button) findViewById(R.id.btnGiai);
        }
    public void giaiPT(View view) {
        try {
            int a = Integer.parseInt(edtA.getText().toString());
            int b = Integer.parseInt(edtB.getText().toString());
            if (a == 0) {
                if (b == 0) {
                    edtKQ.setText("Vô số nghiệm");
                } else {
                    edtKQ.setText("Vô nghiệm");
                }
            } else {
                double x = (double) -b / a;
                edtKQ.setText("" + x);
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Lỗi: Vui lòng nhập số", Toast.LENGTH_SHORT).show();
        }
    }

    }