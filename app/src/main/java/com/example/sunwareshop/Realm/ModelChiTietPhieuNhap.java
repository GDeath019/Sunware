package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.ChiTietPhieuNhap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelChiTietPhieuNhap extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyCTPN;
    Realm getData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initRealM();
        getData = Realm.getDefaultInstance();
        addData();
    }
    public void initRealM() {
        Realm.init(Realm.getApplicationContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DbName)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
//      sét cấu hình cho app
        Realm.setDefaultConfiguration(configuration);
//      lấy mẫu cấu hình ra
        Realm realm = Realm.getInstance(configuration);

    }

    public String insertChiPhieuNhap(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyCTPN.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                ChiTietPhieuNhap dbRealm = backgroundRm.createObject(ChiTietPhieuNhap.class, DbKey);
                String sss = "1";
                dbRealm.setMa_phieu_nhap(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
                dbRealm.setGia_nhap(Double.parseDouble(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateChiTietPhieuNhap(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                ChiTietPhieuNhap dbRealm = realm.where(ChiTietPhieuNhap.class).equalTo("id", tg).findFirst();
                String sss = "9999";
                dbRealm.setMa_phieu_nhap(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
                dbRealm.setGia_nhap(Double.parseDouble(sss.toString()));
//                }
            }
        });
        return "nope";
    }

    public String deleteChiTietPhieuNhap(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<ChiTietPhieuNhap> results = realm1.where(ChiTietPhieuNhap.class).equalTo("id",dlt).findAll();
                if (results.size()==0){
                    check[0] = false;
                }else{
                    results.deleteFirstFromRealm();
                }
            }
        });
        if (check[0]==true){
            return "success";
        }else{
            return "fail";
        }
    }

    public void addData(){
        Realm realm = Realm.getDefaultInstance();
        try{
            List<ChiTietPhieuNhap> dbRealmList = getAll(realm);
            KeyCTPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyCTPN.getAndIncrement();
            ArrayList<ChiTietPhieuNhap> data = new ArrayList<>();
            data.add(new ChiTietPhieuNhap(1, 1, 1, 4500000.0, 100));
            data.add(new ChiTietPhieuNhap(2, 2, 2, 2000000.0, 100));
            data.add(new ChiTietPhieuNhap(3, 1, 3, 1700000.0, 111));
            data.add(new ChiTietPhieuNhap(4, 1, 4, 2000000.0, 123));
            data.add(new ChiTietPhieuNhap(5, 1, 2, 2000000.0, 135));
            data.add(new ChiTietPhieuNhap(6, 2, 3, 1700000.0, 50));
            data.add(new ChiTietPhieuNhap(7, 3, 5, 9000000.0, 60));
            data.add(new ChiTietPhieuNhap(8, 3, 6, 6200000.0, 72));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        ChiTietPhieuNhap dbRealm = backgroundRm.createObject(ChiTietPhieuNhap.class, finalDbKey);
                        dbRealm.setMa_phieu_nhap(data.get(temp).getMa_phieu_nhap());
                        dbRealm.setMa_san_pham(data.get(temp).getMa_san_pham());
                        dbRealm.setSo_luong(data.get(temp).getSo_luong());
                        dbRealm.setGia_nhap(data.get(temp).getGia_nhap());
                    }
                });
                DbKey++;
                n--;
            }
        }
        realm.close();
    }

    public void newKey(){
        Realm realm = Realm.getDefaultInstance();
        try{
            List<ChiTietPhieuNhap> dbRealmList = getAll(realm);
            KeyCTPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            ChiTietPhieuNhap dbRealm = realm.createObject(ChiTietPhieuNhap.class,0);
            // set lại key auto 1 lần nữa
            List<ChiTietPhieuNhap> dbRealmList = getAll(realm);
            KeyCTPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
            // xóa bảng tạm thời
            RealmResults<ChiTietPhieuNhap> results = realm.where(ChiTietPhieuNhap.class).equalTo("id",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<ChiTietPhieuNhap> getAll(Realm passedInRealm){
        RealmResults<ChiTietPhieuNhap> realms = passedInRealm.where(ChiTietPhieuNhap.class).findAll();
        return realms;
    }
}
