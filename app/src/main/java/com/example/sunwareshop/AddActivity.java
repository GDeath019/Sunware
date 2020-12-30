package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.ThuongHieu;
import com.example.sunwareshop.Realm.ModelBaoHanh;
import com.example.sunwareshop.Realm.ModelLoaiSanPham;
import com.example.sunwareshop.Realm.ModelSanPham;
import com.example.sunwareshop.Realm.ModelThuongHieu;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class AddActivity extends AppCompatActivity {
    EditText edtName, edtPrice, edtSoluong;
    ImageButton img;
    Spinner spnThuongHieu, spnLoaiSanPham, spnBaoHanh;
    Button btnSubmit, btnCancel;
    ModelSanPham modelSanPham;
    ModelThuongHieu modelThuongHieu;
    ModelLoaiSanPham modelLoaiSanPham;
    ModelBaoHanh modelBaoHanh;
    Realm getData;
    ArrayList<String> arrThuongHieu, arrLoaiSanPham, arrBaoHanh;
    ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getData = Realm.getDefaultInstance();

        anhXa();
        addSpn();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                onBackPressed();
            }
        });
    }
    private void anhXa(){
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtSoluong = findViewById(R.id.edtSoLuong);
        img = findViewById(R.id.imgBt);
        spnThuongHieu = findViewById(R.id.spnThuongHieu);
        spnLoaiSanPham = findViewById(R.id.spnLoaiSanPham);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
        spnBaoHanh = findViewById(R.id.spnBaoHanh);

        edtSoluong.setEnabled(false);
        ///// model

        modelSanPham = new ModelSanPham();
        modelThuongHieu = new ModelThuongHieu();
        modelLoaiSanPham = new ModelLoaiSanPham();
        modelBaoHanh = new ModelBaoHanh();
    }
    private void addSpn(){
        List<ThuongHieu> dbThuongHieu = modelThuongHieu.getAll(getData);
        arrThuongHieu = new ArrayList<>();
        for (int i=0;i<dbThuongHieu.size();i++){
            arrThuongHieu.add(dbThuongHieu.get(i).getTen_thuong_hieu());
        }
        arrayAdapter = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_1, arrThuongHieu);
        spnThuongHieu.setAdapter(arrayAdapter);

        List<LoaiSP> dbLoaiSP = modelLoaiSanPham.getAll(getData);
        arrLoaiSanPham = new ArrayList<>();
        for (int i=0;i<dbLoaiSP.size();i++){
            arrLoaiSanPham.add(dbLoaiSP.get(i).getTen_loai_sp());
        }
        arrayAdapter2 = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_1, arrLoaiSanPham);
        spnLoaiSanPham.setAdapter(arrayAdapter2);

        List<BaoHanh> dbBaoHanh = modelBaoHanh.getAll(getData);
        arrBaoHanh = new ArrayList<>();
        for (int i=0;i<dbLoaiSP.size();i++){
            arrBaoHanh.add(String.valueOf(dbBaoHanh.get(i).getThoi_gian()));
        }
        arrayAdapter3 = new ArrayAdapter(AddActivity.this, android.R.layout.simple_list_item_1, arrBaoHanh);
        spnBaoHanh.setAdapter(arrayAdapter3);
    }
    private void insertData(){
        ArrayList<String> arrayList = new ArrayList<>();
       try {
           arrayList.add(edtName.getText().toString());
           arrayList.add(String.valueOf(spnThuongHieu.getSelectedItemPosition()));
           arrayList.add(String.valueOf(spnLoaiSanPham.getSelectedItemPosition()));
           arrayList.add(edtPrice.getText().toString());
           arrayList.add(String.valueOf(spnBaoHanh.getSelectedItemPosition()));
//        arrayList.add(edtName.getText().toString());
       }catch (Exception e){
           Toast.makeText(AddActivity.this,"Thiếu dữ liệu", Toast.LENGTH_SHORT).show();
       }
//        Toast.makeText(AddActivity.this, String.valueOf(spnThuongHieu.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
        try {
            String result = modelSanPham.insertSanPham(arrayList);
            if (result == "Success"){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}