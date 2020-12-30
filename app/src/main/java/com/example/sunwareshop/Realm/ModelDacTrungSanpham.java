package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.DacTrung;
import com.example.sunwareshop.Database.DacTrungSanPham;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelDacTrungSanpham extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyDTSP;
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

    public String insertDacTrungSanPham(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyDTSP.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                DacTrungSanPham dbRealm = backgroundRm.createObject(DacTrungSanPham.class, DbKey);
                String sss = "1";
                dbRealm.setMa_loai_dt(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateDacTrungSanpham(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                DacTrungSanPham dbRealm = realm.where(DacTrungSanPham.class).equalTo("id", tg).findFirst();
                String sss = "9999";
                dbRealm.setMa_loai_dt(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
//                }
            }
        });
        return "nope";
    }

    public String deleteDacTrungSanPham(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<DacTrungSanPham> results = realm1.where(DacTrungSanPham.class).equalTo("id",dlt).findAll();
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
            List<DacTrungSanPham> dbRealmList = getAll(realm);
            KeyDTSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyDTSP.getAndIncrement();
            ArrayList<DacTrungSanPham> data = new ArrayList<>();
            data.add(new DacTrungSanPham(1, 3,1));
            data.add(new DacTrungSanPham(2, 5,2));
            data.add(new DacTrungSanPham(3, 4,3));
            data.add(new DacTrungSanPham(4, 7,4));
            data.add(new DacTrungSanPham(5, 3,5));
            data.add(new DacTrungSanPham(6, 1,6));
            data.add(new DacTrungSanPham(7, 6,7));
            data.add(new DacTrungSanPham(8, 7,8));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        DacTrungSanPham dbRealm = backgroundRm.createObject(DacTrungSanPham.class, finalDbKey);
                        dbRealm.setMa_loai_dt(data.get(temp).getMa_loai_dt());
                        dbRealm.setMa_san_pham(data.get(temp).getMa_san_pham());
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
            List<DacTrungSanPham> dbRealmList = getAll(realm);
            KeyDTSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            DacTrungSanPham dbRealm = realm.createObject(DacTrungSanPham.class,0);
            // set lại key auto 1 lần nữa
            List<DacTrungSanPham> dbRealmList = getAll(realm);
            KeyDTSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
            // xóa bảng tạm thời
            RealmResults<DacTrungSanPham> results = realm.where(DacTrungSanPham.class).equalTo("id",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<DacTrungSanPham> getAll(Realm passedInRealm){
        RealmResults<DacTrungSanPham> realms = passedInRealm.where(DacTrungSanPham.class).findAll();
        return realms;
    }
}
