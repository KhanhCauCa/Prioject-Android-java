package com.example.lam_voi_linearlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitWidgets();

    }

    private void InitWidgets() {
        textView = (TextView) findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuGreen){
            textView.setBackgroundColor(Color.GREEN);
        }

        if (item.getItemId() == R.id.mnuRed){
            textView.setBackgroundColor(Color.RED);
        }
        if (item.getItemId() == R.id.mnuYellow){
            textView.setBackgroundColor(Color.YELLOW);
        }
        return super.onOptionsItemSelected(item);
    }
}