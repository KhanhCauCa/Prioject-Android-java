package com.example.usetoiuu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listViewDanhSach;
    EditText editTextTextHoTen, editTextTextNamSinh;
    Button btn_Add ,btn_Sua,btn_TimKiem,btn_Gui,btn_Xoa;
    ArrayList<SinhVien> arrayListSinhVien= new ArrayList<SinhVien>();

    SinhVienAdapter adapter;
    TextView textViewKQ;

    int val;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == 33){
                Intent intent = o.getData();
                int t = intent.getIntExtra("tong", -1);
                textViewKQ.setText(String.valueOf(t));
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initwitget();

        adapter = new SinhVienAdapter(MainActivity.this, R.layout.item, arrayListSinhVien);
        listViewDanhSach.setAdapter(adapter);
        listViewDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = arrayListSinhVien.get(position).getHoTen() +arrayListSinhVien.get(position).getNamSinh() ;
                String hoTen = arrayListSinhVien.get(position).getHoTen();
                Integer namSinh = arrayListSinhVien.get(position).getNamSinh();
                editTextTextHoTen.setText(hoTen);
                editTextTextNamSinh.setText(String.valueOf(namSinh));
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_SHORT).show();
                val = position;
            }
        });
        listViewDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                xacnhanxoa();
                val = position;
                return false;
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoTen = editTextTextHoTen.getText().toString();
                Integer namSinh = Integer.parseInt(editTextTextNamSinh.getText().toString());
                arrayListSinhVien.add(new SinhVien(hoTen,namSinh));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = editTextTextHoTen.getText().toString();
                Integer namSinh = Integer.parseInt(editTextTextNamSinh.getText().toString());
                arrayListSinhVien.set(val, new SinhVien(hoTen,namSinh));
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = editTextTextHoTen.getText().toString();
                Integer namSinh = Integer.parseInt(editTextTextNamSinh.getText().toString());
                boolean kt = false;
                for(int i =0; i< arrayListSinhVien.size() ; i++){
                    if( hoTen.equals(arrayListSinhVien.get(i).getHoTen()) && String.valueOf(namSinh).equals("")){
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        kt = true;
                        adapter.notifyDataSetChanged();
                        break;
                    }
                    if( namSinh.equals(arrayListSinhVien.get(i).getNamSinh()) && hoTen.equals("")){
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        kt = true;
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }
                if(!kt){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = editTextTextHoTen.getText().toString();
                Integer namSinh = Integer.parseInt(editTextTextNamSinh.getText().toString());
                arrayListSinhVien.remove(val);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
        btn_Gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyly();
            }
        });


    }
    private void xyly() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("a", Integer.parseInt(editTextTextHoTen.getText().toString()));
        intent.putExtra("b",Integer.parseInt(editTextTextNamSinh.getText().toString()));
//        startActivityForResult(intent, 99);
        activityResultLauncher.launch(intent);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 99 && resultCode ==33){
//            int t = data.getIntExtra("tong", 1);
//            textViewKQ.setText(String.valueOf(t));
//        }
//    }

    private void xacnhanxoa() {
        AlertDialog.Builder alerdialog = new AlertDialog.Builder(this);
        alerdialog.setTitle("Thông báo");
        alerdialog.setIcon(R.mipmap.ic_launcher);
        alerdialog.setMessage("Are you sure?");
        alerdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrayListSinhVien.remove(val);
                adapter.notifyDataSetChanged();
            }
        });
        alerdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerdialog.show();
    }

    private void Initwitget() {
        listViewDanhSach = (ListView) findViewById(R.id.listViewDanhSach);
        editTextTextHoTen = (EditText) findViewById(R.id.editTextTextHoTen) ;
        editTextTextNamSinh = (EditText) findViewById(R.id.editTextTextNamSInh) ;
        btn_Add = (Button)  findViewById(R.id.btn_Add);
        btn_Sua = (Button)  findViewById(R.id.btn_Sua);
        btn_TimKiem = (Button)  findViewById(R.id.btn_TimKiem);
        btn_Gui = (Button)  findViewById(R.id.btn_Gui);
        btn_Xoa = (Button)  findViewById(R.id.btn_Xoa);
        textViewKQ = (TextView) findViewById(R.id.textViewKQ);
        arrayListSinhVien.add(new SinhVien("XuanTrang",2003));
        arrayListSinhVien.add(new SinhVien("Khanh",2003));
        arrayListSinhVien.add(new SinhVien("Nghien",2003));


    }
}