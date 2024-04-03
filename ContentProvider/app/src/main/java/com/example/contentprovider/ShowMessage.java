package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMessage extends AppCompatActivity {
    Button btn_back2;
    ListView lst_showMessage;

    ArrayList<String> messageList;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);
        initWidget();
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messageList = new ArrayList<String>();
        readMessage();


    }

    private void readMessage() {
        Uri uri =  Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(uri, null,null,null,null);
        while (cursor.moveToNext()){
            int index_phonenumber = cursor.getColumnIndex("address");
            int index_date = cursor.getColumnIndex("date");
            int index_body = cursor.getColumnIndex("body");
            String phone_number = cursor.getString(index_phonenumber);
            String date_ = cursor.getString(index_date);
            String body = cursor.getString(index_body);
            messageList.add(phone_number + "\n" + date_ + "\n" + body);
        }
        cursor.close();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageList);
        lst_showMessage.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initWidget() {
        btn_back2 = (Button) findViewById(R.id.btn_back2);
        lst_showMessage = (ListView) findViewById(R.id.lst_showMessage);
    }
}