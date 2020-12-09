package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoaiSP extends RealmObject {
    @PrimaryKey
    private long ma_loai_sp;
    private String ten_loai_sp;
    private String mo_ta;

    public LoaiSP() {
    }

    public long getMa_loai_sp() {
        return ma_loai_sp;
    }

    public void setMa_loai_sp(long ma_loai_sp) {
        this.ma_loai_sp = ma_loai_sp;
    }

    public String getTen_loai_sp() {
        return ten_loai_sp;
    }

    public void setTen_loai_sp(String ten_loai_sp) {
        this.ten_loai_sp = ten_loai_sp;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
}
