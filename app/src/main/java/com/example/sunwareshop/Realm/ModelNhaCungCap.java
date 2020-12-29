package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.NhaCungCap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelNhaCungCap extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyNCC;
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
        final long DbKey = KeyNCC.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                NhaCungCap dbRealm = backgroundRm.createObject(NhaCungCap.class, DbKey);
                String sss = "1";
                dbRealm.setTen(sss.toString());
                dbRealm.setDia_chi(sss.toString());
                dbRealm.setHot_line(sss.toString());
                dbRealm.setEmail(sss.toString());
                dbRealm.setSdt(sss.toString());
                dbRealm.setHinh_anh(Integer.parseInt(sss.toString()));
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
                NhaCungCap dbRealm = realm.where(NhaCungCap.class).equalTo("ma_ncc", tg).findFirst();
                String sss = "9999";

                dbRealm.setTen(sss.toString());
                dbRealm.setDia_chi(sss.toString());
                dbRealm.setHot_line(sss.toString());
                dbRealm.setEmail(sss.toString());
                dbRealm.setSdt(sss.toString());
                dbRealm.setHinh_anh(Integer.parseInt(sss.toString()));
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
                RealmResults<NhaCungCap> results = realm1.where(NhaCungCap.class).equalTo("ma_ncc",dlt).findAll();
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
            List<NhaCungCap> dbRealmList = getAll(realm);
            KeyNCC = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_ncc()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyNCC.getAndIncrement();
            ArrayList<NhaCungCap> data = new ArrayList<>();
            data.add(new NhaCungCap(1, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(2, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(3, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(4, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(5, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(6, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(7, "ten","diachi","hotline","email" , "sdt" ,1));
            data.add(new NhaCungCap(8, "ten","diachi","hotline","email" , "sdt" ,1));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        NhaCungCap dbRealm = backgroundRm.createObject(NhaCungCap.class, finalDbKey);
                        dbRealm.setTen(data.get(temp).getTen());
                        dbRealm.setDia_chi(data.get(temp).getDia_chi());
                        dbRealm.setHot_line(data.get(temp).getHot_line());
                        dbRealm.setEmail(data.get(temp).getEmail());
                        dbRealm.setSdt(data.get(temp).getSdt());
                        dbRealm.setHinh_anh(data.get(temp).getHinh_anh());
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
            List<NhaCungCap> dbRealmList = getAll(realm);
            KeyNCC = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_ncc()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            NhaCungCap dbRealm = realm.createObject(NhaCungCap.class,0);
            // set lại key auto 1 lần nữa
            List<NhaCungCap> dbRealmList = getAll(realm);
            KeyNCC = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_ncc()+1);
            // xóa bảng tạm thời
            RealmResults<NhaCungCap> results = realm.where(NhaCungCap.class).equalTo("ma_ncc",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<NhaCungCap> getAll(Realm passedInRealm){
        RealmResults<NhaCungCap> realms = passedInRealm.where(NhaCungCap.class).findAll();
        return realms;
    }
}
