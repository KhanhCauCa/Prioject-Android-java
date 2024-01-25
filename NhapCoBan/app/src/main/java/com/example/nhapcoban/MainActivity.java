package com.example.nhapcoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtTen,edtDonGia,edtSoLuong;
    RadioButton rdbNam, rdbNu;
    CheckBox ckbKhachVIP;

    Button btnThanhToan;
    TextView txtThanhTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    InitWitget();
    btnThanhToan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int sl = Integer.parseInt(edtSoLuong.getText().toString());
            double dg = Double.parseDouble(edtDonGia.getText().toString());
            double result = sl * dg;

            if(rdbNu.isChecked() || ckbKhachVIP.isChecked() )
                txtThanhTien.setText(String.valueOf(result * 0.9));
//                Toast.makeText(MainActivity.this,String.valueOf(result),Toast.LENGTH_SHORT).show();
            txtThanhTien.setText(String.valueOf(result));
        }
    });
    }

    private void InitWitget() {
        edtTen = (EditText) findViewById(R.id.edtTen);
        edtDonGia = (EditText) findViewById(R.id.edtDonGia);
        edtSoLuong = (EditText) findViewById(R.id.edtSoLuong);
        rdbNam = (RadioButton) findViewById(R.id.rdbNam);
        rdbNam = (RadioButton) findViewById(R.id.rdbNu);
        ckbKhachVIP = (CheckBox) findViewById(R.id.ckbKhachVIP);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        txtThanhTien = (TextView) findViewById(R.id.txtThanhTien);
    }
}