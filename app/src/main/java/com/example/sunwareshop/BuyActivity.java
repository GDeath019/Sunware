package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunwareshop.Database.ChiTietHoaDon;
import com.example.sunwareshop.Database.HoaDon;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.Product;
import com.example.sunwareshop.Database.TaiKhoan;
import com.example.sunwareshop.Database.ThuongHieu;
import com.example.sunwareshop.Realm.ModelChiTietHoaDon;
import com.example.sunwareshop.Realm.ModelHoaDon;
import com.example.sunwareshop.Realm.ModelLoaiSanPham;
import com.example.sunwareshop.Realm.ModelSanPham;
import com.example.sunwareshop.Realm.ModelTaiKhoan;
import com.example.sunwareshop.Realm.ModelThuongHieu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class BuyActivity extends AppCompatActivity {
    TextView edtName, edtPrice, tvThuongHieu, tvLoaiSanPham;
    EditText edtSoluong;
    Spinner spnNhanVien, spnKhach;
    ImageButton img;
    Button btnSubmit, btnCancel;
    ModelSanPham modelSanPham;
    ModelThuongHieu modelThuongHieu;
    ModelLoaiSanPham modelLoaiSanPham;
    ModelHoaDon modelHoaDon;
    Realm getData;
    ModelTaiKhoan modelTaiKhoan;
    ArrayList<String> arrNhanVien, arrKhach;
    ArrayAdapter arrayAdapter, arrayAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        getData = Realm.getDefaultInstance();
        anhXa();
        setData();
        addSpinner();
        Intent intent = getIntent();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                String currentTime = sdf.format(new Date());
//                Toast.makeText(getApplicationContext(), ""+currentTime, Toast.LENGTH_LONG).show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(String.valueOf(spnNhanVien.getSelectedItem()));
                arrayList.add(spnKhach.getSelectedItem()+"");
                arrayList.add(edtPrice.getText().toString());
                arrayList.add(edtSoluong.getText().toString());
                arrayList.add(edtName.getText().toString());
                arrayList.add(intent.getStringExtra("id"));
                String result = modelHoaDon.insertHoaDon(arrayList);
                List<HoaDon> cc = modelHoaDon.getAll(getData);
                ModelChiTietHoaDon modelChiTietHoaDon = new ModelChiTietHoaDon();
                List<ChiTietHoaDon> chiTietHoaDons = modelChiTietHoaDon.getAll(getData);
            }
        });
    }
    private void addSpinner(){
        List<TaiKhoan> list = modelTaiKhoan.getAll(getData);
        arrNhanVien = new ArrayList<>();
        arrKhach = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            if (list.get(i).getLoai_tai_khoan()==1){
                arrNhanVien.add(list.get(i).getHo_ten());
            }else {
                arrKhach.add(list.get(i).getHo_ten());
            }
        }
        arrayAdapter = new ArrayAdapter(BuyActivity.this, android.R.layout.simple_list_item_1, arrNhanVien);
        arrayAdapter2 = new ArrayAdapter(BuyActivity.this, android.R.layout.simple_list_item_1, arrKhach);
        spnNhanVien.setAdapter(arrayAdapter);
        spnKhach.setAdapter(arrayAdapter2);
    }
    private void anhXa(){
        edtName = findViewById(R.id.tvNameBuy);
        edtPrice = findViewById(R.id.tvPriceBuy);
        edtSoluong = findViewById(R.id.edtSoLuongBuy);
        img = findViewById(R.id.imgBtBuy);
        tvThuongHieu = findViewById(R.id.tvThuongHieuBuy);
        tvLoaiSanPham = findViewById(R.id.tvLoaiSanPhamBuy);
        btnSubmit = findViewById(R.id.btnSubmitBuy);
        btnCancel = findViewById(R.id.btnCancelBuy);
        spnNhanVien = findViewById(R.id.spnNhanVienBuy);
        spnKhach = findViewById(R.id.spnKhachBuy);

        ///// model

        modelSanPham = new ModelSanPham();
        modelThuongHieu = new ModelThuongHieu();
        modelLoaiSanPham = new ModelLoaiSanPham();
        modelTaiKhoan = new ModelTaiKhoan();
        modelHoaDon = new ModelHoaDon();
    }
    private void setData(){
        Intent intent = getIntent();
        List<ThuongHieu> thuongHieuList = modelThuongHieu.getAll(getData);
        List<Product> products = modelSanPham.getAll(getData);
        List<LoaiSP> loaiSPList = modelLoaiSanPham.getAll(getData);
        String nameTH ="";
        String nameLSP ="";
        for (int i=0;i<thuongHieuList.size();i++){
            if (products.get(Integer.parseInt(intent.getStringExtra("id"))).getMa_thuong_hieu()
                    == thuongHieuList.get(i).getMa_thuong_hieu()){
                nameTH = thuongHieuList.get(i).getTen_thuong_hieu();
                break;
            }
        }
        for (int i=0;i<loaiSPList.size();i++){
            if (products.get(Integer.parseInt(intent.getStringExtra("id"))).getMa_thuong_hieu()
                    == loaiSPList.get(i).getMa_loai_sp()){
                nameLSP = loaiSPList.get(i).getTen_loai_sp();
                break;
            }
        }
        edtName.setText(products.get(Integer.parseInt(intent.getStringExtra("id"))).getTen_san_pham());
        edtSoluong.setText(String.valueOf(products.get(Integer.parseInt(intent.getStringExtra("id"))).getSo_luong()));
        edtPrice.setText(String.valueOf(products.get(Integer.parseInt(intent.getStringExtra("id"))).getGia_ban()));
        tvThuongHieu.setText(nameTH);
        tvLoaiSanPham.setText(nameLSP);
    }
}