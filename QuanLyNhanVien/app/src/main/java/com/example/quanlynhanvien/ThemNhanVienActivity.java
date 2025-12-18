package com.example.quanlynhanvien;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quanlynhanvien.database.DbHelper;
import com.example.quanlynhanvien.model.NhanVien;
import com.example.quanlynhanvien.model.PhongBan;
import com.example.quanlynhanvien.utils.DbBitmapUtility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ThemNhanVienActivity extends AppCompatActivity {

    private EditText edtTen, edtSdt;
    private Spinner spinnerPhongBan;
    private ImageView imgAvatar;
    private Button btnSave;
    private DbHelper dbHelper;
    private Bitmap selectedBitmap = null;
    private ArrayList<PhongBan> listPhongBan; // *** Biến toàn cục để chứa list phòng ban

    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        imgAvatar.setImageBitmap(selectedBitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Không thể tải ảnh", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        setTitle("Thêm Nhân Viên");

        dbHelper = new DbHelper(this);
        edtTen = findViewById(R.id.edtTenNV);
        edtSdt = findViewById(R.id.edtSdtNV);
        spinnerPhongBan = findViewById(R.id.spinnerPhongBan);
        imgAvatar = findViewById(R.id.imgAvatarInput);
        btnSave = findViewById(R.id.btnSave);

        // 1. Load danh sách phòng ban vào Spinner
        loadPhongBanIntoSpinner();

        // 2. Bắt sự kiện chọn ảnh
        imgAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        });

        // 3. Bắt sự kiện nút Lưu
        btnSave.setOnClickListener(v -> saveNhanVien());
    }

    private void loadPhongBanIntoSpinner() {
        listPhongBan = dbHelper.getAllPhongBan();
        List<String> tenPhongBanList = listPhongBan.stream()
                                                  .map(PhongBan::getTenPhong)
                                                  .collect(Collectors.toList());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenPhongBanList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPhongBan.setAdapter(spinnerAdapter);

        // Nhận ID phòng ban mặc định và set cho Spinner
        int idPhongDefault = getIntent().getIntExtra("ID_PHONG_DEFAULT", -1);
        if (idPhongDefault != -1) {
            for (int i = 0; i < listPhongBan.size(); i++) {
                if (listPhongBan.get(i).getId() == idPhongDefault) {
                    spinnerPhongBan.setSelection(i);
                    break;
                }
            }
        }
    }

    private void saveNhanVien() {
        String ten = edtTen.getText().toString().trim();
        String sdt = edtSdt.getText().toString().trim();
        int selectedSpinnerPosition = spinnerPhongBan.getSelectedItemPosition();

        if (ten.isEmpty() || sdt.isEmpty() || selectedSpinnerPosition < 0 || listPhongBan.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin và chọn phòng ban!", Toast.LENGTH_SHORT).show();
            return;
        }

        // *** SỬA LỖI TẠI ĐÂY ***
        // Lấy ID phòng ban từ vị trí spinner đã chọn thông qua list toàn cục
        PhongBan selectedPhongBan = listPhongBan.get(selectedSpinnerPosition);
        int idPhong = selectedPhongBan.getId();

        byte[] avatar = null;
        if (selectedBitmap != null) {
            avatar = DbBitmapUtility.getBytes(selectedBitmap);
        }

        NhanVien newNhanVien = new NhanVien(0, ten, sdt, avatar, idPhong);
        dbHelper.addNhanVien(newNhanVien);

        Toast.makeText(this, "Đã lưu nhân viên: " + ten, Toast.LENGTH_SHORT).show();
        finish(); // Đóng activity sau khi lưu
    }
}
