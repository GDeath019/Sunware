package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.ChiTietHoaDon;
import com.example.sunwareshop.Database.ChiTietPhieuNhap;
import com.example.sunwareshop.Database.DacTrung;
import com.example.sunwareshop.Database.DacTrungSanPham;
import com.example.sunwareshop.Database.HoaDon;
import com.example.sunwareshop.Database.IMEI;
import com.example.sunwareshop.Database.LoaiDon;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.NhaCungCap;
import com.example.sunwareshop.Database.PhieuNhap;
import com.example.sunwareshop.Database.Product;
import com.example.sunwareshop.Database.TaiKhoan;
import com.example.sunwareshop.Database.ThuongHieu;
import com.example.sunwareshop.Realm.ModelBaoHanh;
import com.example.sunwareshop.Realm.ModelChiTietHoaDon;
import com.example.sunwareshop.Realm.ModelChiTietPhieuNhap;
import com.example.sunwareshop.Realm.ModelDacTrung;
import com.example.sunwareshop.Realm.ModelDacTrungSanpham;
import com.example.sunwareshop.Realm.ModelHoaDon;
import com.example.sunwareshop.Realm.ModelImei;
import com.example.sunwareshop.Realm.ModelLoaiDon;
import com.example.sunwareshop.Realm.ModelLoaiSanPham;
import com.example.sunwareshop.Realm.ModelNhaCungCap;
import com.example.sunwareshop.Realm.ModelPhieuNhap;
import com.example.sunwareshop.Realm.ModelSanPham;
import com.example.sunwareshop.Realm.ModelTaiKhoan;
import com.example.sunwareshop.Realm.ModelThuongHieu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    private String DbName = "FirstDb.realm";
    public static AtomicLong DBKey;
    Realm getData;
    ModelBaoHanh modelBaoHanh;
    ModelChiTietHoaDon modelChiTietHoaDon;
    ModelChiTietPhieuNhap modelChiTietPhieuNhap;
    ModelDacTrung modelDacTrung;
    ModelDacTrungSanpham modelDacTrungSanpham;
    ModelHoaDon modelHoaDon;
    ModelImei modelImei;
    ModelLoaiDon modelLoaiDon;
    ModelLoaiSanPham modelLoaiSanPham;
    ModelNhaCungCap modelNhaCungCap;
    ModelPhieuNhap modelPhieuNhap;
    ModelSanPham modelSanPham;
    ModelTaiKhoan modelTaiKhoan;
    ModelThuongHieu modelThuongHieu;
    Button btn1;
    ListView lv1;
    ArrayList<String> arr;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRealM();
        getData = Realm.getDefaultInstance();

        setTitle("Sunware");

        anhXa();
        addLv();
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<BaoHanh> testData = modelBaoHanh.getAll(getData);
                modelBaoHanh.deleteBaoHanh(testData.get(position).getMa_bao_hanh());
                addLv();
            }
        });
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                List<BaoHanh> testData = modelBaoHanh.getAll(getData);
//                modelBaoHanh.updateBaoHanh(testData.get(position).getMa_bao_hanh());
//                addLv();
                return false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                modelBaoHanh.addData();
//                modelChiTietHoaDon.addData();
//                modelChiTietPhieuNhap.addData();
//                modelDacTrung.addData();
//                modelDacTrungSanpham.addData();
//                try {
//                    modelHoaDon.addData();
//                    modelTaiKhoan.addData();
//                    modelPhieuNhap.addData();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
                modelImei.addData();
                modelLoaiDon.addData();
                modelLoaiSanPham.addData();
                modelNhaCungCap.addData();
                modelSanPham.addData();
                modelThuongHieu.addData();
                addLv();
            }
        });
    }

    private void addLv() {
        List<BaoHanh> dbRealmList = modelBaoHanh.getAll(getData);
        arr = new ArrayList<>();
        Toast.makeText(this, "???"+dbRealmList.size(), Toast.LENGTH_SHORT).show();
        for (int i=0;i<dbRealmList.size();i++){
            arr.add(dbRealmList.get(i).getMa_bao_hanh()+"\n   "+dbRealmList.get(i).getThoi_gian());
        }
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arr);
        lv1.setAdapter(arrayAdapter);
    }

    private void anhXa() {
        lv1 = findViewById(R.id.Lv1);
        btn1 = findViewById(R.id.btn1);



        /// model
        modelBaoHanh = new ModelBaoHanh();
        modelChiTietHoaDon = new ModelChiTietHoaDon();
        modelChiTietPhieuNhap = new ModelChiTietPhieuNhap();
        modelDacTrung = new ModelDacTrung();
        modelDacTrungSanpham = new ModelDacTrungSanpham();
        modelHoaDon = new ModelHoaDon();
        modelImei = new ModelImei();
        modelLoaiDon = new ModelLoaiDon();
        modelLoaiSanPham = new ModelLoaiSanPham();
        modelNhaCungCap = new ModelNhaCungCap();
        modelPhieuNhap = new ModelPhieuNhap();
        modelSanPham = new ModelSanPham();
        modelTaiKhoan = new ModelTaiKhoan();
        modelThuongHieu = new ModelThuongHieu();
    }

    private void initRealM() {
        Realm.init(this);
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
    public void checkData(){
        List<BaoHanh> dbRealmList = modelBaoHanh.getAll(getData);
        List<ChiTietHoaDon> dbRealmList1 = modelChiTietHoaDon.getAll(getData);
        List<ChiTietPhieuNhap> dbRealmList2 = modelChiTietPhieuNhap.getAll(getData);
        List<DacTrung> dbRealmList3 = modelDacTrung.getAll(getData);
        List<DacTrungSanPham> dbRealmList4 = modelDacTrungSanpham.getAll(getData);
        List<HoaDon> dbRealmList5 = modelHoaDon.getAll(getData);
        List<IMEI> dbRealmList6 = modelImei.getAll(getData);
        List<LoaiDon> dbRealmList7 = modelLoaiDon.getAll(getData);
        List<LoaiSP> dbRealmList8 = modelLoaiSanPham.getAll(getData);
        List<NhaCungCap> dbRealmList9 = modelNhaCungCap.getAll(getData);
        List<Product> dbRealmList10 = modelSanPham.getAll(getData);
        List<PhieuNhap> dbRealmList11 = modelPhieuNhap.getAll(getData);
        List<TaiKhoan> dbRealmList12 = modelTaiKhoan.getAll(getData);
        List<ThuongHieu> dbRealmList13 = modelThuongHieu.getAll(getData);
        int a=0;
    }
}