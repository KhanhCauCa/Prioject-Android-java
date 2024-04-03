package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button buttonSend;
    EditText  editTextHoTen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwitget();

    buttonSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
           int ht = Integer.parseInt(editTextHoTen.getText().toString());
           Bundle bundle =  new Bundle();
           bundle.putInt("HoTen",ht);
           intent.putExtra("mybag",bundle);
//            String ht = editTextHoTen.getText().toString();
            //intent.putExtra("HoTen",ht);
            startActivity(intent);
        }
    });
    }

    private void initwitget() {
        buttonSend = (Button) findViewById(R.id.buttonSend);
        editTextHoTen = (EditText) findViewById(R.id.editTextHoTen);
    }

}