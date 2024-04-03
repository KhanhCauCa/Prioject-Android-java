package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MyDBHelper dbHelper = new MyDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidget();

    }
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }
    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }
    private void InitWidget() {
    }
}