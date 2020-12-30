package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.LoaiSP;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelLoaiSanPham extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyLSP;
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

    public String insertLoaiSanPham(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyLSP.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                LoaiSP dbRealm = backgroundRm.createObject(LoaiSP.class, DbKey);
                String sss = "1";
                dbRealm.setTen_loai_sp(sss.toString());
                dbRealm.setMo_ta(sss.toString());
            }
        });
        insertRm.close();
        return "nope";
    }

    public String updateLoaiSanPham(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LoaiSP dbRealm = realm.where(LoaiSP.class).equalTo("ma_loai_sp", tg).findFirst();
                String sss = "9999";
                dbRealm.setTen_loai_sp(sss.toString());
                dbRealm.setMo_ta(sss.toString());
            }
        });
        return "nope";
    }

    public String deleteLoaiSanPham(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<LoaiSP> results = realm1.where(LoaiSP.class).equalTo("ma_loai_sp",dlt).findAll();
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
            List<LoaiSP> dbRealmList = getAll(realm);
            KeyLSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_sp()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyLSP.getAndIncrement();
            ArrayList<LoaiSP> data = new ArrayList<>();
            data.add(new LoaiSP(1, "Tivi","mota"));
            data.add(new LoaiSP(2, "Tủ lạnh","mota"));
            data.add(new LoaiSP(3, "Quạt","mota"));
            data.add(new LoaiSP(4, "Điện thoại","mota"));
            data.add(new LoaiSP(5, "Bếp từ","mota"));
            data.add(new LoaiSP(6, "Đầu kĩ thuật số","mota"));
            data.add(new LoaiSP(7, "Máy tính","mota"));
            data.add(new LoaiSP(8, "Đồ bếp","mota"));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        LoaiSP dbRealm = backgroundRm.createObject(LoaiSP.class, finalDbKey);
                        dbRealm.setTen_loai_sp(data.get(temp).getTen_loai_sp());
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
            List<LoaiSP> dbRealmList = getAll(realm);
            KeyLSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_sp()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            LoaiSP dbRealm = realm.createObject(LoaiSP.class,0);
            // set lại key auto 1 lần nữa
            List<LoaiSP> dbRealmList = getAll(realm);
            KeyLSP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_loai_sp()+1);
            // xóa bảng tạm thời
            RealmResults<LoaiSP> results = realm.where(LoaiSP.class).equalTo("ma_loai_sp",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<LoaiSP> getAll(Realm passedInRealm){
        RealmResults<LoaiSP> realms = passedInRealm.where(LoaiSP.class).findAll();
        return realms;
    }
}
