package com.example.sqlite;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlite.dao.PersonDao;
import com.example.sqlite.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lvPerson;
    ArrayAdapter<Person> adapter;
    PersonDao dao;

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
        addViews();
    }

    private void addViews() {
        lvPerson = findViewById(R.id.lvPerson);
        dao = new PersonDao(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getAll());
        lvPerson.setAdapter(adapter);
        //ADD temporary data
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
        try{
            dao.insert(new Person(1, "Nguyen Van A", sdf.parse("01/01/2000"), "Nam"));
            dao.insert(new Person(2, "Nguyen Van B", sdf.parse("01/01/2000"), "Nam"));
            dao.insert(new Person(3, "Nguyen Van C", sdf.parse("01/01/2000"), "Nam"));
        }catch (Exception e){
                throw new RuntimeException(e);
        }


    }
}