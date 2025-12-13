package com.example.sqlite;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEdit extends AppCompatActivity {

    private EditText edtName, edtBirthday;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Button btnSave, btnDelete;

    private Person currentPerson;
    private boolean isEditMode = false;
    private int personPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        edtName = findViewById(R.id.edtName);
        edtBirthday = findViewById(R.id.edtBirthday);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PERSON_DATA")) {
            isEditMode = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                currentPerson = intent.getParcelableExtra("PERSON_DATA", Person.class);
            } else {
                currentPerson = intent.getParcelableExtra("PERSON_DATA");
            }
            personPosition = intent.getIntExtra("PERSON_POSITION", -1);
            populateData(currentPerson);
        } else {
            isEditMode = false;
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(v -> savePerson());
        btnDelete.setOnClickListener(v -> deletePerson());
    }

    private void populateData(Person person) {
        if (person == null) return;
        edtName.setText(person.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        edtBirthday.setText(sdf.format(person.getBirthday()));
        if ("Nam".equalsIgnoreCase(person.getGender())) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
    }

    private void savePerson() {
        String name = edtName.getText().toString().trim();
        String birthdayStr = edtBirthday.getText().toString().trim();

        if (name.isEmpty() || birthdayStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date birthday = sdf.parse(birthdayStr);

            int id = isEditMode ? currentPerson.getId() : 0; // ID will be handled in MainActivity
            String gender = rbMale.isChecked() ? "Nam" : "Nữ";

            Person resultPerson = new Person(id, name, birthday, gender);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("PERSON_RESULT", resultPerson);
            resultIntent.putExtra("PERSON_POSITION", personPosition);

            setResult(RESULT_OK, resultIntent);
            finish();

        } catch (ParseException e) {
            Toast.makeText(this, "Định dạng ngày không hợp lệ (dd/MM/yyyy)", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePerson() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("PERSON_POSITION", personPosition);
        setResult(MainActivity.RESULT_DELETE, resultIntent);
        finish();
    }
}
