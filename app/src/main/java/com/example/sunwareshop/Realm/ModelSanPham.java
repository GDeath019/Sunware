package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.DacTrungSanPham;
import com.example.sunwareshop.Database.Product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelSanPham extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeySP;
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
        final long DbKey = KeySP.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                Product dbRealm = backgroundRm.createObject(Product.class, DbKey);
                String sss = "1";
                dbRealm.setMa_thuong_hieu(Long.parseLong(sss.toString()));
                dbRealm.setMa_loai_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setTen_san_pham(sss.toString());
                dbRealm.setGia_ban(Long.parseLong(sss.toString()));
                dbRealm.setHinh_anh(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
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
                Product dbRealm = realm.where(Product.class).equalTo("ma_san_pham", tg).findFirst();
                String sss = "9999";
                dbRealm.setMa_thuong_hieu(Long.parseLong(sss.toString()));
                dbRealm.setMa_loai_san_pham(Long.parseLong(sss.toString()));
                dbRealm.setTen_san_pham(sss.toString());
                dbRealm.setGia_ban(Long.parseLong(sss.toString()));
                dbRealm.setHinh_anh(Long.parseLong(sss.toString()));
                dbRealm.setSo_luong(Long.parseLong(sss.toString()));
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
                RealmResults<Product> results = realm1.where(Product.class).equalTo("ma_san_pham",dlt).findAll();
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
            List<Product> dbRealmList = getAll(realm);
            KeySP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_san_pham()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeySP.getAndIncrement();
            ArrayList<Product> data = new ArrayList<>();
            data.add(new Product(1, 1,1,"name",1.0,1,1));
            data.add(new Product(2, 1,1,"name",1.0,1,1));
            data.add(new Product(3, 1,1,"name",1.0,1,1));
            data.add(new Product(4, 1,1,"name",1.0,1,1));
            data.add(new Product(5, 1,1,"name",1.0,1,1));
            data.add(new Product(6, 1,1,"name",1.0,1,1));
            data.add(new Product(7, 1,1,"name",1.0,1,1));
            data.add(new Product(8, 1,1,"name",1.0,1,1));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        Product dbRealm = backgroundRm.createObject(Product.class, finalDbKey);
                        dbRealm.setMa_thuong_hieu(data.get(temp).getMa_thuong_hieu());
                        dbRealm.setMa_loai_san_pham(data.get(temp).getMa_loai_san_pham());
                        dbRealm.setTen_san_pham(data.get(temp).getTen_san_pham());
                        dbRealm.setGia_ban(data.get(temp).getGia_ban());
                        dbRealm.setHinh_anh(data.get(temp).getHinh_anh());
                        dbRealm.setSo_luong(data.get(temp).getSo_luong());
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
            List<Product> dbRealmList = getAll(realm);
            KeySP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_san_pham()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            Product dbRealm = realm.createObject(Product.class,0);
            // set lại key auto 1 lần nữa
            List<Product> dbRealmList = getAll(realm);
            KeySP = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_san_pham()+1);
            // xóa bảng tạm thời
            RealmResults<Product> results = realm.where(Product.class).equalTo("ma_san_pham",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<Product> getAll(Realm passedInRealm){
        RealmResults<Product> realms = passedInRealm.where(Product.class).findAll();
        return realms;
    }
}
