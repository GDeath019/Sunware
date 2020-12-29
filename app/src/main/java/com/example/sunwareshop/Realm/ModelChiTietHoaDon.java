package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelChiTietHoaDon extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyCTHD;
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

    public String insertChiTietHoaDon(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyCTHD.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                ChiTietHoaDon dbRealm = backgroundRm.createObject(ChiTietHoaDon.class, DbKey);
                String sss = "1";
                dbRealm.setMa_hoa_don(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
                dbRealm.setGia_ban(Double.parseDouble(sss.toString()));
                dbRealm.setBao_hanh(Long.parseLong(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateChiTietHoaDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                if(edtName.getText().length()==0 || edtUnv.getText().length()==0 || edtAge.getText().length()==0){
//                    Toast.makeText(getApplicationContext(), "Mời nhập đủ dữ liệu!!", Toast.LENGTH_SHORT).show();
//                }else {
                ChiTietHoaDon dbRealm = realm.where(ChiTietHoaDon.class).equalTo("id", tg).findFirst();
                String sss = "9999";
                dbRealm.setMa_hoa_don(Long.parseLong(sss.toString()));
                dbRealm.setMa_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
                dbRealm.setGia_ban(Double.parseDouble(sss.toString()));
                dbRealm.setBao_hanh(Long.parseLong(sss.toString()));
//                }
            }
        });
        return "nope";
    }

    public String deleteChiTietHoaDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<ChiTietHoaDon> results = realm1.where(ChiTietHoaDon.class).equalTo("id",dlt).findAll();
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
            List<ChiTietHoaDon> dbRealmList = getAll(realm);
            KeyCTHD = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyCTHD.getAndIncrement();
            ArrayList<ChiTietHoaDon> data = new ArrayList<>();
            data.add(new ChiTietHoaDon(1, 1, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(2, 2, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(3, 3, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(4, 4, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(5, 5, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(6, 6, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(7, 7, 1, 1, 1, 1));
            data.add(new ChiTietHoaDon(8, 8, 1, 1, 1, 1));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        ChiTietHoaDon dbRealm = backgroundRm.createObject(ChiTietHoaDon.class, finalDbKey);
                        dbRealm.setMa_hoa_don(data.get(temp).getMa_hoa_don());
                        dbRealm.setMa_san_pham(data.get(temp).getMa_san_pham());
                        dbRealm.setSo_luong(data.get(temp).getSo_luong());
                        dbRealm.setGia_ban(data.get(temp).getGia_ban());
                        dbRealm.setBao_hanh(data.get(temp).getBao_hanh());
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
            List<ChiTietHoaDon> dbRealmList = getAll(realm);
            KeyCTHD = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            ChiTietHoaDon dbRealm = realm.createObject(ChiTietHoaDon.class,0);
            // set lại key auto 1 lần nữa
            List<ChiTietHoaDon> dbRealmList = getAll(realm);
            KeyCTHD = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
            // xóa bảng tạm thời
            RealmResults<ChiTietHoaDon> results = realm.where(ChiTietHoaDon.class).equalTo("id",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<ChiTietHoaDon> getAll(Realm passedInRealm){
        RealmResults<ChiTietHoaDon> realms = passedInRealm.where(ChiTietHoaDon.class).findAll();
        return realms;
    }
}
