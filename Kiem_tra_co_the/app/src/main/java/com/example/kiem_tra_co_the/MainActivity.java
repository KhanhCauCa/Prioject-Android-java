package com.example.kiem_tra_co_the;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edtChieuCao, edtCanNang;
    TextView txtBMI, txtTrangThai;
    ImageView imageView;
    Button btnKt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();
        btnKt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double h, w;
                h = Double.parseDouble(edtChieuCao.getText().toString());
                w = Double.parseDouble(edtCanNang.getText().toString());
                double BMI = w / (h * h);
                String s;
                if (BMI < 15) {
                    s = "Very severly underweight";
                    imageView.setImageResource(R.drawable.fat);
                } else if (BMI < 16) {
                    s = "Serverly underweight ";
                    imageView.setImageResource(R.drawable.fat);
                } else {
                    s = "Very serverly obse";
                    imageView.setImageResource(R.drawable.thin);
                }


                DecimalFormat df = new DecimalFormat("#.#");
                double BMI1 = Double.parseDouble(df.format(BMI));
                txtBMI.setText(String.valueOf(BMI1));
                txtTrangThai.setText(s);

            }
        });
    }

    private void InitWidgets() {
        txtBMI = (TextView) findViewById(R.id.txtBMI);
        txtTrangThai = (TextView) findViewById(R.id.txtTrangThai);
        edtChieuCao = (EditText) findViewById(R.id.edtChieuCao);
        edtCanNang = (EditText) findViewById(R.id.edtCanNang);
        btnKt = (Button) findViewById(R.id.btnKt);
        imageView = (ImageView) findViewById(R.id.imageView);
    }
}