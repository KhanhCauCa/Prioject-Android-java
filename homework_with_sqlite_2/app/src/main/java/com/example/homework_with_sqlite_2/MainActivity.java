package com.example.homework_with_sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_ID, edt_Name, edt_Number;
    Button btn_Insert, btn_Delete, btn_Update,btn_Load;
    ListView lst_Product;
    MyDBHelper dbHelper = new MyDBHelper(this);

    ArrayList<String> arrayListProduct = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();
        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultInsert = dbHelper.insert(
                        edt_Name.getText().toString(),
                        edt_Number.getText().toString()
                );
                if(resultInsert == -1 ){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.DisplayAll();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                StringBuilder data = new StringBuilder();
                for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getID()));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getNAME()));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getNUMBER()));

                    data.append(id).append("-").append(name).append("-").append(number).append("\n");
                }
                String allData = data.toString();
                intent.putExtra("AllData", allData);
                startActivity(intent);
            }
        });
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultUpdate = dbHelper.update(
                        Integer.parseInt(edt_ID.getText().toString()),
                        edt_Name.getText().toString(),
                        edt_Number.getText().toString()
                );
                if(resultUpdate == -1 ){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultDelete = dbHelper.delete(
                        Integer.parseInt(edt_ID.getText().toString())
                );
                if(resultDelete == -1){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
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
    private void InitWidget() {
        edt_ID = (EditText) findViewById(R.id.edt_ID);
        edt_Name = (EditText) findViewById(R.id.edt_Name);
        edt_Number = (EditText) findViewById(R.id.edt_Number);
        btn_Update = (Button) findViewById(R.id.btn_Update);
        btn_Insert = (Button) findViewById(R.id.btn_Insert);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);
        btn_Load = (Button) findViewById(R.id.btn_Load);
    }
}