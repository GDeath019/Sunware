package com.example.sunwareshop.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class IMEI extends RealmObject {
    @PrimaryKey
    private long IMEI;
    private long ma_san_pham;
    private long ma_phieu_nhap;
    private long ma_hoa_don;

    public IMEI() {
    }

    public IMEI(long IMEI, long ma_san_pham, long ma_phieu_nhap, long ma_hoa_don) {
        this.IMEI = IMEI;
        this.ma_san_pham = ma_san_pham;
        this.ma_phieu_nhap = ma_phieu_nhap;
        this.ma_hoa_don = ma_hoa_don;
    }

    public long getIMEI() {
        return IMEI;
    }

    public void setIMEI(long IMEI) {
        this.IMEI = IMEI;
    }

    public long getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(long ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    public long getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(long ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    public long getMa_hoa_don() {
        return ma_hoa_don;
    }

    public void setMa_hoa_don(long ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }
}
