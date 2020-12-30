package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowActivity extends AppCompatActivity {
    ImageView img;
    TextView tvName, tvThuongHieu, tvLoaiSp, tvGiaBan, tvSoLuong;
    Button btnBuy, btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        anhXa();
        setData();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void anhXa(){
        img = findViewById(R.id.imgBtShow);
        tvName = findViewById(R.id.edtNameShow);
        tvThuongHieu = findViewById(R.id.tvThuongHieuShow);
        tvLoaiSp = findViewById(R.id.tvLoaiSanPhamShow);
        tvGiaBan = findViewById(R.id.edtPriceShow);
        tvSoLuong = findViewById(R.id.edtSoLuongShow);
        btnback = findViewById(R.id.btnBack);
        btnBuy = findViewById(R.id.btnBuy);
    }
    private void setData(){
        Intent intent = getIntent();
        tvName.setText(intent.getStringExtra("name"));
        tvThuongHieu.setText(intent.getStringExtra("thuonghieu"));
        tvLoaiSp.setText(intent.getStringExtra("loaisp"));
        tvGiaBan.setText(intent.getStringExtra("giaban"));
        tvSoLuong.setText(intent.getStringExtra("soluong"));
 //        tvName.setText(intent.getStringExtra("hinhanh"));
    }
}