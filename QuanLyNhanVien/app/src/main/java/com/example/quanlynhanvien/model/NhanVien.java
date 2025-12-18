package com.example.quanlynhanvien.model;

public class NhanVien {
    private int id;
    private String tenNV;
    private String sdt;
    private byte[] hinhAnh; // Lưu ảnh dạng mảng byte
    private int idPhong;    // Khóa ngoại

    private String tenPhong;

    public NhanVien(int id, String tenPhong) {
        this.id = id;
        this.tenPhong = tenPhong;
    }

    public NhanVien(int id, String tenNV, String sdt, byte[] hinhAnh, int idPhong) {
        this.id = id;
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
        this.idPhong = idPhong;
    }

    // Constructor dùng khi thêm mới (chưa có ID)
    public NhanVien(String tenNV, String sdt, byte[] hinhAnh, int idPhong) {
        this.tenNV = tenNV;
        this.sdt = sdt;
        this.hinhAnh = hinhAnh;
        this.idPhong = idPhong;
    }

    public int getId() { return id; }
    public String getTenNV() { return tenNV; }
    public String getSdt() { return sdt; }
    public byte[] getHinhAnh() { return hinhAnh; }
    public int getIdPhong() { return idPhong; }

    public String getTenPhong() { return tenPhong; }
    public void setTenPhong(String tenPhong) { this.tenPhong = tenPhong; }
}