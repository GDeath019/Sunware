package com.example.sunwareshop.Realm;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunwareshop.Database.DacTrungSanPham;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.Product;
import com.example.sunwareshop.Database.ThuongHieu;

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
    ModelThuongHieu modelThuongHieu;
    ModelLoaiSanPham modelLoaiSanPham;
    ArrayList<String> arrThuongHieu, arrLoaiSanPham;

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

    public String insertSanPham(ArrayList<String> arrList){
        initRealM();
        final Realm insertRm = Realm.getDefaultInstance();
        getData = Realm.getDefaultInstance();
        modelLoaiSanPham = new ModelLoaiSanPham();
        arrThuongHieu = new ArrayList<>();
        arrLoaiSanPham = new ArrayList<>();
        modelThuongHieu = new ModelThuongHieu();
        newKey();
        final long DbKey = KeySP.getAndIncrement();
        List<ThuongHieu> listTH = modelThuongHieu.getAll(getData);
        Long idTH = new Long(arrList.get(1))+1;
        Long idLSP = new Long(arrList.get(2))+1;
        Long finalIdTH = idTH;
        Long finalIdLSP = idLSP;
        if (finalIdLSP == -1 || finalIdTH == -1){
            return "Insert Fail";
        }
        insertRm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm backgroundRm) {
                Product dbRealm = backgroundRm.createObject(Product.class, DbKey);

                dbRealm.setMa_thuong_hieu(finalIdTH);
                dbRealm.setMa_loai_san_pham(finalIdLSP);
                dbRealm.setTen_san_pham(arrList.get(0));
                dbRealm.setGia_ban(Long.parseLong(arrList.get(3)));
                dbRealm.setHinh_anh(Long.parseLong("1"));
                dbRealm.setSo_luong(Long.parseLong("0"));
                dbRealm.setBao_hanh(Long.parseLong(arrList.get(4))+1);
            }
        });
        insertRm.close();
        return "Success";
    }

    public String updateSanpham(Long id, ArrayList<String> arrList){
        Realm realm = Realm.getDefaultInstance();
        final Long tg = id+1;
        Long idTH = new Long(arrList.get(1))+1;
        Long idLSP = new Long(arrList.get(2))+1;
        Long finalIdTH = idTH;
        Long finalIdLSP = idLSP;
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Product dbRealm = realm.where(Product.class).equalTo("ma_san_pham", tg).findFirst();

                dbRealm.setMa_thuong_hieu(finalIdTH);
                dbRealm.setMa_loai_san_pham(finalIdLSP);
                dbRealm.setTen_san_pham(arrList.get(0));
                dbRealm.setGia_ban(Double.parseDouble(arrList.get(3)));
                dbRealm.setHinh_anh(Long.parseLong("1"));
                dbRealm.setBao_hanh(Long.parseLong(arrList.get(4))+1);
//                dbRealm.setSo_luong(Long.parseLong(arrList.get(4)));
            }
        });
        return "Success";
    }

    public String deleteSanPham(Long id){
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
            data.add(new Product(1, 1,1,"name",1.0,1,1,1));
            data.add(new Product(2, 1,1,"name",1.0,1,1,2));
            data.add(new Product(3, 1,1,"name",1.0,1,1,3));
            data.add(new Product(4, 1,1,"name",1.0,1,1,4));
            data.add(new Product(5, 1,1,"name",1.0,1,1,5));
            data.add(new Product(6, 1,1,"name",1.0,1,1,6));
            data.add(new Product(7, 1,1,"name",1.0,1,1,7));
            data.add(new Product(8, 1,1,"name",1.0,1,1,8));
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
