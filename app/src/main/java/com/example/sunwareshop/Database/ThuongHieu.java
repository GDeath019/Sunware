package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ThuongHieu extends RealmObject {
    @PrimaryKey
    private long ma_thuong_hieu;
    private String ten_thuong_hieu;

    public ThuongHieu() {
    }

    public ThuongHieu(long ma_thuong_hieu, String ten_thuong_hieu) {
        this.ma_thuong_hieu = ma_thuong_hieu;
        this.ten_thuong_hieu = ten_thuong_hieu;
    }

    public long getMa_thuong_hieu() {
        return ma_thuong_hieu;
    }

    public void setMa_thuong_hieu(long ma_thuong_hieu) {
        this.ma_thuong_hieu = ma_thuong_hieu;
    }

    public String getTen_thuong_hieu() {
        return ten_thuong_hieu;
    }

    public void setTen_thuong_hieu(String ten_thuong_hieu) {
        this.ten_thuong_hieu = ten_thuong_hieu;
    }
}
