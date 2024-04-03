package com.example.homework_with_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt_HoTen, edt_ID, edt_NamSinh;
    Button btn_Insert, btn_Delete, btn_Update,btn_Load;
    TextView txt_KQ;
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
        InitWidget();

        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultInsert = dbHelper.insert(
                        edt_HoTen.getText().toString(),
                        Integer.parseInt(edt_NamSinh.getText().toString())
                );
                if(resultInsert == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer buffer = new StringBuffer();
                Cursor cursor = dbHelper.DisplayAll();
                for(cursor.moveToNext(); !cursor.isAfterLast(); cursor.moveToNext()){
                    buffer.append(" ");
                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getID())));
                    buffer.append("-");

                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getNAME())));
                    buffer.append("-");

                    buffer.append(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getYEAROB())));
                }
                txt_KQ.setText(buffer);
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
                if(resultUpdate == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long  resultDelete = dbHelper.delete(Integer.parseInt(edt_ID.getText().toString()));
                if(resultDelete == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void InitWidget() {
       edt_ID = (EditText) findViewById(R.id.edt_ID);
       edt_HoTen = (EditText) findViewById(R.id.edt_HoTen);
       edt_NamSinh = (EditText) findViewById(R.id.edt_NamSinh);
       btn_Update = (Button) findViewById(R.id.btn_Update);
       btn_Insert = (Button) findViewById(R.id.btn_Insert);
       btn_Delete = (Button) findViewById(R.id.btn_Delete);
       btn_Load = (Button) findViewById(R.id.btn_Load);
       txt_KQ =  (TextView) findViewById(R.id.txt_KQ);

    }
}