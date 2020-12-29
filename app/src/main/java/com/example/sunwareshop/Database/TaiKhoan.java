package com.example.sunwareshop.Database;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TaiKhoan extends RealmObject {
    @PrimaryKey
    private long id;
    private String email;
    private Date ngay_lap;
    private String mat_khau;
    private String ho_ten;
    private String dia_chi;
    private String sdt;
    private long hinh_anh;
    private long loai_tai_khoan;

    public TaiKhoan() {
    }

    public TaiKhoan(long id, String email, Date ngay_lap, String mat_khau, String ho_ten, String dia_chi, String sdt, long hinh_anh, long loai_tai_khoan) {
        this.id = id;
        this.email = email;
        this.ngay_lap = ngay_lap;
        this.mat_khau = mat_khau;
        this.ho_ten = ho_ten;
        this.dia_chi = dia_chi;
        this.sdt = sdt;
        this.hinh_anh = hinh_anh;
        this.loai_tai_khoan = loai_tai_khoan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgay_lap() {
        return ngay_lap;
    }

    public void setNgay_lap(Date ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public String getMat_khau() {
        return mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        this.mat_khau = mat_khau;
    }

    public String getHo_ten() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
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

    public long getLoai_tai_khoan() {
        return loai_tai_khoan;
    }

    public void setLoai_tai_khoan(long loai_tai_khoan) {
        this.loai_tai_khoan = loai_tai_khoan;
    }
}
