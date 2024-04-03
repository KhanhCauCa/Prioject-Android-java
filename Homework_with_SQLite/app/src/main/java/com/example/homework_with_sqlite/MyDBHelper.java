package com.example.homework_with_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    private static String DBName ="mydb.db";
    private static int VERSION = 1;
    private static String TABLE_NAME = "SinhVien";
    private static String ID = "_id";
    private  static String NAME = "name";
    private static String YEAROB = "yearob";
    private SQLiteDatabase myDB;

    public MyDBHelper(@Nullable Context context) {
        super(context,DBName,null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getYEAROB() {
        return YEAROB;
    }

    public void openDB(){
        myDB = getWritableDatabase();
    }
    public void closeDB(){
        if(myDB != null && myDB.isOpen()){
            myDB.close();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng
        String queryTable = " CREATE TABLE " + TABLE_NAME
                + " ( "
                + ID + " INTEGER PRIMARY KEY autoincrement, "
                + NAME + " TEXT NOT NULL, "
                + YEAROB + " INTEGER NOT NULL "
                + " ) ";
        db.execSQL(queryTable);
    }

    //Load
    public Cursor DisplayAll(){
        String qr = "SELECT * FROM " + TABLE_NAME;
        return myDB.rawQuery(qr, null);
    }
    //insert
    public long insert(String name, int yearob){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(YEAROB ,yearob);
        return db.insert(TABLE_NAME, null, values);
    }
    //update
    public long update(int id, String name, int yearob){
        ContentValues value = new ContentValues();
        value.put(ID,id);
        value.put(NAME,name);
        value.put(YEAROB, yearob);
        String where = ID + " = " +id;
        return myDB.update(TABLE_NAME, value, where,null);
    }
    //delete
    public long delete(int id){
        String where = ID + " = " + id;
        return myDB.delete(TABLE_NAME, where, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
