package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button buttonBack;
    TextView textViewKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            initwitget();

         Intent intent1 = getIntent();
//       String ht1 = intent1.getStringExtra("HoTen");
         Bundle bundle = intent1.getBundleExtra("mybag");
         if(bundle != null){
             int ht1 = bundle.getInt("HoTen");
             textViewKQ.setText(String.valueOf(ht1));
         }
         //textViewKQ.setText(ht1);
        buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initwitget() {
        buttonBack = (Button) findViewById(R.id.buttonBack);
        textViewKQ = (TextView) findViewById(R.id.textViewKQ);
    }
}