package com.example.sunwareshop.Database;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HoaDon extends RealmObject {
    @PrimaryKey
    private long ma_hoa_don;
    private long ma_nhan_vien;
    private long ma_khach_hang;
    private Date ngay_lap;
    private long loai_don;
    private String trang_thai;
    private Double tong_tien;

    public HoaDon() {
    }

    public long getMa_hoa_don() {
        return ma_hoa_don;
    }

    public void setMa_hoa_don(long ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }

    public long getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(long ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public long getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(long ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    public Date getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(Date ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public long getLoai_don() {
        return loai_don;
    }

    public void setLoai_don(long loai_don) {
        this.loai_don = loai_don;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public Double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Double tong_tien) {
        this.tong_tien = tong_tien;
    }
}
