package com.example.lab04_2310900051;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Bai21Activity extends AppCompatActivity {

    private EditText edtItemName;
    private Button btnSend;
    private TextView txtPrice;

    private final ActivityResultLauncher<Intent> getQuote = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 1) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            Bundle bundle = intent.getExtras();
                            String priceQuote = bundle.getString("priceQuote");
                            txtPrice.setText(priceQuote);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai21);

        edtItemName = findViewById(R.id.edtItemName);
        btnSend = findViewById(R.id.btnSend);
        txtPrice = findViewById(R.id.txtPrice);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai21Activity.this, Bai22Activity.class);
                intent.putExtra("itemName", edtItemName.getText().toString());
                getQuote.launch(intent);
            }
        });
    }
}
