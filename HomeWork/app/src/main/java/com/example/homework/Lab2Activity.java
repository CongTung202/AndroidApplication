package com.example.homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Lab2Activity extends AppCompatActivity {

    private EditText editTextName, editTextMssv, editTextAge;
    private RadioGroup radioGroupGender;
    private CheckBox checkBoxSoccer, checkBoxGaming;
    private Button buttonSubmit;
    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2);

        // --- Cấu hình Toolbar ---
        Toolbar toolbar = findViewById(R.id.toolbar_lab2);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Lab2_PS23456");
        }

        // Ánh xạ view
        editTextName = findViewById(R.id.editTextName_lab2);
        editTextMssv = findViewById(R.id.editTextMssv_lab2);
        editTextAge = findViewById(R.id.editTextAge_lab2);
        radioGroupGender = findViewById(R.id.radioGroupGender_lab2);
        checkBoxSoccer = findViewById(R.id.checkBoxSoccer);
        checkBoxGaming = findViewById(R.id.checkBoxGaming);
        buttonSubmit = findViewById(R.id.buttonSubmit_lab2);
        textViewInfo = findViewById(R.id.textViewInfo);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInfo();
            }
        });
    }

    private void displayInfo() {
        // Lấy thông tin từ các trường nhập liệu
        String name = editTextName.getText().toString().trim();
        String mssv = editTextMssv.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        // Kiểm tra thông tin bắt buộc
        if (name.isEmpty() || mssv.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy giới tính
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String gender = selectedGenderRadioButton.getText().toString();

        // Lấy sở thích
        StringBuilder hobbies = new StringBuilder();
        if (checkBoxSoccer.isChecked()) {
            hobbies.append(checkBoxSoccer.getText().toString()).append(" ");
        }
        if (checkBoxGaming.isChecked()) {
            hobbies.append(checkBoxGaming.getText().toString());
        }

        String hobbiesStr = hobbies.toString().trim();
        if (hobbiesStr.isEmpty()) {
             Toast.makeText(this, "Vui lòng chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show();
            return;
        }


        // Xây dựng chuỗi thông tin
        String info = "Tôi tên: " + name + "\n"
                + "MSSV: " + mssv + "\n"
                + "Tuổi: " + age + "\n"
                + "Giới tính: " + gender + "\n"
                + "Sở thích: " + hobbiesStr;

        // Hiển thị thông tin
        textViewInfo.setText(info);
    }
}
