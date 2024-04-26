package com.example.lesson2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivityEdit extends AppCompatActivity {
    EditText edt_edit_name,edt_edit_identification_number,edt_edit_math,edt_edit_physical,edt_edit_chemistry;
    Button btn_back2,btn_edit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);
        InitWidget();
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityEdit.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void InitWidget() {
        edt_edit_name =(EditText) findViewById(R.id.edt_edit_name);
        edt_edit_identification_number =(EditText) findViewById(R.id.edt_edit_identification_number);
        edt_edit_math =(EditText) findViewById(R.id.edt_edit_math);
        edt_edit_physical =(EditText) findViewById(R.id.edt_edit_physical);
        edt_edit_chemistry =(EditText) findViewById(R.id.edt_edit_chemistry);
        btn_back2 = (Button) findViewById(R.id.btn_back3);
        btn_edit2 = (Button) findViewById(R.id.btn_add3);

    }
}