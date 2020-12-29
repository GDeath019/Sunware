package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoaiDon extends RealmObject {
    @PrimaryKey
    private long ma_loai_don;
    private String mo_ta;

    public LoaiDon() {
    }

    public LoaiDon(long ma_loai_don, String mo_ta) {
        this.ma_loai_don = ma_loai_don;
        this.mo_ta = mo_ta;
    }

    public long getMa_loai_don() {
        return ma_loai_don;
    }

    public void setMa_loai_don(long ma_loai_don) {
        this.ma_loai_don = ma_loai_don;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
}
