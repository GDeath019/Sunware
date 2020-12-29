package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NhaCungCap extends RealmObject {
    @PrimaryKey
    private long ma_ncc;
    private String ten;
    private String dia_chi;
    private String hot_line;
    private String email;
    private String sdt;
    private long hinh_anh;

    public NhaCungCap() {
    }

    public NhaCungCap(long ma_ncc, String ten, String dia_chi, String hot_line, String email, String sdt, long hinh_anh) {
        this.ma_ncc = ma_ncc;
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.hot_line = hot_line;
        this.email = email;
        this.sdt = sdt;
        this.hinh_anh = hinh_anh;
    }

    public long getMa_ncc() {
        return ma_ncc;
    }

    public void setMa_ncc(long ma_ncc) {
        this.ma_ncc = ma_ncc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getHot_line() {
        return hot_line;
    }

    public void setHot_line(String hot_line) {
        this.hot_line = hot_line;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public long getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(long hinh_anh) {
        this.hinh_anh = hinh_anh;
    }
}
