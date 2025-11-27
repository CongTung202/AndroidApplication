package com.example.simplespinner;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spnArray,spnList,spnResource;
    ArrayAdapter<String> apapter_array;
    ArrayAdapter<Integer> apapter_list;
    ArrayAdapter<String> apapter_resource;

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
        spnArray = findViewById(R.id.spnArray);
        String[] arr=new String[]{"Ha Noi","Nhinh Binh","NewYork","XiaoPiao"};
        apapter_array=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arr);
        spnArray.setAdapter(apapter_array);

        spnList = findViewById(R.id.spnList);
        List<Integer> list=new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(9);
        apapter_list=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        spnList.setAdapter(apapter_list);

        spnResource = findViewById(R.id.spnResource);
        String[] array=new String[]{"Thien","Ly","Oi","J97"};
        apapter_resource=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,array);
        spnResource.setAdapter(apapter_resource);

    }
}