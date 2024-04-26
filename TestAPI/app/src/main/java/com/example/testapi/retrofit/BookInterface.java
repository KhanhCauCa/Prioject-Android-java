package com.example.testapi.retrofit;
import com.example.testapi.object.Book;
import com.example.testapi.object.NhaCungCap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookInterface {
    @GET("/book/getall")
    Call<ArrayList<Book>> getAllBook();
    @GET("/book/getbyid/[id]")
    Call<Book> getBookId(@Path("id") String id);
    @GET("/ncc/insert")
    Call<NhaCungCap> insertNCC(@Body NhaCungCap ncc);
}
