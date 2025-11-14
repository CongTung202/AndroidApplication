package com.example.simplecontroller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Check_Radio_ToggleActivity extends AppCompatActivity {
    RadioButton radNam,radNu,radKhac;
    CheckBox chkDev,chkTravel,chkGame;
    ToggleButton tgbLamp1,tgbLamp2;
    Switch swtWifi,swtBluetooth;
    Button btnOutput;
    EditText edtOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_check_radio_toggle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        allControls();
        addEvents();
    }

    private void addEvents() {
        btnOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kq="Your Gender: ";
                if(radNam.isChecked())
                    kq+="Nam";
                else if(radNu.isChecked())
                    kq+="Nu";
                else if(radKhac.isChecked())
                    kq+="Khac";
                kq+="\nYour Hobbies: ";
                if(chkDev.isChecked())
                    kq+="Dev";
                if(chkTravel.isChecked())
                    kq+="Travel";
                if(chkGame.isChecked())
                    kq+="Game";
                kq+="\nYour Lamp: ";
                if(tgbLamp1.isChecked())
                    kq+="\nLamp LivingRoom turn on";
                if(tgbLamp2.isChecked())
                    kq+="\nLamp GuestRoom turn on";
                kq+="\nYour Wifi: ";
                if(swtWifi.isChecked())
                    kq+="On";
                else
                    kq+="Off";
                kq+="\nYour Bluetooth: ";
                if(swtBluetooth.isChecked())
                    kq+="On";
                else
                    kq+="Off";
                edtOutput.setText(kq);
            }
        });

    }

    private void allControls() {
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        radKhac = findViewById(R.id.radKhac);
        chkDev = findViewById(R.id.chkDev);
        chkTravel = findViewById(R.id.chkTravel);
        chkGame = findViewById(R.id.chkGame);
        tgbLamp1 = findViewById(R.id.tgbLamp1);
        tgbLamp2 = findViewById(R.id.tgbLamp2);
        swtWifi = findViewById(R.id.swtWifi);
        swtBluetooth = findViewById(R.id.swtBluetooth);
        btnOutput = findViewById(R.id.btnOutput);
        edtOutput = findViewById(R.id.edtOutput);

    }
}