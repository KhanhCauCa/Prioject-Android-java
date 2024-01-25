package com.example.java_collection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewMonHoc;
    Spinner spinnerMonHoc;
    EditText edtMonHoc;
    Button buttonOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initwitget();

        final String arr[] = {"Java", "Android", "Data mining",
                              "image processing", "Game" };

        ArrayList<String > arrayListMonHoc = new ArrayList<String>();
        arrayListMonHoc.add("Java");
        arrayListMonHoc.add("Android");
        arrayListMonHoc.add("Data mining");
        arrayListMonHoc.add("image processing");
        arrayListMonHoc.add("Game");

        //Gắn data source vào adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arrayListMonHoc);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayListMonHoc);
        listViewMonHoc.setAdapter(adapter);
        listViewMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListMonHoc.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        spinnerMonHoc.setAdapter(adapter1);
        spinnerMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListMonHoc.get(position), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHocMoi = edtMonHoc.getText().toString();
                if(!monHocMoi.isEmpty()){
                    if (!arrayListMonHoc.contains(monHocMoi)) {
                            arrayListMonHoc.add(monHocMoi);
                            adapter1.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }

    private void Initwitget() {
        listViewMonHoc = (ListView) findViewById(R.id.listViewMonHoc);
        spinnerMonHoc = (Spinner) findViewById(R.id.spinnerMonHoc);
        edtMonHoc = (EditText) findViewById(R.id.edtMonHoc);
        buttonOK = (Button) findViewById(R.id.buttonOK);
    }
}