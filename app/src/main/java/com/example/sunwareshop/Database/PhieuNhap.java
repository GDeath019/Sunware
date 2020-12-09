package com.example.sunwareshop.Database;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PhieuNhap extends RealmObject {
    @PrimaryKey
    private long ma_phieu_nhap;
    private long ma_nhan_vien;
    private long ma_ncc;
    private Date ngay_nhap;
    private Double tong_tien;
    private String ghi_chu;

    public PhieuNhap() {
    }

    public long getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(long ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    public long getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(long ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public long getMa_ncc() {
        return ma_ncc;
    }

    public void setMa_ncc(long ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public Date getNgay_nhap() {
        return ngay_nhap;
    }

    public void setNgay_nhap(Date ngay_nhap) {
        this.ngay_nhap = ngay_nhap;
    }

    public Double getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(Double tong_tien) {
        this.tong_tien = tong_tien;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }
}
