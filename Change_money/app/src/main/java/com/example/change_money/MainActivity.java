package com.example.change_money;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtTien;
    RadioButton rdbUSD, rdbEUR, rdbCNY;
    CheckBox ckbChietKhau;
    Button btnChange;

    TextView txtTien, txtTen;

    Spinner spnLoaiTien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwitget();

        ArrayList<String> arrayListLoaiTien = new ArrayList<String>();
        arrayListLoaiTien.add("EUR");
        arrayListLoaiTien.add("USD");
        arrayListLoaiTien.add("CNY");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayListLoaiTien);
        spnLoaiTien.setAdapter(adapter);
        spnLoaiTien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Double money = Double.parseDouble(edtTien.getText().toString());
                Double result = 0.0;
                String s = "";

                if (position == 0) { // "EUR"
                    result = money * 0.000037;
                    s = "EUR";
                } else if (position == 1) { // "USD"
                    result = money * 0.000041;
                    s = "USD";
                } else if (position == 2) { // "CNY"
                    result = money * 0.00029;
                    s = "CNY";
                }

                DecimalFormat df = new DecimalFormat("#.###");
                double result1 = Double.parseDouble(df.format(result));
                txtTien.setText(String.valueOf(result1));

                txtTen.setText(s);
                Toast.makeText(MainActivity.this, arrayListLoaiTien.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


       /* btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double money = Double.parseDouble(edtTien.getText().toString());
                Double result = 0.0;
                String s = "";
                if (rdbEUR.isChecked()) {
                    result = money * 0.000037;
                    s = "EUR";
                }

                if (rdbUSD.isChecked()) {
                    result = money * 0.000041;
                    s = "USD";
                }

                if (rdbCNY.isChecked()) {
                    result = money * 0.00029;
                    s = "CNY";
                }
               DecimalFormat df = new DecimalFormat("#.###");
                double result1 = Double.parseDouble(df.format(result));
                txtTien.setText(String.valueOf(result1));


                txtTen.setText(s);

            }
        });*/
    }

    private void initwitget() {
        edtTien = (EditText) findViewById(R.id.edtTien);
        rdbEUR = (RadioButton) findViewById(R.id.rdbEUR);
        rdbUSD = (RadioButton) findViewById(R.id.rdbUSD);
        rdbCNY = (RadioButton) findViewById(R.id.rdbCNY);
        ckbChietKhau = (CheckBox) findViewById(R.id.ckbChietKhau);
        btnChange = (Button) findViewById(R.id.btnChange);
        txtTien = (TextView) findViewById(R.id.txtTien);
        txtTen = (TextView) findViewById(R.id.txtTen);
        spnLoaiTien = (Spinner) findViewById(R.id.spnLoaiTien);
    }
}