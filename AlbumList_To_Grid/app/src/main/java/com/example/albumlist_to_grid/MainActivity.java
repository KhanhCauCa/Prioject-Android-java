package com.example.albumlist_to_grid;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] students = {"Student 1", "Student 2", "Student 3"}; // Danh sách sinh viên

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStudent = students[position]; // Lấy tên sinh viên đã chọn
                Intent intent = new Intent(MainActivity.this, GridActivity.class);
                intent.putExtra("student", selectedStudent); // Truyền tên sinh viên qua activity mới
                startActivity(intent);
            }
        });
    }
}