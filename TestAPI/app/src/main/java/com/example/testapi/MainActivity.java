package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.object.Book;
import com.example.testapi.object.NhaCungCap;
import com.example.testapi.retrofit.BookInterface;
import com.example.testapi.retrofit.BookUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    BookInterface bookInterface;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);

        bookInterface = BookUtils.getBookService();
        bookInterface.getAllBook().enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if(response.isSuccessful()){
                    ArrayList<Book> kq = response.body();
                    Toast.makeText(MainActivity.this, kq.get(2).getTenSach(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        bookInterface.getBookId("S01").enqueue(new Callback<Book>() {
//            @Override
//            public void onResponse(Call<Book> call, Response<Book> response) {
//                if(response.isSuccessful()){
//                    Book kq = response.body();
//                    Toast.makeText(MainActivity.this, kq.getTenSach(), Toast.LENGTH_SHORT).show();
//                    if (kq != null) {
//                        String tenSach = kq.getTenSach();
//                        if (tenSach != null) {
//                            textView.setText(tenSach);
//                        } else {
//                            textView.setText("Tên sách không khả dụng");
//                        }
//                    } else {
//                        textView.setText("Không tìm thấy sách");
//                    }                }
//            }
//            @Override
//            public void onFailure(Call<Book> call, Throwable throwable) {
//
//            }
//        });
//        NhaCungCap ncc = new NhaCungCap("Lại", "Da co sau");
//        bookInterface.insertNCC(ncc).enqueue(new Callback<NhaCungCap>() {
//            @Override
//            public void onResponse(Call<NhaCungCap> call, Response<NhaCungCap> response) {
//                if ((response.isSuccessful())){
//                    Toast.makeText(MainActivity.this, "TC", Toast.LENGTH_SHORT).show();
//                    textView.setText("tc");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NhaCungCap> call, Throwable t) {
//
//            }
//        });

    }
}