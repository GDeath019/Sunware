package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.DacTrung;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelDacTrung extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyDT;
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

    public String insertDacTrung(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyDT.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                DacTrung dbRealm = backgroundRm.createObject(DacTrung.class, DbKey);
                String sss = "1";
                dbRealm.setTen(sss.toString());
                dbRealm.setMo_ta(sss.toString());
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateDacTrung(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                DacTrung dbRealm = realm.where(DacTrung.class).equalTo("ma_loai_dt", tg).findFirst();
                String sss = "9999";
                dbRealm.setTen(sss.toString());
                dbRealm.setMo_ta(sss.toString());
//                }
            }
        });
        return "nope";
    }

    public String deleteDactrung(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<DacTrung> results = realm1.where(DacTrung.class).equalTo("ma_loai_dt",dlt).findAll();
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
            List<DacTrung> dbRealmList = getAll(realm);
            KeyDT = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_dt()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyDT.getAndIncrement();
            ArrayList<DacTrung> data = new ArrayList<>();
            data.add(new DacTrung(1, "Đen tuyền","mota"));
            data.add(new DacTrung(2, "Bạch kim","mota"));
            data.add(new DacTrung(3, "Sang trọng","mota"));
            data.add(new DacTrung(4, "Thể thao","mota"));
            data.add(new DacTrung(5, "Công nghệ mới","mota"));
            data.add(new DacTrung(6, "Tiết kiệm điện","mota"));
            data.add(new DacTrung(7, "Tốc độ nhanh","mota"));
            data.add(new DacTrung(8, "Âm thanh tốt","mota"));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        DacTrung dbRealm = backgroundRm.createObject(DacTrung.class, finalDbKey);
                        dbRealm.setTen(data.get(temp).getTen());
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
            List<DacTrung> dbRealmList = getAll(realm);
            KeyDT = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_dt()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            DacTrung dbRealm = realm.createObject(DacTrung.class,0);
            // set lại key auto 1 lần nữa
            List<DacTrung> dbRealmList = getAll(realm);
            KeyDT = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_dt()+1);
            // xóa bảng tạm thời
            RealmResults<DacTrung> results = realm.where(DacTrung.class).equalTo("ma_loai_dt",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<DacTrung> getAll(Realm passedInRealm){
        RealmResults<DacTrung> realms = passedInRealm.where(DacTrung.class).findAll();
        return realms;
    }
}
