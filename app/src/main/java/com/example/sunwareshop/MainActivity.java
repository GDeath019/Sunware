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
import com.example.sunwareshop.Realm.ModelBaoHanh;

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
        modelBaoHanh = new ModelBaoHanh();
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
                List<BaoHanh> testData = modelBaoHanh.getAll(getData);
                modelBaoHanh.updateBaoHanh(testData.get(position).getMa_bao_hanh());
                addLv();
                return false;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelBaoHanh.addData();
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
}