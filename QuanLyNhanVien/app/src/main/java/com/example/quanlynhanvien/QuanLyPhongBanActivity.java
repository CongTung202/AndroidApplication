package com.example.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlynhanvien.adapter.PhongBanAdapter;
import com.example.quanlynhanvien.database.DbHelper;
import com.example.quanlynhanvien.model.PhongBan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class QuanLyPhongBanActivity extends AppCompatActivity {

    DbHelper dbHelper;
    RecyclerView rvPhongBan;
    PhongBanAdapter adapter;
    ArrayList<PhongBan> listPhongBan = new ArrayList<>(); // Khởi tạo list ở đây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_phong_ban);

        setTitle("Quản Lý Phòng Ban");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DbHelper(this);
        rvPhongBan = findViewById(R.id.rvPhongBan);
        rvPhongBan.setLayoutManager(new LinearLayoutManager(this));

        // Tạo adapter một lần
        adapter = new PhongBanAdapter(this, listPhongBan, new PhongBanAdapter.OnPhongBanClickListener() {
            @Override
            public void onItemClick(PhongBan pb) {
                Intent intent = new Intent(QuanLyPhongBanActivity.this, DanhSachNhanVienActivity.class);
                intent.putExtra("ID_PHONG", pb.getId());
                intent.putExtra("TEN_PHONG", pb.getTenPhong());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(PhongBan pb) {
                new AlertDialog.Builder(QuanLyPhongBanActivity.this)
                        .setTitle("Cảnh báo")
                        .setMessage("Xóa phòng này sẽ xóa hết nhân viên bên trong. Bạn chắc chứ?")
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            dbHelper.deletePhongBan(pb.getId());
                            loadData();
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });
        rvPhongBan.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAddPhongBan);
        fab.setOnClickListener(v -> showDialogAddPhongBan());

        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void loadData() {
        listPhongBan.clear(); // Xóa dữ liệu cũ
        listPhongBan.addAll(dbHelper.getAllPhongBan()); // Thêm dữ liệu mới
        adapter.notifyDataSetChanged(); // Báo cho adapter biết dữ liệu đã thay đổi
    }

    private void showDialogAddPhongBan() {
        EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Thêm Phòng Ban")
                .setView(input)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String ten = input.getText().toString();
                    if (!ten.isEmpty()) {
                        dbHelper.addPhongBan(ten);
                        loadData(); // Tải lại dữ liệu sau khi thêm
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}
