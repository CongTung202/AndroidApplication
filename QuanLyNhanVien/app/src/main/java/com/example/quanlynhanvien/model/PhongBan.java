package com.example.quanlynhanvien.model;
import java.io.Serializable;
public class PhongBan implements Serializable{
    private int id; // Phải là int, không được là String
    private String tenPhong;

    public PhongBan(int id, String tenPhong) {
        this.id = id;
        this.tenPhong = tenPhong;
    }

    // Constructor dùng khi thêm mới (chưa có ID)
    public PhongBan(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    // Quan trọng: Hàm này giúp Spinner hiển thị tên phòng thay vì địa chỉ bộ nhớ
    @Override
    public String toString() {
        return tenPhong;
    }
}