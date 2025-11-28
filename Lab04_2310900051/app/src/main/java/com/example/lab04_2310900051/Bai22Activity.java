package com.example.lab04_2310900051;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bai22Activity extends AppCompatActivity {

    private TextView txtItemName;
    private EditText edtPriceQuote;
    private Button btnQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai22);

        txtItemName = findViewById(R.id.txtItemName);
        edtPriceQuote = findViewById(R.id.edtPriceQuote);
        btnQuote = findViewById(R.id.btnQuote);

        // Get the item name from Bai21Activity
        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        txtItemName.setText(itemName);

        btnQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceQuote = edtPriceQuote.getText().toString();
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("priceQuote", priceQuote);
                resultIntent.putExtras(bundle);
                setResult(1, resultIntent);
                finish();
            }
        });
    }
}
