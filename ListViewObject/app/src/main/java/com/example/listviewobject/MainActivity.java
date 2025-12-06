package com.example.listviewobject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listviewobject.adapter.PersonAdapter;
import com.example.listviewobject.model.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtDateOfBirth;
    private Spinner spnGender;
    private Button btnAdd, btnEdit, btnDel, btnClear;
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
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        spnGender = findViewById(R.id.spnGender);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        btnClear = findViewById(R.id.btnClear);
        lvPerson = findViewById(R.id.lvPerson);
    }

    private void initData() {
        personList = new ArrayList<>();
        personList.add(new Person("1", "BokaChan", "Nam", "20/02/2005"));
        personList.add(new Person("2", "Lina", "Nữ", "01/01/2005"));
        personList.add(new Person("3", "Speyin", "Khác", "11/05/2005"));

        adapter = new PersonAdapter(this, R.layout.list_item_person, personList);
        lvPerson.setAdapter(adapter);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGender.setAdapter(genderAdapter);
    }

    private void addEvents() {
        btnAdd.setOnClickListener(v -> addPerson());
        btnEdit.setOnClickListener(v -> editPerson());
        btnDel.setOnClickListener(v -> deletePerson());
        btnClear.setOnClickListener(v -> {
            clearFields();
            selectedPosition = -1;
            adapter.setSelectedPosition(-1);
        });

        lvPerson.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            adapter.setSelectedPosition(position);

            Person person = personList.get(position);
            edtId.setText(person.getId());
            edtName.setText(person.getName());
            edtDateOfBirth.setText(person.getDateOfBirth());

            String[] genderArray = getResources().getStringArray(R.array.gender_array);
            int genderPosition = Arrays.asList(genderArray).indexOf(person.getGender());
            spnGender.setSelection(genderPosition);
        });
    }

    private void addPerson() {
        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String dateOfBirth = edtDateOfBirth.getText().toString().trim();
        String gender = spnGender.getSelectedItem().toString();

        if (id.isEmpty() || name.isEmpty() || dateOfBirth.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Person person = new Person(id, name, gender, dateOfBirth);
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
        String dateOfBirth = edtDateOfBirth.getText().toString().trim();
        String gender = spnGender.getSelectedItem().toString();

        if (id.isEmpty() || name.isEmpty() || dateOfBirth.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Person person = personList.get(selectedPosition);
        person.setId(id);
        person.setName(name);
        person.setGender(gender);
        person.setDateOfBirth(dateOfBirth);

        adapter.notifyDataSetChanged();
        clearFields();
        selectedPosition = -1; // Reset selection
        adapter.setSelectedPosition(-1); // Clear selection in adapter
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
        adapter.setSelectedPosition(-1); // Clear selection in adapter
    }

    private void clearFields() {
        edtId.setText("");
        edtName.setText("");
        edtDateOfBirth.setText("");
        spnGender.setSelection(0);
    }
}
