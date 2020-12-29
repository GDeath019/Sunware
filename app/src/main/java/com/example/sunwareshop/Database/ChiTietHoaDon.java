package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChiTietHoaDon extends RealmObject {
    @PrimaryKey
    private long id;
    private long ma_hoa_don;
    private long ma_san_pham;
    private double gia_ban;
    private long so_luong;
    private long bao_hanh;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(long id, long ma_hoa_don, long ma_san_pham, double gia_ban, long so_luong, long bao_hanh) {
        this.id = id;
        this.ma_hoa_don = ma_hoa_don;
        this.ma_san_pham = ma_san_pham;
        this.gia_ban = gia_ban;
        this.so_luong = so_luong;
        this.bao_hanh = bao_hanh;
    }

    public long getBao_hanh() {
        return bao_hanh;
    }

    public void setBao_hanh(long bao_hanh) {
        this.bao_hanh = bao_hanh;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMa_hoa_don() {
        return ma_hoa_don;
    }

    public void setMa_hoa_don(long ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }

    public long getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(long ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
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
