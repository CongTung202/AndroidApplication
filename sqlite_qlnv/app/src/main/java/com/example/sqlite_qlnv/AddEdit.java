package com.example.sqlite_qlnv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sqlite_qlnv.dao.PersonDao;
import com.example.sqlite_qlnv.model.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEdit extends AppCompatActivity {
    TextView txtTitle;
    EditText edtId, edtName, edtBirthday;
    RadioButton radMale, radFemale;
    Button btnSave;
    PersonDao dao;
    int id;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        // Ẩn Action Bar nếu muốn
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        addViews();
        addEvents();
    }

    private void addViews() {
        txtTitle = findViewById(R.id.txtTitle);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtBirthday = findViewById(R.id.edtBirthday);
        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);
        btnSave = findViewById(R.id.btnSave);
        dao = new PersonDao(this);

        // Lấy Intent
        id = getIntent().getIntExtra("id", -1);

        if (id == -1) {
            txtTitle.setText("Thêm mới nhân viên");
            edtId.setEnabled(true); // Cho phép nhập ID khi thêm mới
        } else {
            txtTitle.setText("Sửa nhân viên");
            edtId.setEnabled(false); // Không cho sửa ID (Khóa chính)
            loadData(id);
        }
    }

    private void loadData(int id) {
        Person p = dao.getById(id);
        edtId.setText(String.valueOf(p.getId()));
        edtName.setText(p.getName());
        edtBirthday.setText(sdf.format(p.getBirthday()));

        // FIX LỖI LOGIC: So sánh chuỗi bằng equals
        if (p.getGender() != null && p.getGender().equals("Nam"))
            radMale.setChecked(true);
        else
            radFemale.setChecked(true);
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Person p = new Person();
                    p.setId(Integer.parseInt(edtId.getText().toString()));
                    p.setName(edtName.getText().toString());

                    Date birthday;
                    try {
                        birthday = sdf.parse(edtBirthday.getText().toString());
                    } catch (Exception e) {
                        birthday = new Date(); // Mặc định hôm nay nếu lỗi
                    }
                    p.setBirthday(birthday);

                    String gender = radMale.isChecked() ? "Nam" : "Nữ";
                    p.setGender(gender);

                    if (id == -1) {
                        dao.insert(p);
                    } else {
                        dao.update(p);
                    }
                    finish(); // Đóng activity quay về Main
                } catch (NumberFormatException e) {
                    edtId.setError("Vui lòng nhập ID số");
                }
            }
        });
    }
}