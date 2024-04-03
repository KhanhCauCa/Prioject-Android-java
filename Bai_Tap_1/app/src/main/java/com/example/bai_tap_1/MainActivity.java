package com.example.bai_tap_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstSinhVien;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> sinhVienArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();

        adapter = new SinhVienAdapter(MainActivity.this, R.layout.item, sinhVienArrayList);
        lstSinhVien.setAdapter(adapter);
        lstSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SinhVien selectedSinhVien = (SinhVien) lstSinhVien.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("student", selectedSinhVien.getName());
                startActivity(intent);
            }
        });
    }

    private void initWidget() {
        lstSinhVien = (ListView) findViewById(R.id.lstSinhVien);
        sinhVienArrayList.add(new SinhVien("Khanh",2003));
        sinhVienArrayList.add(new SinhVien("XuanTrang",2003));
        sinhVienArrayList.add(new SinhVien("Nghien",2003));
        sinhVienArrayList.add(new SinhVien("Minh",2003));
        sinhVienArrayList.add(new SinhVien("Tuấn",2003));
        sinhVienArrayList.add(new SinhVien("Huyền",2003));
        sinhVienArrayList.add(new SinhVien("Duyệt",2003));
        sinhVienArrayList.add(new SinhVien("Việt",2003));
        sinhVienArrayList.add(new SinhVien("Tú",2003));
        sinhVienArrayList.add(new SinhVien("Viên",2003));
        sinhVienArrayList.add(new SinhVien("Thanh",2003));
        sinhVienArrayList.add(new SinhVien("Quảng",2003));

    }
}