package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txtHoTen, txtGioiTinh,txtQuocGia;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initwitget();
        Intent intent1 = getIntent();
        String ht1 = intent1.getStringExtra("HoTen");
        String gt1 = intent1.getStringExtra("GioiTinh");
        String qg1 = intent1.getStringExtra("QuocGia");
        txtHoTen.setText(ht1);
        txtQuocGia.setText(qg1);
        txtGioiTinh.setText(gt1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);


                String qg2 = txtQuocGia.getText().toString();
                if (qg2.equals("Việt Nam")) {
                    qg2 = "Thành công";
                }else qg2 = "Thất bại";
                intent.putExtra("qg",qg2);

                startActivity(intent);

            }
        });
    }


    private void initwitget() {
        txtHoTen = (TextView) findViewById(R.id.txtHoTen);
        txtGioiTinh = (TextView) findViewById(R.id.txtGioiTinh);
        txtQuocGia = (TextView) findViewById(R.id.txtQuocGia);
        btnBack = (Button) findViewById(R.id.btnBack);
    }

}
