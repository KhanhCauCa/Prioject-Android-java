package com.example.homework_with_sqlite_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txt_Load;
    Button btn_Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initWidget();
        Intent intent = getIntent();
        if(intent != null) {
            String allData = intent.getStringExtra("AllData");
            if(allData != null && !allData.isEmpty()) {
                txt_Load.setText(allData);
            } else {
                txt_Load.setText("No data available");
            }
        } else {
            txt_Load.setText("No data available");
        }
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidget() {
        txt_Load = (TextView) findViewById(R.id.txt_Load);
        btn_Back = (Button) findViewById(R.id.btn_Back);
    }
}