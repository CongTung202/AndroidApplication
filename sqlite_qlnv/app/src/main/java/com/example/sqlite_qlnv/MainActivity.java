package com.example.sqlite_qlnv;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sqlite_qlnv.adapter.PersonAdapter;
import com.example.sqlite_qlnv.dao.PersonDao;
import com.example.sqlite_qlnv.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jspecify.annotations.NonNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvPerson;
    PersonAdapter adapter;
    PersonDao dao;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản lý nhân viên");


        addViews();
        addEvents();
    }

    private void addViews() {
        lvPerson = findViewById(R.id.lvPerson);
        loadDataToListview();
        registerForContextMenu(lvPerson);
    }

    private void addEvents() {
        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                return false;
            }
        });
    }

    private void loadDataToListview() {
        dao = new PersonDao(this);
        List<Person> list = dao.getAll();
        adapter = new PersonAdapter(this, R.layout.item_person, list);
        lvPerson.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataToListview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            startActivity(new Intent(this, AddEdit.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Sửa");
        menu.add(0, 2, 1, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info != null) selectedPosition = info.position;

        if (item.getItemId() == 1) { // Sửa
            Person p = adapter.getItem(selectedPosition);
            Intent intent = new Intent(this, AddEdit.class);
            intent.putExtra("id", p.getId());
            startActivity(intent);
        } else if (item.getItemId() == 2) { // Xóa
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn thật sự muốn xóa?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Person p = adapter.getItem(selectedPosition);
                            dao.delete(p.getId());
                            loadDataToListview();
                            Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Không", null).show();
        }
        return super.onContextItemSelected(item);
    }
}
