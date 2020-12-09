package com.example.sunwareshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData = Realm.getDefaultInstance();
        modelBaoHanh = new ModelBaoHanh();
        List<BaoHanh> testData = modelBaoHanh.getAll(getData);
        setTitle("Sunware");
        initRealM();
        anhXa();
        addLv();
    }

    private void addLv() {
        List<BaoHanh> dbRealmList = modelBaoHanh.getAll(getData);
        final ArrayList<String> arr = new ArrayList<>();
        for (int i=0;i<dbRealmList.size();i++){
            arr.add(dbRealmList.get(i).getMa_bao_hanh()+"\n   "+dbRealmList.get(i).getThoi_gian());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arr);
        lv1.setAdapter(arrayAdapter);
    }

    private void anhXa() {
        lv1 = findViewById(R.id.Lv1);
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