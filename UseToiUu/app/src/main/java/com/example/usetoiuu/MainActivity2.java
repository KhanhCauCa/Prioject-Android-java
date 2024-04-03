package com.example.usetoiuu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btn_Tinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_Tinh = (Button) findViewById(R.id.btn_Tinh);
        Intent intent = getIntent();
        btn_Tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = intent.getIntExtra("a", -1);
                int b = intent.getIntExtra("b", -1);
                int c = a + b;
                intent.putExtra("tong", c);
                setResult(33, intent);
                finish();



            }
        });
    }
}