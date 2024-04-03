package com.example.albumlist_to_grid;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.albumlist_to_grid.ImageAdapter;

public class GridActivity extends AppCompatActivity {
    int[][] studentImages = {
            {R.drawable.student1_image1, R.drawable.student1_image2, R.drawable.student1_image3, R.drawable.student3_image2},
            {R.drawable.student2_image1, R.drawable.student2_image2, R.drawable.student2_image3},
            {R.drawable.student3_image1, R.drawable.student3_image2, R.drawable.student3_image3}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);

        GridView gridView = findViewById(R.id.gridView);

        // Lấy tên sinh viên từ intent
        String selectedStudent = getIntent().getStringExtra("student");

        // Hiển thị ảnh của sinh viên đó
        int position = getPositionOfStudent(selectedStudent);
        if (position != -1) {
            gridView.setAdapter(new ImageAdapter(this, studentImages[position]));
        }
/*        else {
            Toast.makeText(this, "Không tìm thấy ảnh của sinh viên", Toast.LENGTH_SHORT).show();
            finish();
        }*/
    }

    private int getPositionOfStudent(String studentName) {
        String[] students = {"Student 1", "Student 2", "Student 3"};
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(studentName)) {
                return i;
            }
        }
        return -1;
    }
}
