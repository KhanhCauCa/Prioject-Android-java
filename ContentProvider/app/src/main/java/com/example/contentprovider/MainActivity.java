package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_showContact,btn_showMessage,btn_showCalllog;
    Button btn_Load,btn_Insert,btn_Update,btn_Delete;
    EditText edt_Name, edt_Number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        getPermistion();
        btn_showContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowContact.class);
                startActivity(intent);
            }
        });

        btn_showMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowMessage.class );
                startActivity(intent);
            }
        });

        btn_showCalllog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] calllog = new String[]{
                        CallLog.Calls.DATE,
                        CallLog.Calls.NUMBER,
                        CallLog.Calls.DURATION
                };
                Cursor c = getContentResolver().query(
                        CallLog.Calls.CONTENT_URI,
                        calllog,
                        CallLog.Calls.DURATION + "<?",
                        new String[]{"30"},
                        CallLog.Calls.DATE +" Asc"
                );

                c.moveToFirst();
                String s = "";
                while (c.isAfterLast() == false){
                    for(int i =0; i<c.getColumnCount(); i ++ ){
                        s+=c.getString(i) + " - ";
                    }
                    s += "\n";
                    c.moveToNext();
                }
                c.close();
                Toast.makeText(MainActivity.this, s , Toast.LENGTH_SHORT).show();


            }
        });
        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ContentResolver
                ContentResolver contentResolver = getContentResolver();

                // Tạo một raw contact mới
                ContentValues rawContactValues = new ContentValues();
                Uri rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, rawContactValues);

                // Kiểm tra xem việc tạo raw contact có thành công hay không
                if (rawContactUri != null) {
                    long rawContactId = ContentUris.parseId(rawContactUri); // Parse raw contact ID từ URI

                    // Tạo một ContentValues để chứa thông tin liên hệ
                    ContentValues contactValues = new ContentValues();
                    contactValues.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, rawContactId);
                    contactValues.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, edt_Name.getText().toString());
                    contactValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, edt_Number.getText().toString());

                    // Chỉ định mimetype cho dữ liệu liên hệ
                    contactValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);

                    // Thêm thông tin liên hệ vào raw contact
                    Uri contactUri = contentResolver.insert(ContactsContract.Data.CONTENT_URI, contactValues);

                    // Kiểm tra xem việc thêm thông tin liên hệ có thành công hay không
                    if (contactUri != null) {
                        Toast.makeText(MainActivity.this, "Thêm liên hệ thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Thêm liên hệ thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Tạo raw contact thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getPermistion() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},999);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS},999);
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG},999);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},999);
        }

    }

    private void initWidget() {
        btn_showContact = (Button) findViewById(R.id.btn_showContact);
        btn_showMessage = (Button) findViewById(R.id.btn_showMessage);
        btn_showCalllog = (Button) findViewById(R.id.btn_showCalllog);
        btn_Load = (Button) findViewById(R.id.btn_Load);
        btn_Insert = (Button) findViewById(R.id.btn_Insert);
        btn_Delete = (Button) findViewById(R.id.btn_Delete);
        btn_Update = (Button) findViewById(R.id.btn_Update);
        edt_Name = (EditText) findViewById(R.id.edt_Name);
        edt_Number = (EditText) findViewById(R.id.edt_Number);

    }
}