package com.example.lab04_2310900051;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Bai31Activity extends AppCompatActivity {

    private EditText edtLoginUsername, edtLoginPassword;
    private Button btnLogin, btnGoToRegister;

    private String registeredUsername;
    private String registeredPassword;

    private final ActivityResultLauncher<Intent> registerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Bundle bundle = data.getExtras();
                            registeredUsername = bundle.getString("username");
                            registeredPassword = bundle.getString("password");
                            // Optional: Fill the login form with the new credentials
                            edtLoginUsername.setText(registeredUsername);
                            edtLoginPassword.setText(registeredPassword);
                            Toast.makeText(Bai31Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai31);

        edtLoginUsername = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bai31Activity.this, Bai32Activity.class);
                registerLauncher.launch(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtLoginUsername.getText().toString().trim();
                String password = edtLoginPassword.getText().toString().trim();

                if (registeredUsername == null || registeredPassword == null) {
                    Toast.makeText(Bai31Activity.this, "Vui lòng đăng ký tài khoản trước", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                    Toast.makeText(Bai31Activity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Bai31Activity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
