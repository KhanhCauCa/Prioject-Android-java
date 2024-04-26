package com.example.testapi.retrofit;

public class BookUtils {
    public static final String BASE_URL = "http://localhost:3333";
    public static BookInterface getBookService(){
        return RetrofitClient.getClient(BASE_URL).create(BookInterface.class);
    }

}
