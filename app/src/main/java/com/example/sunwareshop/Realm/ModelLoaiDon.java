package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.LoaiDon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelLoaiDon extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyLoaiDon;
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

    public String insertLoaiDon(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyLoaiDon.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                LoaiDon dbRealm = backgroundRm.createObject(LoaiDon.class, DbKey);
                String sss = "1";
                dbRealm.setMo_ta(sss.toString());
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateLoaiDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LoaiDon dbRealm = realm.where(LoaiDon.class).equalTo("ma_loai_don", tg).findFirst();
                String sss = "9999";

                dbRealm.setMo_ta(sss.toString());
            }
        });
        return "nope";
    }

    public String deleteLoaiDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<LoaiDon> results = realm1.where(LoaiDon.class).equalTo("ma_loai_don",dlt).findAll();
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
            List<LoaiDon> dbRealmList = getAll(realm);
            KeyLoaiDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_don()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyLoaiDon.getAndIncrement();
            ArrayList<LoaiDon> data = new ArrayList<>();
            data.add(new LoaiDon(1, "Trực tiếp"));
            data.add(new LoaiDon(2, "Online"));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        LoaiDon dbRealm = backgroundRm.createObject(LoaiDon.class, finalDbKey);

                        dbRealm.setMo_ta(data.get(temp).getMo_ta());
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
            List<LoaiDon> dbRealmList = getAll(realm);
            KeyLoaiDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_don()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            LoaiDon dbRealm = realm.createObject(LoaiDon.class,0);
            // set lại key auto 1 lần nữa
            List<LoaiDon> dbRealmList = getAll(realm);
            KeyLoaiDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_don()+1);
            // xóa bảng tạm thời
            RealmResults<LoaiDon> results = realm.where(LoaiDon.class).equalTo("ma_loai_don",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<LoaiDon> getAll(Realm passedInRealm){
        RealmResults<LoaiDon> realms = passedInRealm.where(LoaiDon.class).findAll();
        return realms;
    }
}
