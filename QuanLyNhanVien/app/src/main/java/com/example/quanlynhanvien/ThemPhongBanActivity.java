package com.example.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlynhanvien.model.PhongBan;

public class ThemPhongBanActivity extends AppCompatActivity {

    // Bỏ etMaPhong vì ID tự động tăng
    private EditText etTenPhong;
    private Button btnLuuPhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong_ban);

        // etMaPhong = findViewById(R.id.etMaPhong); // Xóa hoặc ẩn dòng này
        etTenPhong = findViewById(R.id.etTenPhong);
        btnLuuPhong = findViewById(R.id.btnLuuPhong);

        btnLuuPhong.setOnClickListener(v -> {
            String tenPhong = etTenPhong.getText().toString().trim();

            if (tenPhong.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên phòng", Toast.LENGTH_SHORT).show();
                return;
            }

            // SỬA LỖI TẠI ĐÂY:
            // Chỉ truyền tên phòng, ID sẽ để Database tự lo
            PhongBan newPhongBan = new PhongBan(tenPhong);

            Intent resultIntent = new Intent();
            // LƯU Ý: Để dòng dưới chạy được, class PhongBan phải implements Serializable (Xem Bước 2)
            resultIntent.putExtra("NEW_PHONG_BAN", newPhongBan);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}