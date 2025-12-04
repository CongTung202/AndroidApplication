package com.example.listviewobject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listviewobject.adapter.PersonAdapter;
import com.example.listviewobject.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtTuoi;
    private RadioGroup ragGender;
    private RadioButton rbNam, rbNu, rbKhac;
    private Button btnAdd, btnEdit, btnDel;
    private ListView lvPerson;

    private PersonAdapter adapter;
    private ArrayList<Person> personList;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addViews();
        initData();
        addEvents();
    }

    private void addViews() {
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtTuoi = findViewById(R.id.edtTuoi);
        ragGender = findViewById(R.id.ragGender);
        rbNam = findViewById(R.id.rbNam);
        rbNu = findViewById(R.id.rbNu);
        rbKhac = findViewById(R.id.rbKhac);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        lvPerson = findViewById(R.id.lvPerson);
    }

    private void initData() {
        personList = new ArrayList<>();
        personList.add(new Person("1", "BokaChan", "Nam", "20"));
        personList.add(new Person("2", "Lina", "Nữ", "22"));
        personList.add(new Person("3", "Trien", "Khác", "21"));

        adapter = new PersonAdapter(this, R.layout.list_item_person, personList);
        lvPerson.setAdapter(adapter);
    }

    private void addEvents() {
        btnAdd.setOnClickListener(v -> addPerson());
        btnEdit.setOnClickListener(v -> editPerson());
        btnDel.setOnClickListener(v -> deletePerson());

        lvPerson.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            Person person = personList.get(position);
            edtId.setText(person.getId());
            edtName.setText(person.getName());
            edtTuoi.setText(person.getAge());

            if (person.getGender().equalsIgnoreCase("Nam")) {
                rbNam.setChecked(true);
            } else if (person.getGender().equalsIgnoreCase("Nữ")) {
                rbNu.setChecked(true);
            } else {
                rbKhac.setChecked(true);
            }
        });
    }

    private void addPerson() {
        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String age = edtTuoi.getText().toString().trim();
        String gender = getSelectedGender();

        if (id.isEmpty() || name.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Person person = new Person(id, name, gender, age);
        personList.add(person);
        adapter.notifyDataSetChanged();
        clearFields();
    }

    private void editPerson() {
        if (selectedPosition == -1) {
            Toast.makeText(this, "Vui lòng chọn một người để sửa", Toast.LENGTH_SHORT).show();
            return;
        }

        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String age = edtTuoi.getText().toString().trim();
        String gender = getSelectedGender();

        if (id.isEmpty() || name.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Person person = personList.get(selectedPosition);
        person.setId(id);
        person.setName(name);
        person.setGender(gender);
        person.setAge(age);

        adapter.notifyDataSetChanged();
        clearFields();
        selectedPosition = -1; // Reset selection
    }

    private void deletePerson() {
        if (selectedPosition == -1) {
            Toast.makeText(this, "Vui lòng chọn một người để xóa", Toast.LENGTH_SHORT).show();
            return;
        }

        personList.remove(selectedPosition);
        adapter.notifyDataSetChanged();
        clearFields();
        selectedPosition = -1; // Reset selection
    }

    private String getSelectedGender() {
        int selectedId = ragGender.getCheckedRadioButtonId();
        if (selectedId == R.id.rbNam) {
            return "Nam";
        } else if (selectedId == R.id.rbNu) {
            return "Nữ";
        } else if (selectedId == R.id.rbKhac) {
            return "Khác";
        }
        return "";
    }

    private void clearFields() {
        edtId.setText("");
        edtName.setText("");
        edtTuoi.setText("");
        ragGender.clearCheck();
    }
}
