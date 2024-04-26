package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityAdd extends AppCompatActivity {

    EditText edt_nameSong2,edt_singer2,edt_time2;
    Button btn_add2,btn_back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        InitWidget();
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdd.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    String name = edt_nameSong2.getText().toString().trim();
                    String singer = edt_singer2.getText().toString().trim();
                    float time = Float.parseFloat(edt_time2.getText().toString().trim());
                    Intent intent = new Intent();
                    intent.putExtra("name" ,name);
                    intent.putExtra("singer" ,singer);
                    intent.putExtra("time" ,time);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }

    private void InitWidget() {
        edt_nameSong2 = (EditText) findViewById(R.id.edt_nameSong2);
        edt_singer2 = (EditText) findViewById(R.id.edt_singer2);
        edt_time2 = (EditText) findViewById(R.id.edt_time2);
        btn_add2 = (Button) findViewById(R.id.btn_add2);
        btn_back2 = (Button) findViewById(R.id.btn_back2);
    }

    private boolean check() {
        String name = edt_nameSong2.getText().toString().trim();
        String singer = edt_singer2.getText().toString().trim();
        String time = edt_time2.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (singer.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập ca sĩ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (time.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập thời gian", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isNumeric(time)) {
            Toast.makeText(MainActivityAdd.this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(isNumeric(time)){
            float time2 = Float.parseFloat(time);
            if (time2 < 0) {
                Toast.makeText(MainActivityAdd.this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str); // Hoặc Integer.parseInt(str) nếu bạn muốn kiểm tra số nguyên
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}