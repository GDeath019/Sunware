package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BaoHanh extends RealmObject {
    @PrimaryKey
    private long ma_bao_hanh;
    private long thoi_gian;

    public BaoHanh() {
    }

    public BaoHanh(long ma_bao_hanh, long thoi_gian) {
        this.ma_bao_hanh = ma_bao_hanh;
        this.thoi_gian = thoi_gian;
    }

    public long getMa_bao_hanh() {
        return ma_bao_hanh;
    }

    public void setMa_bao_hanh(long ma_bao_hanh) {
        this.ma_bao_hanh = ma_bao_hanh;
    }

    public long getThoi_gian() {
        return thoi_gian;
    }

    public void setThoi_gian(long thoi_gian) {
        this.thoi_gian = thoi_gian;
    }
}
