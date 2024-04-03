package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Hinhanh> arrayListImage;
    HinhanhAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initwitget();
//        final String[] c = new String[]{"A","B", "C","D","E","F","G","H","I","J","K","L","M"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,c);
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,c[position],Toast.LENGTH_SHORT).show();
//
//            }
//        });

        adapter = new HinhanhAdapter(MainActivity.this, R.layout.donghinhanh,arrayListImage);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListImage.get(position).getTen(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Initwitget() {
    gridView = (GridView) findViewById(R.id.gridView);
    arrayListImage = new ArrayList<>();
    arrayListImage.add(new Hinhanh("Hinh so 1",R.drawable.hinh1));
    arrayListImage.add(new Hinhanh("Hinh so 2",R.drawable.hinh2));
    arrayListImage.add(new Hinhanh("Hinh so 3",R.drawable.hinh3));

    }
}