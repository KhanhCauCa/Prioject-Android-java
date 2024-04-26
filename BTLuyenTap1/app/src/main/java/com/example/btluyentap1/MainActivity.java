package com.example.btluyentap1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edt_search;
    ListView lv_singer;
    ImageButton btn_add;

    MyAdapter adapter;
    List<Song> songs;
    MySql mySql = new MySql(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitWidget();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void InitWidget() {
        edt_search = findViewById(R.id.edt_search);
        lv_singer = findViewById(R.id.list_view_singer);
        btn_add = findViewById(R.id.btn_add);
        songs = new ArrayList<Song>();

        getAllSinger();
    }

    public void getAllSinger(){
        Cursor cursor = mySql.getAll();
        songs.clear();
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MySql.NAME));
            String singer = cursor.getString(cursor.getColumnIndexOrThrow(MySql.SINGER));
            Double timer = cursor.getDouble(cursor.getColumnIndexOrThrow(MySql.TIME));

            Song song = new Song(name, singer, timer);
            songs.add(song);

        }

        adapter = new MyAdapter(this, R.layout.item_singer, songs);
        lv_singer.setAdapter(adapter);
    }



}