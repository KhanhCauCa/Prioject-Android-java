package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox ckbTheThao, ckbDuLich;
    RadioButton rdbNam, rdbNu;
    Button btn;
    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwitget();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckbTheThao.isChecked()){
                    s  += ckbTheThao.getText().toString();
                }
                if(ckbDuLich.isChecked()){
                    s  += ckbDuLich.getText().toString();
                }
                if(rdbNam.isChecked()){
                    s  += rdbNam.getText().toString();
                }
                if(rdbNu.isChecked()){
                    s  += rdbNu.getText().toString();
                }
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void initwitget() {
        ckbTheThao = (CheckBox) findViewById(R.id.ckbTheThao);
        ckbDuLich = (CheckBox) findViewById(R.id.ckbDuLich);
        rdbNam = (RadioButton) findViewById(R.id.rdbNam);
        rdbNu = (RadioButton) findViewById(R.id.rdbNu);
    }
}