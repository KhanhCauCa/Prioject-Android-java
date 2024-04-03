package com.example.use_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edt_ID, edt_NamSinh, edt_HoTen;
    Button btn_Insert, btn_Load, btn_Update,btn_Delete,btn_Search;
    TextView txt_Data;
    ListView lst_SinhVien;

    ArrayList<SinhVien> arrayListSinhVien = new ArrayList<SinhVien>();
    SinhVienAdapter adapter;

    MyDBHelper dbHelper = new MyDBHelper(this);


    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        adapter = new SinhVienAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arrayListSinhVien);
        lst_SinhVien.setAdapter(adapter);

        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultInsert = dbHelper.Insert(
                        edt_HoTen.getText().toString(),
                        Integer.parseInt(edt_NamSinh.getText().toString())
                );
                if(resultInsert == -1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder buffer = new StringBuilder();
                Cursor cursor = dbHelper.DisplayAll();
                for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext() ){
                    buffer.append(" ");
                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getID())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getNAME())));
                    buffer.append("-");
                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getYEAROB())));
                }
                txt_Data.setText(buffer);

            }
        });
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultUpdate = dbHelper.update(
                        Integer.parseInt(edt_ID.getText().toString()),
                        edt_HoTen.getText().toString(),
                        Integer.parseInt(edt_NamSinh.getText().toString())
                );
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultDelete = dbHelper.delete(Integer.parseInt(edt_ID.getText().toString()));
                if(resultDelete == -1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    private void InitWidgets() {
        edt_ID = (EditText) findViewById(R.id.edt_ID);
        edt_HoTen = (EditText) findViewById(R.id.edt_HoTen);
        edt_NamSinh = (EditText) findViewById(R.id.edt_NamSinh);
        btn_Insert = (Button) findViewById(R.id.btn_Insert);
        btn_Load = (Button) findViewById(R.id.btn_Load);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);
        btn_Update = (Button) findViewById(R.id.btn_Update);
        btn_Search = (Button) findViewById(R.id.btn_Search);
        lst_SinhVien = (ListView) findViewById(R.id.lst_SinhVien) ;
        txt_Data = (TextView) findViewById(R.id.txt_Data);
        arrayListSinhVien = new ArrayList<>();
        arrayListSinhVien.add(new SinhVien(1,"XuanTrang",2003));
        arrayListSinhVien.add(new SinhVien(2,"Khanh",2003));
        arrayListSinhVien.add(new SinhVien(3,"Nghien",2003));

    }

}