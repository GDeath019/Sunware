package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DacTrung extends RealmObject {
    @PrimaryKey
    private long ma_loai_dt;
    private String ten;
    private String mo_ta;

    public DacTrung() {
    }

    public long getMa_loai_dt() {
        return ma_loai_dt;
    }

    public void setMa_loai_dt(long ma_loai_dt) {
        this.ma_loai_dt = ma_loai_dt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
}
