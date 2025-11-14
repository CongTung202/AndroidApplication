package com.example.simplecontroller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImageButtonActivity extends AppCompatActivity {
    ImageView imgAnh;
    ImageButton btnAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagebutton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((int) imgAnh.getTag() == R.drawable.siesta_screen) {
                    imgAnh.setImageResource(R.drawable.cross_screen);
                    imgAnh.setTag(R.drawable.cross_screen);
                }else{
                    imgAnh.setImageResource(R.drawable.siesta_screen);
                    imgAnh.setTag(R.drawable.siesta_screen);
                }
            }
        });
    }
    

    private void addControls() {
        imgAnh = (ImageView) findViewById(R.id.imgAnh);
        btnAnh = (ImageButton) findViewById(R.id.btnAnh);
        imgAnh.setTag(R.drawable.siesta_screen);
        

    }
}