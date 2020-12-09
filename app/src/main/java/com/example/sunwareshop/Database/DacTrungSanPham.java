package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DacTrungSanPham extends RealmObject {
    @PrimaryKey
    private long id;
    private long ma_loai_dt;
    private long ma_san_pham;

    public DacTrungSanPham() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMa_loai_dt() {
        return ma_loai_dt;
    }

    public void setMa_loai_dt(long ma_loai_dt) {
        this.ma_loai_dt = ma_loai_dt;
    }

    public long getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(long ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }
}
