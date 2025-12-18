package com.example.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlynhanvien.adapter.NhanVienAdapter;
import com.example.quanlynhanvien.database.DbHelper;
import com.example.quanlynhanvien.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class DanhSachNhanVienActivity extends AppCompatActivity {

    DbHelper dbHelper;
    RecyclerView rvNhanVien;
    NhanVienAdapter adapter;
    ArrayList<NhanVien> listNhanVien = new ArrayList<>(); // Khởi tạo list ở đây
    int idPhongHienTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nhan_vien);

        idPhongHienTai = getIntent().getIntExtra("ID_PHONG", -1);
        String tenPhong = getIntent().getStringExtra("TEN_PHONG");
        setTitle(tenPhong != null ? tenPhong : "Tất cả nhân viên");

        dbHelper = new DbHelper(this);
        rvNhanVien = findViewById(R.id.rvNhanVien);
        rvNhanVien.setLayoutManager(new LinearLayoutManager(this));

        // Tạo adapter một lần
        adapter = new NhanVienAdapter(this, listNhanVien, nv -> {
            dbHelper.deleteNhanVien(nv.getId());
            loadData();
        });
        rvNhanVien.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddNhanVien);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThemNhanVienActivity.class);
            if (idPhongHienTai != -1) { // Chỉ truyền ID nếu đang xem 1 phòng cụ thể
               intent.putExtra("ID_PHONG_DEFAULT", idPhongHienTai);
            }
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        listNhanVien.clear(); // Xóa dữ liệu cũ
        if (idPhongHienTai == -1) {
            // Xem tất cả nhân viên
            listNhanVien.addAll(dbHelper.getAllNhanVienWithTenPhong());
        } else {
            // Xem nhân viên theo phòng ban
            listNhanVien.addAll(dbHelper.getNhanVienByPhong(idPhongHienTai));
        }
        adapter.notifyDataSetChanged(); // Báo cho adapter biết dữ liệu đã thay đổi
    }
}