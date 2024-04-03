package com.example.java_collection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewMonHoc;
    Spinner spinnerMonHoc;
    EditText edtMonHoc;
    Button buttonOK,buttonLogin;
    ArrayList<String > arrayListMonHoc = new ArrayList<String>();
    ArrayAdapter<String> adapter,adapter1;
    String s = "";
    int val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initwitget();

        final String arr[] = {"Java", "Android", "Data mining",
                              "image processing", "Game" };

        arrayListMonHoc.add("Java");
        arrayListMonHoc.add("Android");
        arrayListMonHoc.add("Data mining");
        arrayListMonHoc.add("image processing");
        arrayListMonHoc.add("Game");

        //Gắn data source vào adapter

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1,arrayListMonHoc);
        listViewMonHoc.setAdapter(adapter);
        listViewMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListMonHoc.get(position), Toast.LENGTH_SHORT).show();
                val = position;

            }
        });
        adapter1 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrayListMonHoc);
        spinnerMonHoc.setAdapter(adapter1);
        spinnerMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, arrayListMonHoc.get(position), Toast.LENGTH_SHORT).show();
                val = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHocMoi = edtMonHoc.getText().toString();
                if(!monHocMoi.isEmpty()){
                    if (!arrayListMonHoc.contains(monHocMoi)) {
                            arrayListMonHoc.add(val,monHocMoi);
                            adapter1.notifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();


                }

            }
        });
        listViewMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//              arrayListMonHoc.remove(i);
//              adapter.notifyDataSetChanged();
                val = position;
                xaccnhanxoa();
                return true;
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyDialog();
            }
        });
    }

    private void callMyDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        EditText editTextUsername = (EditText) dialog.findViewById(R.id.editTextUsername);
        EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPassword);
        Button buttonOK1 = (Button) dialog.findViewById(R.id.buttonOK1);
        Button buttonCancle = (Button) dialog.findViewById(R.id.buttonCancle);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        buttonOK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if(username.equals("abcd") && password.equals("1234")){
                    Toast.makeText(MainActivity.this, "Correct",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT).show();

                }
            }
        });
        dialog.show();
    }

    private void xaccnhanxoa(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thong bao");
        alertDialog.setIcon(R.mipmap.ic_launcher_round);
        alertDialog.setMessage("Are you sure ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                arrayListMonHoc.remove(val);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    private void Initwitget() {
        listViewMonHoc = (ListView) findViewById(R.id.listViewMonHoc);
        spinnerMonHoc = (Spinner) findViewById(R.id.spinnerMonHoc);
        edtMonHoc = (EditText) findViewById(R.id.edtMonHoc);
        buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }
}