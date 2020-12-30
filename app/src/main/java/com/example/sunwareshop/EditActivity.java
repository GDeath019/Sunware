package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.Product;
import com.example.sunwareshop.Database.ThuongHieu;
import com.example.sunwareshop.Realm.ModelBaoHanh;
import com.example.sunwareshop.Realm.ModelLoaiSanPham;
import com.example.sunwareshop.Realm.ModelSanPham;
import com.example.sunwareshop.Realm.ModelThuongHieu;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class EditActivity extends AppCompatActivity {
    EditText edtName, edtPrice, edtSoluong;
    ImageButton img;
    Spinner spnThuongHieu, spnLoaiSanPham, spnBaohanh;
    Button btnSubmit, btnCancel;
    ModelSanPham modelSanPham;
    ModelThuongHieu modelThuongHieu;
    ModelLoaiSanPham modelLoaiSanPham;
    ModelBaoHanh modelBaoHanh;
    Realm getData;
    ArrayList<String> arrThuongHieu, arrLoaiSanPham, arrBaoHanh;
    ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3;
    Long id = Long.valueOf(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
                updateData();
                onBackPressed();
            }
        });
        Intent intent = getIntent();
        id = Long.parseLong(intent.getStringExtra("id"));
        setData();
    }

    private void setData(){
        List<Product> products = modelSanPham.getAll(getData);
        edtName.setText(products.get(Math.toIntExact(id)).getTen_san_pham());
        edtSoluong.setText(String.valueOf(products.get(Math.toIntExact(id)).getSo_luong()));
        edtPrice.setText(String.valueOf(products.get(Math.toIntExact(id)).getGia_ban()));
//        spnLoaiSanPham.setSelection((int) (products.get(0).getMa_loai_san_pham()-1));
//        spnThuongHieu.setSelection((int) (products.get(0).getMa_thuong_hieu()-1));


    }

    private void anhXa(){
        edtName = findViewById(R.id.edtNameEdit);
        edtPrice = findViewById(R.id.edtPriceEdit);
        edtSoluong = findViewById(R.id.edtSoLuongEdit);
        img = findViewById(R.id.imgBtEdit);
        spnThuongHieu = findViewById(R.id.spnThuongHieuEdit);
        spnLoaiSanPham = findViewById(R.id.spnLoaiSanPhamEdit);
        btnSubmit = findViewById(R.id.btnSubmitEdit);
        btnCancel = findViewById(R.id.btnCancelEdit);
        edtSoluong.setEnabled(false);
        spnBaohanh = findViewById(R.id.spnBaoHanhEdit);


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
        arrayAdapter = new ArrayAdapter(EditActivity.this, android.R.layout.simple_list_item_1, arrThuongHieu);
        spnThuongHieu.setAdapter(arrayAdapter);

        List<LoaiSP> dbLoaiSP = modelLoaiSanPham.getAll(getData);
        arrLoaiSanPham = new ArrayList<>();
        for (int i=0;i<dbLoaiSP.size();i++){
            arrLoaiSanPham.add(dbLoaiSP.get(i).getTen_loai_sp());
        }
        arrayAdapter2 = new ArrayAdapter(EditActivity.this, android.R.layout.simple_list_item_1, arrLoaiSanPham);
        spnLoaiSanPham.setAdapter(arrayAdapter2);

        List<BaoHanh> dbBaoHanh = modelBaoHanh.getAll(getData);
        arrBaoHanh = new ArrayList<>();
        for (int i=0;i<dbLoaiSP.size();i++){
            arrBaoHanh.add(String.valueOf(dbBaoHanh.get(i).getThoi_gian()));
        }
        arrayAdapter3 = new ArrayAdapter(EditActivity.this, android.R.layout.simple_list_item_1, arrBaoHanh);
        spnBaohanh.setAdapter(arrayAdapter3);
    }
    private void updateData(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            arrayList.add(edtName.getText().toString());
            arrayList.add(String.valueOf(spnThuongHieu.getSelectedItemPosition()));
            arrayList.add(String.valueOf(spnLoaiSanPham.getSelectedItemPosition()));
            arrayList.add(edtPrice.getText().toString());
            arrayList.add(String.valueOf(spnBaohanh.getSelectedItemPosition()));
//            arrayList.add(edtSoluong.getText().toString());
//        arrayList.add(edtName.getText().toString());
        }catch (Exception e){
            Toast.makeText(EditActivity.this,"Thiếu dữ liệu", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(AddActivity.this, String.valueOf(spnThuongHieu.getSelectedItem().toString()), Toast.LENGTH_SHORT).show();
        try {
            String result = modelSanPham.updateSanpham(id,arrayList);
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