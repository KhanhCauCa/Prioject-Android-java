package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtName;
    RadioButton rdbNam, rdbNu;
    Spinner spinner;
    Button btnGui;

    TextView txtNhan;

    ArrayList<String > arrayListQuocGia = new ArrayList<String>();
    ArrayAdapter<String> adapter1;
    String qg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwitget();
        arrayListQuocGia.add("Việt Nam");
        arrayListQuocGia.add("Trung Quốc");
        arrayListQuocGia.add("Mỹ");
        arrayListQuocGia.add("Nga");
        adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayListQuocGia);
        spinner.setAdapter(adapter1);



        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                String ht = edtName.getText().toString();
                intent.putExtra("HoTen",ht);
                String gt = "";
                if(rdbNam.isChecked()){
                    gt = "Nam";
                }
                else if(rdbNu.isChecked()){
                    gt="Nu";
                }
                intent.putExtra("GioiTinh",gt);

                 qg = spinner.getSelectedItem().toString();
                intent.putExtra("QuocGia",qg);


                startActivity(intent);
            }
        });
    }

    private void initwitget() {
        edtName = (EditText) findViewById(R.id.edtName);
        rdbNam = (RadioButton) findViewById(R.id.rdbNam);
        rdbNu =(RadioButton) findViewById(R.id.rdbNu);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnGui = (Button) findViewById(R.id.btnGui);
        txtNhan = (TextView) findViewById(R.id.txtNhan);

        Intent intent1 =getIntent();
        String qg3 = intent1.getStringExtra("qg");
        txtNhan.setText(qg3);

    }
}