package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.PhieuNhap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelPhieuNhap extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyPN;
    Realm getData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initRealM();
        getData = Realm.getDefaultInstance();
        try {
            addData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public String insertDacTrung() throws ParseException {
        final Realm insertRm = Realm.getDefaultInstance();
        newKey();
        final long DbKey = KeyPN.getAndIncrement();
        String date="12/12/2020";
        Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                PhieuNhap dbRealm = backgroundRm.createObject(PhieuNhap.class, DbKey);
                String sss = "1";
                dbRealm.setMa_nhan_vien(Integer.parseInt(sss.toString()));
                dbRealm.setMa_ncc(Integer.parseInt(sss.toString()));
                //dbRealm.setNgay_nhap(Integer.parseInt(sss.toString()));
                dbRealm.setTong_tien(Double.parseDouble(sss.toString()));
                dbRealm.setGhi_chu(sss.toString());
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
                PhieuNhap dbRealm = realm.where(PhieuNhap.class).equalTo("ma_phieu_nhap", tg).findFirst();
                String sss = "9999";

                dbRealm.setMa_nhan_vien(Integer.parseInt(sss.toString()));
                dbRealm.setMa_ncc(Integer.parseInt(sss.toString()));
                //dbRealm.setNgay_nhap(Integer.parseInt(sss.toString()));
                dbRealm.setTong_tien(Double.parseDouble(sss.toString()));
                dbRealm.setGhi_chu(sss.toString());
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
                RealmResults<PhieuNhap> results = realm1.where(PhieuNhap.class).equalTo("ma_phieu_nhap",dlt).findAll();
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
            List<PhieuNhap> dbRealmList = getAll(realm);
            KeyPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_phieu_nhap()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyPN.getAndIncrement();
            String date="12/12/2020";
            Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            ArrayList<PhieuNhap> data = new ArrayList<>();
            data.add(new PhieuNhap(1, 1,1,date1, (double) 1354700000,"ghi chu"));
            data.add(new PhieuNhap(2, 4,2,date1, (double) 285000000,"ghi chu"));
            data.add(new PhieuNhap(3, 1,3,date1, (double) 973800000,"ghi chu"));
            data.add(new PhieuNhap(4, 4,4,date1, (double) 0,"ghi chu"));
            data.add(new PhieuNhap(5, 6,5,date1, (double) 0,"ghi chu"));
            data.add(new PhieuNhap(6, 6,6,date1, (double) 0,"ghi chu"));
            data.add(new PhieuNhap(7, 7,7,date1, (double) 0,"ghi chu"));
            data.add(new PhieuNhap(8, 4,8,date1, (double) 0,"ghi chu"));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        PhieuNhap dbRealm = backgroundRm.createObject(PhieuNhap.class, finalDbKey);
                        dbRealm.setMa_nhan_vien(data.get(temp).getMa_nhan_vien());
                        dbRealm.setMa_ncc(data.get(temp).getMa_ncc());
                        dbRealm.setNgay_nhap(data.get(temp).getNgay_nhap());
                        dbRealm.setTong_tien(data.get(temp).getTong_tien());
                        dbRealm.setGhi_chu(data.get(temp).getGhi_chu());

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
            List<PhieuNhap> dbRealmList = getAll(realm);
            KeyPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_phieu_nhap()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            PhieuNhap dbRealm = realm.createObject(PhieuNhap.class,0);
            // set lại key auto 1 lần nữa
            List<PhieuNhap> dbRealmList = getAll(realm);
            KeyPN = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_phieu_nhap()+1);
            // xóa bảng tạm thời
            RealmResults<PhieuNhap> results = realm.where(PhieuNhap.class).equalTo("ma_phieu_nhap",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<PhieuNhap> getAll(Realm passedInRealm){
        RealmResults<PhieuNhap> realms = passedInRealm.where(PhieuNhap.class).findAll();
        return realms;
    }

}
