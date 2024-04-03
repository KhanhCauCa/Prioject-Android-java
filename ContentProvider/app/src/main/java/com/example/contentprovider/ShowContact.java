package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {
    Button btn_back;
    ListView lst_showView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        initWidget();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        ShowContact();
    }

    public void ShowContact(){

        ArrayList<String> list = new ArrayList<String>();
        Uri uri = Uri.parse("content://contacts/people");
        Cursor c1 = getContentResolver().query(uri,null,null,null,null);
        c1.moveToFirst();
        while (!c1.isAfterLast()){
            String s = "";
            String idColumnName = ContactsContract.Contacts._ID;
            int iDIndex = c1.getColumnIndex(idColumnName);
            s = c1.getString(iDIndex) + "-";
//            s = ${c1.getString(iDIndex)} -;
            String nameColumnName = ContactsContract.Contacts.DISPLAY_NAME;
            int nameIndex = c1.getColumnIndex(nameColumnName);
            s += c1.getString(nameIndex);
            list.add(s);
            c1.moveToNext();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            lst_showView.setAdapter(adapter);
        }
    }
    private void initWidget() {
        btn_back = (Button) findViewById(R.id.btn_back2);
        lst_showView = (ListView) findViewById(R.id.lst_showView);
    }
}