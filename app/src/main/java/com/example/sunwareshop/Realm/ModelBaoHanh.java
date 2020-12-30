package com.example.sunwareshop.Realm;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelBaoHanh extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyBaohanh;
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

    public String insertBaoHanh(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyBaohanh.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                BaoHanh dbRealm = backgroundRm.createObject(BaoHanh.class, DbKey);
                String sss = "1";
                dbRealm.setThoi_gian(Integer.parseInt(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateBaoHanh(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                    BaoHanh dbRealm = realm.where(BaoHanh.class).equalTo("ma_bao_hanh", tg).findFirst();
                    String sss = "9999";
                    dbRealm.setThoi_gian(Integer.parseInt(sss.toString()));
//                }
            }
        });
        return "nope";
    }

    public String deleteBaoHanh(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<BaoHanh> results = realm1.where(BaoHanh.class).equalTo("ma_bao_hanh",dlt).findAll();
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
            List<BaoHanh> dbRealmList = getAll(realm);
            KeyBaohanh = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_bao_hanh()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyBaohanh.getAndIncrement();
            ArrayList<String> time = new ArrayList<>();
            time.add("36");
            time.add("24");
            time.add("18");
            time.add("12");
            time.add("9");
            time.add("6");
            time.add("3");
            time.add("1");
            int n = time.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        BaoHanh dbRealm = backgroundRm.createObject(BaoHanh.class, finalDbKey);
                        dbRealm.setThoi_gian(Integer.parseInt(time.get(temp)));
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
            List<BaoHanh> dbRealmList = getAll(realm);
            KeyBaohanh = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_bao_hanh()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            BaoHanh dbRealm = realm.createObject(BaoHanh.class,0);
            // set lại key auto 1 lần nữa
            List<BaoHanh> dbRealmList = getAll(realm);
            KeyBaohanh = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_bao_hanh()+1);
            // xóa bảng tạm thời
            RealmResults<BaoHanh> results = realm.where(BaoHanh.class).equalTo("ma_bao_hanh",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<BaoHanh> getAll(Realm passedInRealm){
        RealmResults<BaoHanh> realms = passedInRealm.where(BaoHanh.class).findAll();
        return realms;
    }
}
