package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {
    @PrimaryKey
    private long ma_san_pham;
    private long ma_thuong_hieu;
    private long ma_loai_san_pham;
    private String ten_san_pham;
    private double gia_ban;
    private long hinh_anh;
    private long so_luong;

    public Product() {
    }

    public long getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(long hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public long getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(long ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    public long getMa_thuong_hieu() {
        return ma_thuong_hieu;
    }

    public void setMa_thuong_hieu(long ma_thuong_hieu) {
        this.ma_thuong_hieu = ma_thuong_hieu;
    }

    public long getMa_loai_san_pham() {
        return ma_loai_san_pham;
    }

    public void setMa_loai_san_pham(long ma_loai_san_pham) {
        this.ma_loai_san_pham = ma_loai_san_pham;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public double getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(double gia_ban) {
        this.gia_ban = gia_ban;
    }

    public long getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(long so_luong) {
        this.so_luong = so_luong;
    }
}
