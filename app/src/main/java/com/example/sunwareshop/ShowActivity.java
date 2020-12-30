package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sunwareshop.Database.BaoHanh;
import com.example.sunwareshop.Database.LoaiSP;
import com.example.sunwareshop.Database.ThuongHieu;
import com.example.sunwareshop.Realm.ModelBaoHanh;
import com.example.sunwareshop.Realm.ModelLoaiSanPham;
import com.example.sunwareshop.Realm.ModelThuongHieu;

import org.w3c.dom.Text;

import java.util.List;

import io.realm.Realm;

public class ShowActivity extends AppCompatActivity {
    ImageView img;
    TextView tvName, tvThuongHieu, tvLoaiSp, tvGiaBan, tvSoLuong, tvBaoHanh;
    Button btnBuy, btnback;
    ModelThuongHieu modelThuongHieu;
    ModelLoaiSanPham modelLoaiSanPham;
    ModelBaoHanh modelBaoHanh;
    Realm getData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getData = Realm.getDefaultInstance();
        Intent data = getIntent();
        anhXa();
        setData();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowActivity.this, BuyActivity.class);
                //id la vi tri
                intent.putExtra("id", data.getStringExtra("id"));
                startActivity(intent);
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
        tvBaoHanh = findViewById(R.id.tvBaoHanhShow);

        //////
        modelBaoHanh = new ModelBaoHanh();
        modelLoaiSanPham = new ModelLoaiSanPham();
        modelThuongHieu = new ModelThuongHieu();
    }
    private void setData(){
        Intent intent = getIntent();
        List<BaoHanh> baoHanhs = modelBaoHanh.getAll(getData);
        List<LoaiSP> loaiSPList = modelLoaiSanPham.getAll(getData);
        List<ThuongHieu> thuongHieuList = modelThuongHieu.getAll(getData);
        String bh = "";
        String lsp = "";
        String th = "";
        for (int i=0;i<baoHanhs.size();i++){
            if ((baoHanhs.get(i).getMa_bao_hanh())==Integer.parseInt(intent.getStringExtra("baohanh"))){
                bh = String.valueOf(baoHanhs.get(i).getThoi_gian());
                break;
            }
        }
        for (int i=0;i<loaiSPList.size();i++){
            if ((loaiSPList.get(i).getMa_loai_sp())==Integer.parseInt(intent.getStringExtra("loaisp"))){
                lsp = String.valueOf(loaiSPList.get(i).getTen_loai_sp());
                break;
            }
        }
        for (int i=0;i<thuongHieuList.size();i++){
            if ((thuongHieuList.get(i).getMa_thuong_hieu())==Integer.parseInt(intent.getStringExtra("thuonghieu"))){
                th = String.valueOf(thuongHieuList.get(i).getTen_thuong_hieu());
                break;
            }
        }
        tvName.setText(intent.getStringExtra("name"));
        tvThuongHieu.setText(th);
        tvLoaiSp.setText(lsp);
        tvGiaBan.setText(intent.getStringExtra("giaban"));
        tvSoLuong.setText(intent.getStringExtra("soluong"));
        tvBaoHanh.setText(bh);
 //        tvName.setText(intent.getStringExtra("hinhanh"));
    }
}