package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.NumberFormatter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtTong;
    EditText edta, edtb;
    Button btnTinh;
    ImageView imgAnh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a + b;

                Toast.makeText(MainActivity.this, String.valueOf(c), Toast.LENGTH_SHORT).show();
                txtTong.setText(String.valueOf(c));
                imgAnh.setImageResource(R.drawable.hoi);
            }
        });
    }
    private void InitWidgets(){
        txtTong = (TextView) findViewById(R.id.txtTong);
        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        btnTinh = (Button) findViewById(R.id.btnTinh);
        imgAnh = (ImageView) findViewById(R.id.imgAnh);
    }
}