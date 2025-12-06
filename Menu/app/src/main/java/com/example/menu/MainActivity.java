package com.example.menu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jspecify.annotations.NonNull;

public class MainActivity extends AppCompatActivity {

    ListView lvList;
    ArrayAdapter<String> adapter;

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
        lvList = findViewById(R.id.lvList);
        String[] arr=new String[]{"Boka","Chan","Ga","Oishi"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        lvList.setAdapter(adapter);
        registerForContextMenu(lvList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,0,"Them Moi");
        menu.add(1,2,1,"About");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1,1,0,"Sua");
        menu.add(1,2,1,"Xoa");
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==1){
            Toast.makeText(this,"Ban Chon Them Moi", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==2) {
            Toast.makeText(this, "Ban Chon About", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}