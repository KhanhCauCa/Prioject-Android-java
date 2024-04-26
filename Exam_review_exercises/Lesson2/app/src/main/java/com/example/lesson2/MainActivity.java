package com.example.lesson2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_search;
    ImageFilterButton btn_add;
    ListView lst_sinhVien;
    ArrayList<SinhVien> lstSinhVien;
    SinhVienAdapter adapter;

    MySQL db = new MySQL(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        db.deleteAllData();
        if(db.getCount() == 0){
            db.insert("GHA01844", "Khánh", 10,8,9);
            db.insert("GHA01842", "Trang", 7,8,2);
            db.insert("GHA01843", "Dung", 10,4,9);
        }

        //Thêm dữ liệu từ csdl vào listView
        Cursor c = db.DisplayAll();
        lstSinhVien.clear();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(db.getID()));
            String name = c.getString(c.getColumnIndexOrThrow(db.getNAME()));
            String identificationNumber = c.getString(c.getColumnIndexOrThrow(db.getIDENTIFICATION_NUMBER()));
            float math = c.getFloat(c.getColumnIndexOrThrow(db.getMATH()));
            float physical = c.getFloat(c.getColumnIndexOrThrow(db.getPHYSICAL()));
            float chemistry = c.getFloat(c.getColumnIndexOrThrow(db.getCHEMISTRY()));

            SinhVien sv = new SinhVien(id, identificationNumber, name, math, physical, chemistry);
            lstSinhVien.add(sv);
        }

        adapter = new SinhVienAdapter(this, R.layout.item_student, lstSinhVien);
        lst_sinhVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lst_sinhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ra sinh viên được chọn
                SinhVien selectedSinhVien = lstSinhVien.get(position);

                // Hiển thị AlertDialog cho người dùng chọn tác vụ
                showOptionsDialog(selectedSinhVien);

                return true; // Đã xử lý sự kiện long click
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
//                searchByName(searchText);
                searchByIdentificationNumber(searchText);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityAdd.class);
                startActivity(intent);
            }
        });
    }

    private void searchByName(String searchText) {
        Cursor cursor = db.searchByName(searchText);

        lstSinhVien.clear();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(db.getID()));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(db.getNAME()));
                String identificationNumber = cursor.getString(cursor.getColumnIndexOrThrow(db.getIDENTIFICATION_NUMBER()));
                float math = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getMATH()));
                float physical = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getPHYSICAL()));
                float chemistry = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getCHEMISTRY()));

                SinhVien sv = new SinhVien(id, identificationNumber, name, math, physical, chemistry);
                lstSinhVien.add(sv);
            }

            // Cập nhật lại adapter để hiển thị danh sách sinh viên tìm được
            adapter.notifyDataSetChanged();
        }

        // Đóng cursor sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
    }

    private void searchByIdentificationNumber(String searchText) {
        Cursor cursor = db.searchByIdentificationNumber(searchText);

        lstSinhVien.clear();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(db.getID()));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(db.getNAME()));
                String identificationNumber = cursor.getString(cursor.getColumnIndexOrThrow(db.getIDENTIFICATION_NUMBER()));
                float math = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getMATH()));
                float physical = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getPHYSICAL()));
                float chemistry = cursor.getFloat(cursor.getColumnIndexOrThrow(db.getCHEMISTRY()));

                SinhVien sv = new SinhVien(id, identificationNumber, name, math, physical, chemistry);
                lstSinhVien.add(sv);
            }

            // Cập nhật lại adapter để hiển thị danh sách sinh viên tìm được
            adapter.notifyDataSetChanged();
        }

        // Đóng cursor sau khi sử dụng
        if (cursor != null) {
            cursor.close();
        }
    }

    private void showOptionsDialog(final SinhVien sinhVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");

        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MainActivity.this, MainActivityEdit.class);
//                intent.putExtra("SinhVien", sinhVien);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
//                deleteSinhVien(sinhVien);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void onStart() {
        super.onStart();
        db.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }


    private void InitWidgets() {
        edt_search = (EditText) findViewById(R.id.edt_search);
        btn_add = (ImageFilterButton) findViewById(R.id.btn_add);
        lst_sinhVien = (ListView) findViewById(R.id.lst_sinhVien);
        lstSinhVien = new ArrayList<>();
    }
}