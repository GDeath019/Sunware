package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.IMEI;
import com.example.sunwareshop.Database.PhieuNhap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelImei extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyIMEI;
    Realm getData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initRealM();
        getData = Realm.getDefaultInstance();
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

    public String insertIMEI(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyIMEI.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                IMEI dbRealm = backgroundRm.createObject(IMEI.class, DbKey);
                String sss = "1";
                dbRealm.setMa_san_pham(Integer.parseInt(sss.toString()));
                dbRealm.setMa_phieu_nhap(Integer.parseInt(sss.toString()));
                dbRealm.setMa_hoa_don(Integer.parseInt(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateIMEI(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                IMEI dbRealm = realm.where(IMEI.class).equalTo("IMEI", tg).findFirst();
                String sss = "9999";

                dbRealm.setMa_phieu_nhap(Integer.parseInt(sss.toString()));
                dbRealm.setMa_san_pham(Integer.parseInt(sss.toString()));
                dbRealm.setMa_hoa_don(Integer.parseInt(sss.toString()));
            }
        });
        return "nope";
    }

    public String deleteIMEI(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<IMEI> results = realm1.where(IMEI.class).equalTo("IMEI",dlt).findAll();
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
            List<IMEI> dbRealmList = getAll(realm);
            KeyIMEI = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getIMEI()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyIMEI.getAndIncrement();
            ArrayList<IMEI> data = new ArrayList<>();
            data.add(new IMEI(1, 1,1,1));
            data.add(new IMEI(2, 2,2,2));
            data.add(new IMEI(3, 3,3,3));
            data.add(new IMEI(4, 4,4,4));
            data.add(new IMEI(5, 5,5,5));
            data.add(new IMEI(6, 6,6,6));
            data.add(new IMEI(7, 7,7,7));
            data.add(new IMEI(8, 8,8,8));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        IMEI dbRealm = backgroundRm.createObject(IMEI.class, finalDbKey);
                        dbRealm.setMa_san_pham(data.get(temp).getMa_san_pham());
                        dbRealm.setMa_hoa_don(data.get(temp).getMa_hoa_don());
                        dbRealm.setMa_phieu_nhap(data.get(temp).getMa_phieu_nhap());

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
            List<IMEI> dbRealmList = getAll(realm);
            KeyIMEI = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getIMEI()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            IMEI dbRealm = realm.createObject(IMEI.class,0);
            // set lại key auto 1 lần nữa
            List<IMEI> dbRealmList = getAll(realm);
            KeyIMEI = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getIMEI()+1);
            // xóa bảng tạm thời
            RealmResults<IMEI> results = realm.where(IMEI.class).equalTo("IMEI",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<IMEI> getAll(Realm passedInRealm){
        RealmResults<IMEI> realms = passedInRealm.where(IMEI.class).findAll();
        return realms;
    }

}
