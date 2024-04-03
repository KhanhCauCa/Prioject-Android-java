package com.example.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static String  DBName = "mydb.db";
    private static  int VERSION =1;
    private static  String TABLE_NAME = "Product";
    private static String ID = "_id";
    private static String NAME = "name";
    private static String NUMBER = "number";
    private SQLiteDatabase myDB;
    public MyDBHelper(@Nullable Context context) {
        super(context,DBName,null,VERSION);
    }
    public void openDB(){
        myDB = getWritableDatabase();
    }

    public void closeDB(){
        if(myDB != null && myDB.isOpen()){
            myDB.close();
        }
    }
    //Load
    public Cursor DisplayAll(){
        String qr = "SELECT * FROM " + TABLE_NAME;
        return myDB.rawQuery(qr, null);
    }
    //insert
    public long insert(String name, String number){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(NUMBER, number);
        return db.insert(TABLE_NAME, null, values);
    }
    //update
    public long update(int id, String name, String number){
        ContentValues values = new ContentValues();
        values.put(ID,id);
        values.put(NAME, name);
        values.put(NUMBER, number);
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAME,values, where, null);
    }
    //delete
    public long delete(int id){
        String where = ID + " = " + id;
        return myDB.delete(TABLE_NAME, where, null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        String queryTable = " CREATE TABLE " + TABLE_NAME
                + " ( "
                + ID + " INTEGER PRIMARY KEY autoincrement, "
                + NAME + " TEXT NOT NULL, "
                + NUMBER + " TEXT NOT NULL "
                + " ) ";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
