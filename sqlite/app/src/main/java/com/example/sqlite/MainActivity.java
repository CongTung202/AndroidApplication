package com.example.sqlite;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sqlite.adapter.PersonAdapter;
import com.example.sqlite.model.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView lvPerson;
    private PersonAdapter personAdapter;
    private static final List<Person> personList = new ArrayList<>();

    private static final int ADD_PERSON_REQUEST = 1;
    private static final int EDIT_PERSON_REQUEST = 2;
    public static final int RESULT_DELETE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvPerson = findViewById(R.id.lvPerson);

        if (personList.isEmpty()) {
            addSampleData();
        }

        personAdapter = new PersonAdapter(this, personList);
        lvPerson.setAdapter(personAdapter);

        lvPerson.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, AddEdit.class);
            intent.putExtra("PERSON_DATA", personList.get(position));
            intent.putExtra("PERSON_POSITION", position);
            startActivityForResult(intent, EDIT_PERSON_REQUEST);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(this, AddEdit.class);
            startActivityForResult(intent, ADD_PERSON_REQUEST);
            return true;
        } else if (id == R.id.action_sort) {
            sortByName();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;
        Person personResult = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            personResult = data.getParcelableExtra("PERSON_RESULT", Person.class);
        } else {
            personResult = data.getParcelableExtra("PERSON_RESULT");
        }

        if (requestCode == ADD_PERSON_REQUEST && resultCode == RESULT_OK) {
            if (personResult != null) {
                // Assign a new ID
                int nextId = personList.isEmpty() ? 1 : Collections.max(personList, (p1, p2) -> Integer.compare(p1.getId(), p2.getId())).getId() + 1;
                personResult.setId(nextId);
                personList.add(personResult);
                personAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == EDIT_PERSON_REQUEST) {
            int position = data.getIntExtra("PERSON_POSITION", -1);
            if (position == -1) return;

            if (resultCode == RESULT_OK) {
                if (personResult != null) {
                    personList.set(position, personResult);
                    personAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Đã cập nhật!", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_DELETE) {
                personList.remove(position);
                personAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Đã xóa!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sortByName() {
        Collections.sort(personList, (p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        personAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã sắp xếp theo tên", Toast.LENGTH_SHORT).show();
    }

    private void addSampleData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            personList.add(new Person(1, "Nguyen Van C", sdf.parse("01/01/2000"), "Nam"));
            personList.add(new Person(2, "Nguyen Van A", sdf.parse("01/01/2000"), "Nam"));
            personList.add(new Person(3, "Nguyen Van B", sdf.parse("01/01/2000"), "Nam"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
