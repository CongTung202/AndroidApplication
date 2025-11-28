package com.example.activitiyintentbundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitiyA extends AppCompatActivity {
    Button btnGet;
    TextView txtGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivitiyA.this,ActivityB.class);
                startActivityForResult(intent,100);

            }
        });
    }

    private void addViews() {
        btnGet=findViewById(R.id.btnGet);
        txtGet=findViewById(R.id.txtGet);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    String name = bundle.getString("Name");
                    int age = bundle.getInt("Age");
                    txtGet.setText("Name: " + name + "\nAge: " + age);
                }
            }
        }
    }

}