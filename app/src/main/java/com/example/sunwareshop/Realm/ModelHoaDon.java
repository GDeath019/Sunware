package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.HoaDon;
import com.example.sunwareshop.Database.TaiKhoan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ModelHoaDon extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong KeyHoaDon;
    Realm getData;
    ModelTaiKhoan modelTaiKhoan;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initRealM();
        getData = Realm.getDefaultInstance();
    }

    public void addData() throws ParseException {
        Realm realm = Realm.getDefaultInstance();
        try{
            List<HoaDon> dbRealmList = getAll(realm);
            KeyHoaDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_hoa_don()+1);
        }catch (Exception e){
            newKey();
            long DbKey = KeyHoaDon.getAndIncrement();
            String date="12/12/2020";
            Date date1 = new SimpleDateFormat("dd/mm/yyyy").parse(date);
            ArrayList<HoaDon> data = new ArrayList<>();
            data.add(new HoaDon(1,1,1,date1,1,"asas", (double) 1000000));
            data.add(new HoaDon(2,2,2,date1,2,"asas",(double)  1000000));
            data.add(new HoaDon(3,3,3,date1,3,"asas", (double) 1000000));
            data.add(new HoaDon(4,4,4,date1,4,"asas", (double) 1000000));
            data.add(new HoaDon(5,5,5,date1,5,"asas", (double) 1000000));
            data.add(new HoaDon(6,6,6,date1,6,"asas", (double) 1000000));
            data.add(new HoaDon(7,7,7,date1,7,"asas", (double) 1000000));
            data.add(new HoaDon(8,8,8,date1,8,"asas", (double) 1000000));
            int n = data.size()-1;
            // them data vao phai dinh kem trong 1 transaction
            while (n >= 0){
                int temp = n;
                long finalDbKey = DbKey;
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm backgroundRm) {
                        HoaDon dbRealm = backgroundRm.createObject(HoaDon.class, finalDbKey);
                        dbRealm.setMa_nhan_vien(data.get(temp).getMa_nhan_vien());
                        dbRealm.setMa_khach_hang(data.get(temp).getMa_khach_hang());
                        dbRealm.setNgay_lap(data.get(temp).getNgay_lap());
                        dbRealm.setLoai_don(data.get(temp).getLoai_don());
                        dbRealm.setTrang_thai(data.get(temp).getTrang_thai());
                        dbRealm.setTong_tien(data.get(temp).getTong_tien());

                    }
                });
                DbKey++;
                n--;
            }
        }
        realm.close();
    }
    public String insertHoaDon(ArrayList<String> arrayList){
        final Realm insertRm = Realm.getDefaultInstance();
        getData = Realm.getDefaultInstance();
        newKey();
        ModelChiTietHoaDon modelChiTietHoaDon = new ModelChiTietHoaDon();
        modelTaiKhoan = new ModelTaiKhoan();
        List<TaiKhoan> taiKhoans = modelTaiKhoan.getAll(getData);
        int nv=0,kh=0;
        int check =0;
        for (int i=0;i<taiKhoans.size();i++){
            if (String.valueOf(taiKhoans.get(i).getHo_ten()).equals(String.valueOf(arrayList.get(0)))){
                nv = Integer.parseInt(taiKhoans.get(i).getId()+"");
                check++;
            }
            if (String.valueOf(taiKhoans.get(i).getHo_ten()).equals(String.valueOf(arrayList.get(1)))){
                kh = Integer.parseInt(taiKhoans.get(i).getId()+"");
                check++;
            }
            if (check==2){
                break;
            }
        }
        final long DbKey = KeyHoaDon.getAndIncrement();
        int finalNv = nv;
        int finalKh = kh;
        final String[] s = {""};
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                HoaDon dbRealm = backgroundRm.createObject(HoaDon.class, DbKey);
                dbRealm.setMa_nhan_vien(Integer.parseInt(finalNv +""));
                dbRealm.setMa_khach_hang(Integer.parseInt(finalKh +""));
                //dbRealm.setNgay_lap(Integer.parseInt(sss.toString()));
                dbRealm.setLoai_don(Integer.parseInt("1"));
                dbRealm.setTrang_thai("1");
                double tt =(double) Integer.parseInt(arrayList.get(3))*Double.parseDouble(arrayList.get(2));
                dbRealm.setTong_tien(Double.parseDouble(tt+""));
                s[0] ="non";
            }
        });
        insertRm.close();
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add(DbKey+"");
        arrayList1.add(arrayList.get(5)+"");// id
        arrayList1.add(arrayList.get(2));//price
        arrayList1.add(arrayList.get(3));//soluong
        String temp = modelChiTietHoaDon.insertChiTietHoaDon(arrayList1);
        return temp;
    }

    public String updateHoaDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HoaDon dbRealm = realm.where(HoaDon.class).equalTo("ma_hoa_don", tg).findFirst();
                String sss = "9999";

                dbRealm.setMa_nhan_vien(Integer.parseInt(sss.toString()));
                dbRealm.setMa_khach_hang(Integer.parseInt(sss.toString()));
                //dbRealm.setNgay_lap(Integer.parseInt(sss.toString()));
                dbRealm.setLoai_don(Integer.parseInt(sss.toString()));
                dbRealm.setTrang_thai(sss.toString());
                dbRealm.setTong_tien(Double.parseDouble(sss.toString()));
            }
        });
        return "nope";
    }

    public String deleteHoaDon(Long id){
        Realm realm = Realm.getDefaultInstance();
        final Long dlt = id;
        final boolean[] check = {true};
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm1) {
                RealmResults<HoaDon> results = realm1.where(HoaDon.class).equalTo("ma_hoa_don",dlt).findAll();
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
    private void initRealM() {
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
    public void newKey(){
        Realm realm = Realm.getDefaultInstance();
        try{
            List<HoaDon> dbRealmList = getAll(realm);
            KeyHoaDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_hoa_don()+1);
        }catch (Exception e){
            // lỗi do chưa có data
            realm.beginTransaction();
            // tạo 1 bảng tạm thời
            HoaDon dbRealm = realm.createObject(HoaDon.class,0);
            // set lại key auto 1 lần nữa
            List<HoaDon> dbRealmList = getAll(realm);
            KeyHoaDon = new AtomicLong(dbRealmList.get(dbRealmList.size()-1).getMa_hoa_don()+1);
            // xóa bảng tạm thời
            RealmResults<HoaDon> results = realm.where(HoaDon.class).equalTo("ma_hoa_don",0).findAll();
            results.deleteAllFromRealm();
            realm.commitTransaction();
        }
        realm.close();
    }
    public List<HoaDon> getAll(Realm passedInRealm){
        RealmResults<HoaDon> realms = passedInRealm.where(HoaDon.class).findAll();
        return realms;
    }
}
