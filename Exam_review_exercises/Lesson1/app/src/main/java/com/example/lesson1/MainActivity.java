package com.example.lesson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText edt_search;
    ListView lsv_album;
    FloatingActionButton btn_add;
    MySQL db = new MySQL(this);
    private static final int REQUEST_ADD_SONG = 1;

    @Override
    protected void onStart() {
        super.onStart();
        db.openDB();
        updateListView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();

        if(db.getCount() ==0){
            db.insert("Khanh", "Khanh", 2);
            db.insert("Quang", "Quang", 5);
            db.insert("Minh", "Minh", 3);
            db.insert("Toan", "Toan", 3);
            db.insert("Lam", "Lam", 3);
            db.insert("Hoa", "Hoa", 3);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityAdd.class);
                startActivityForResult(intent,REQUEST_ADD_SONG);
            }
        });

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
                filterListView(searchText);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

    }

    private void InitWidget() {
        edt_search = (EditText) findViewById(R.id.edt_search);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        lsv_album = (ListView) findViewById(R.id.lsv_album);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD_SONG && resultCode == RESULT_OK && data != null) {

            // Lấy dữ liệu từ MainActivityAdd
            String name = data.getStringExtra("name");
            String singer = data.getStringExtra("singer");
            float time = data.getFloatExtra("time", 0.0f);
            // Kiểm tra xem Singer có được nhập hay không
            if (singer == null || singer.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập tên ca sĩ.", Toast.LENGTH_SHORT).show();
                return; // Không thực hiện thêm dữ liệu nếu thiếu tên ca sĩ
            }
            // Chèn dữ liệu vào cơ sở dữ liệu
            long result = db.insert(name, singer, time);

            if (result != -1) {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();

                // Cập nhật ListView sau khi thêm dữ liệu mới
                updateListView();
            } else {
                Toast.makeText(MainActivity.this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void updateListView() {
        Cursor cursor = db.DisplayAllSortedByTime();
        // Tạo và thiết lập Adapter cho ListView
        SongAdapter songAdapter = new SongAdapter(this, cursor);
        lsv_album.setAdapter(songAdapter);
    }

    private void filterListView(String searchText) {
        Cursor cursor = db.searchSongsByName(searchText); // Lọc danh sách bài hát theo tên

        if (cursor != null) {
            // Tạo Adapter mới với Cursor đã lọc và cập nhật ListView
            SongAdapter songAdapter = new SongAdapter(this, cursor);
            lsv_album.setAdapter(songAdapter);
        }
    }



}