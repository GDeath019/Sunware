package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.DacTrungSanPham;
import com.example.sunwareshop.Database.ThuongHieu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelThuongHieu extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyTH;
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

    public String insertThuongHieu(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyTH.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                ThuongHieu dbRealm = backgroundRm.createObject(ThuongHieu.class, DbKey);
                String sss = "1";
                dbRealm.setTen_thuong_hieu(sss.toString());
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateThuongHieu(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                ThuongHieu dbRealm = realm.where(ThuongHieu.class).equalTo("ma_thuong_hieu", tg).findFirst();
                String sss = "9999";
                dbRealm.setTen_thuong_hieu(sss.toString());
//                }
            }
        });
        return "nope";
    }

    public String deleteThuongHieu(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<ThuongHieu> results = realm1.where(ThuongHieu.class).equalTo("ma_thuong_hieu",dlt).findAll();
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
            List<ThuongHieu> dbRealmList = getAll(realm);
            KeyTH = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_thuong_hieu()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyTH.getAndIncrement();
            ArrayList<ThuongHieu> data = new ArrayList<>();
            data.add(new ThuongHieu(1, "non"));
            data.add(new ThuongHieu(2, "kem"));
            data.add(new ThuongHieu(3, "ga"));
            data.add(new ThuongHieu(4, "ngu"));
            data.add(new ThuongHieu(5, "1"));
            data.add(new ThuongHieu(6, "1"));
            data.add(new ThuongHieu(7, "1"));
            data.add(new ThuongHieu(8, "1"));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        ThuongHieu dbRealm = backgroundRm.createObject(ThuongHieu.class, finalDbKey);
                        dbRealm.setTen_thuong_hieu(data.get(temp).getTen_thuong_hieu());
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
            List<ThuongHieu> dbRealmList = getAll(realm);
            KeyTH = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_thuong_hieu()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            ThuongHieu dbRealm = realm.createObject(ThuongHieu.class,0);
            // set lại key auto 1 lần nữa
            List<ThuongHieu> dbRealmList = getAll(realm);
            KeyTH = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_thuong_hieu()+1);
            // xóa bảng tạm thời
            RealmResults<ThuongHieu> results = realm.where(ThuongHieu.class).equalTo("ma_thuong_hieu",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<ThuongHieu> getAll(Realm passedInRealm){
        RealmResults<ThuongHieu> realms = passedInRealm.where(ThuongHieu.class).findAll();
        return realms;
    }
}
