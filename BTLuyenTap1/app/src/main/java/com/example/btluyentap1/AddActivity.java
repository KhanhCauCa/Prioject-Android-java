package com.example.btluyentap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText edt_add_name, edt_add_singer, edt_add_timer;
    Button btn_add_singer, btn_back;
    MySql mySql = new MySql(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        InitWidget();

        btn_add_singer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_add_name.getText().toString();
                String singer = edt_add_singer.getText().toString();
                String timer = edt_add_timer.getText().toString();
                if(name != null && singer != null && timer != null){
                    long kq = mySql.insertSinger(name, singer, timer);
                    if(kq != -1){
                        Toast.makeText(AddActivity.this, "Add Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddActivity.this, "Add Fail", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidget() {
        edt_add_name = findViewById(R.id.edt_add_name);
        edt_add_singer = findViewById(R.id.edt_add_singer);
        edt_add_timer = findViewById(R.id.edt_add_timer);
        btn_add_singer = findViewById(R.id.btn_add_singer);
        btn_back = findViewById(R.id.btn_back);
    }
}