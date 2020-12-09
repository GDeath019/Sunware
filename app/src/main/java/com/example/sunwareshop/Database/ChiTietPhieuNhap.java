package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChiTietPhieuNhap extends RealmObject {
    @PrimaryKey
    private long id;
    private long ma_phieu_nhap;
    private long ma_san_pham;
    private Double gia_nhap;
    private long so_luong;

    public ChiTietPhieuNhap() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(long ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    public long getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(long ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    public Double getGia_nhap() {
        return gia_nhap;
    }

    public void setGia_nhap(Double gia_nhap) {
        this.gia_nhap = gia_nhap;
    }

    public long getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(long so_luong) {
        this.so_luong = so_luong;
    }
}
