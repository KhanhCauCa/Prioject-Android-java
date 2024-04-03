package com.example.bai_tap_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    GridView gridView_imgae ;

    int[][] studentImages = {
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
                    R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background
                    ,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background},
            {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background}

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ititWidget();

        // Lấy tên sinh viên từ intent
        String selectedStudent = getIntent().getStringExtra("student");

        // Hiển thị ảnh của sinh viên đó
        int position = getPositionOfStudent(selectedStudent);

        if (position == -1) {
            Toast.makeText(this, "Không tìm thấy ảnh của sinh viên", Toast.LENGTH_SHORT).show();
            finish();
        } else gridView_imgae.setAdapter(new ImageAdapter(this, studentImages[position]));
    }

    private int getPositionOfStudent(String studentName) {
        String[] students = {"Khanh", "XuanTrang", "Nghien", "Minh", "Tuấn", "Huyền", "Duyệt", "Việt", "Tú", "Viên", "Thanh", "Quảng"};
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(studentName)) {
                return i;
            }
        }
        return -1;
    }

    private void ititWidget() {
        gridView_imgae = (GridView) findViewById(R.id.gridView_imgae);
    }
}