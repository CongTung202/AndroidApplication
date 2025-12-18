package com.example.quanlynhanvien.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quanlynhanvien.model.NhanVien;
import com.example.quanlynhanvien.model.PhongBan;
import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "QuanLyNhanSu.db";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 1. Tạo bảng Phòng Ban
        String sqlPB = "CREATE TABLE PhongBan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT)";
        db.execSQL(sqlPB);

        // 2. Tạo bảng Nhân Viên (Có cột avatar BLOB và khóa ngoại)
        String sqlNV = "CREATE TABLE NhanVien (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten TEXT, " +
                "sdt TEXT, " +
                "avatar BLOB, " +
                "id_phong INTEGER, " +
                "FOREIGN KEY(id_phong) REFERENCES PhongBan(id) ON DELETE CASCADE)";
        db.execSQL(sqlNV);

        // Thêm dữ liệu mẫu cho Phòng ban để dễ test
        db.execSQL("INSERT INTO PhongBan (ten) VALUES ('Phòng Kế Toán')");
        db.execSQL("INSERT INTO PhongBan (ten) VALUES ('Phòng Kỹ Thuật')");
        db.execSQL("INSERT INTO PhongBan (ten) VALUES ('Phòng Nhân Sự')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS PhongBan");
        onCreate(db);
    }

    // --- CÁC HÀM XỬ LÝ PHÒNG BAN ---

    public ArrayList<PhongBan> getAllPhongBan() {
        ArrayList<PhongBan> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhongBan", null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new PhongBan(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void addPhongBan(String tenPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", tenPhong);
        db.insert("PhongBan", null, values);
    }

    // Xóa phòng ban (Sẽ xóa luôn nhân viên thuộc phòng đó nhờ ON DELETE CASCADE)
    public void deletePhongBan(int idPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("PhongBan", "id=?", new String[]{String.valueOf(idPhong)});
    }

    // --- CÁC HÀM XỬ LÝ NHÂN VIÊN ---
    private NhanVien cursorToNhanVien(Cursor cursor) {
        NhanVien nv = new NhanVien(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("ten")),
                cursor.getString(cursor.getColumnIndexOrThrow("sdt")),
                cursor.getBlob(cursor.getColumnIndexOrThrow("avatar")),
                cursor.getInt(cursor.getColumnIndexOrThrow("id_phong"))
        );
        int tenPhongColIndex = cursor.getColumnIndex("ten_phong");
        if (tenPhongColIndex != -1) {
            nv.setTenPhong(cursor.getString(tenPhongColIndex));
        }
        return nv;
    }

    // Lấy nhân viên theo ID Phòng Ban (ĐÃ SỬA)
    public ArrayList<NhanVien> getNhanVienByPhong(int idPhong) {
        ArrayList<NhanVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT nv.*, pb.ten as ten_phong FROM NhanVien nv JOIN PhongBan pb ON nv.id_phong = pb.id WHERE nv.id_phong = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(idPhong)});
        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToNhanVien(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void addNhanVien(NhanVien nv) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ten", nv.getTenNV());
        values.put("sdt", nv.getSdt());
        values.put("avatar", nv.getHinhAnh());
        values.put("id_phong", nv.getIdPhong());
        db.insert("NhanVien", null, values);
    }

    public void deleteNhanVien(int idNV) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("NhanVien", "id=?", new String[]{String.valueOf(idNV)});
    }

    // Lấy tất cả nhân viên (ĐÃ SỬA)
    public ArrayList<NhanVien> getAllNhanVienWithTenPhong() {
        ArrayList<NhanVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT nv.*, pb.ten as ten_phong FROM NhanVien nv INNER JOIN PhongBan pb ON nv.id_phong = pb.id";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToNhanVien(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}