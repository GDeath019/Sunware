package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.TaiKhoan;
import com.example.sunwareshop.Database.ThuongHieu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelTaiKhoan extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyTK;
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

    public String insertTaiKhoan(){
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyTK.getAndIncrement();
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                TaiKhoan dbRealm = backgroundRm.createObject(TaiKhoan.class, DbKey);
                String sss = "1";
                String sDate1="31/12/1998";
                Date date1= null;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dbRealm.setEmail(sss.toString());
                dbRealm.setMat_khau(sss.toString());
                dbRealm.setDia_chi(sss.toString());
                dbRealm.setSdt(sss.toString());
                dbRealm.setNgay_lap(date1);
                dbRealm.setHinh_anh(Long.parseLong(sss.toString()));
                dbRealm.setHo_ten(sss.toString());
                dbRealm.setLoai_tai_khoan(Long.parseLong(sss.toString()));
            }
        });
        insertRm.close();
        final Realm InsertRm = Realm.getDefaultInstance();
        return "nope";
    }

    public String updateTaiKhoan(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TaiKhoan dbRealm = realm.where(TaiKhoan.class).equalTo("id", tg).findFirst();
                String sDate1="31/12/1998";
                Date date1= null;
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String sss = "9999";
                dbRealm.setEmail(sss.toString());
                dbRealm.setMat_khau(sss.toString());
                dbRealm.setDia_chi(sss.toString());
                dbRealm.setSdt(sss.toString());
                dbRealm.setNgay_lap(date1);
                dbRealm.setHinh_anh(Long.parseLong(sss.toString()));
                dbRealm.setHo_ten(sss.toString());
                dbRealm.setLoai_tai_khoan(Long.parseLong(sss.toString()));
            }
        });
        return "nope";
    }

    public String deleteTaiKhoan(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<TaiKhoan> results = realm1.where(TaiKhoan.class).equalTo("id",dlt).findAll();
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

    public void addData() throws ParseException {
        Realm realm = Realm.getDefaultInstance();
        try{
            List<TaiKhoan> dbRealmList = getAll(realm);
            KeyTK = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyTK.getAndIncrement();
            String sDate1="31/12/1998";
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
            ArrayList<TaiKhoan> data = new ArrayList<>();
            data.add(new TaiKhoan(1, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(2, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(3, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(4, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(5, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(6, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(7, "1", date1, "1","name","dia chi","sdt", 1, 1));
            data.add(new TaiKhoan(8, "1", date1, "1","name","dia chi","sdt", 1, 1));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        TaiKhoan dbRealm = backgroundRm.createObject(TaiKhoan.class, finalDbKey);
                        dbRealm.setEmail(data.get(temp).getEmail());
                        dbRealm.setMat_khau(data.get(temp).getMat_khau());
                        dbRealm.setHo_ten(data.get(temp).getHo_ten());
                        dbRealm.setDia_chi(data.get(temp).getDia_chi());
                        dbRealm.setSdt(data.get(temp).getSdt());
                        dbRealm.setHinh_anh(data.get(temp).getHinh_anh());
                        dbRealm.setLoai_tai_khoan(data.get(temp).getLoai_tai_khoan());
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
            List<TaiKhoan> dbRealmList = getAll(realm);
            KeyTK = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            TaiKhoan dbRealm = realm.createObject(TaiKhoan.class,0);
            // set lại key auto 1 lần nữa
            List<TaiKhoan> dbRealmList = getAll(realm);
            KeyTK = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getId()+1);
            // xóa bảng tạm thời
            RealmResults<TaiKhoan> results = realm.where(TaiKhoan.class).equalTo("id",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<TaiKhoan> getAll(Realm passedInRealm){
        RealmResults<TaiKhoan> realms = passedInRealm.where(TaiKhoan.class).findAll();
        return realms;
    }
}
