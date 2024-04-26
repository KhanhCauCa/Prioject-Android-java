package com.example.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityAdd extends AppCompatActivity {
    EditText edt_add_name,edt_add_identification_number,edt_add_math,edt_add_physical,edt_add_chemistry;
    Button btn_back3,btn_add3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add);
        InitWidget();
        btn_back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdd.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check()){
                    String identification_number = edt_add_identification_number.getText().toString().trim();
                    String name = edt_add_name.getText().toString().trim();
                    float math = Float.parseFloat(edt_add_math.getText().toString().trim());
                    float physical = Float.parseFloat(edt_add_physical.getText().toString().trim());
                    float chemistry = Float.parseFloat(edt_add_chemistry.getText().toString().trim());


                    Intent intent = new Intent();
                    intent.putExtra("identification_number" ,identification_number);
                    intent.putExtra("name" ,name);
                    intent.putExtra("math" ,math);
                    intent.putExtra("physical" ,physical);
                    intent.putExtra("chemistry" ,chemistry);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
    private void InitWidget() {
        edt_add_name =(EditText) findViewById(R.id.edt_add_name);
        edt_add_identification_number =(EditText) findViewById(R.id.edt_add_identification_number);
        edt_add_math =(EditText) findViewById(R.id.edt_add_math);
        edt_add_physical =(EditText) findViewById(R.id.edt_add_physical);
        edt_add_chemistry =(EditText) findViewById(R.id.edt_add_chemistry);
        btn_back3 = (Button) findViewById(R.id.btn_back3);
        btn_add3 = (Button) findViewById(R.id.btn_add3);

    }
    private boolean check() {
        String identification_number = edt_add_identification_number.getText().toString().trim();
        String name = edt_add_name.getText().toString().trim();
        String math = edt_add_math.getText().toString().trim();
        String physical = edt_add_physical.getText().toString().trim();
        String chemistry = edt_add_chemistry.getText().toString().trim();

        //Kiểm tra số báo danh
        if (identification_number.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập số báo danh", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Kiểm tra tên
        if (name.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập tên sinh viên", Toast.LENGTH_SHORT).show();
            return false;
        }

        //Kểm tra điểm toán
        if (math.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập điểm toán", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isNumeric(math)) {
            Toast.makeText(MainActivityAdd.this, "Điểm toán nhập không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isNumeric(math)) {
            float score = Float.parseFloat(math);
            if (score < 0) {
                Toast.makeText(MainActivityAdd.this, "Điểm toán nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        //Kiểm tra điểm lý
        if (physical.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập điểm lý", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isNumeric(physical)) {
            Toast.makeText(MainActivityAdd.this, "Điểm lý nhập không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isNumeric(physical)) {
            float score = Float.parseFloat(physical);
            if (score < 0) {
                Toast.makeText(MainActivityAdd.this, "Điểm lý nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        //Kiểm tra điểm hóa
        if (chemistry.isEmpty()) {
            Toast.makeText(MainActivityAdd.this, "Vui lòng nhập điểm hóa", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isNumeric(chemistry)) {
            Toast.makeText(MainActivityAdd.this, "Điểm hóa nhập không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(isNumeric(chemistry)) {
            float score = Float.parseFloat(chemistry);
            if (score < 0) {
                Toast.makeText(MainActivityAdd.this, "Điểm hóa nhập không hợp lệ", Toast.LENGTH_SHORT).show();
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