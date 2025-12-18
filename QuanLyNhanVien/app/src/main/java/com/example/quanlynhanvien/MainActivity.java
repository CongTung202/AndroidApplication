package com.example.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnQuanLyPhongBan;
    private Button btnQuanLyNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnQuanLyPhongBan = findViewById(R.id.btnQuanLyPhongBan);
        btnQuanLyNhanVien = findViewById(R.id.btnQuanLyNhanVien);

        btnQuanLyPhongBan.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuanLyPhongBanActivity.class);
            startActivity(intent);
        });

        btnQuanLyNhanVien.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DanhSachNhanVienActivity.class);
            intent.putExtra("ID_PHONG", -1); // ID -1 để báo hiệu rằng chúng ta muốn xem tất cả nhân viên
            intent.putExtra("TEN_PHONG", "Tất cả nhân viên"); // Đặt tiêu đề chung
            startActivity(intent);
        });
    }
}
