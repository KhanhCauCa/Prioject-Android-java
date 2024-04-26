package com.example.lesson1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Time;

import kotlin.contracts.Returns;

public class MySQL extends SQLiteOpenHelper {

        private static String DBName = "mydb.db";
        private  static  int VERSION = 1;
        private static String TABLE_NAME = "Song";
        private static  String ID = "_Id";
        private static String NAME = "Name";
        private static String SINGER = "Singer";
        private static String TIME = "Time";

    private SQLiteDatabase myDB;

    public MySQL(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getSINGER() {
        return SINGER;
    }

    public static String getTIME() {
        return TIME;
    }

    public void openDB() {
        if (myDB == null || !myDB.isOpen()) {
            myDB = getWritableDatabase();
        }
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = " create table " + TABLE_NAME
                + "("
                + ID + " integer primary key autoincrement, "
                + NAME + " text not null, "
                + SINGER + " text not null, "
                + TIME + " real not null"
                + ")";
        db.execSQL(queryTable);
    }

    public Cursor DisplayAll(){
        if (myDB == null || !myDB.isOpen()) {
            openDB(); // Mở lại nếu chưa được mở
        }
        String qr = "select * from " + TABLE_NAME;
        return myDB.rawQuery(qr, null);
    }

    public long insert(String name, String singer, float time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(SINGER, singer);
        values.put(TIME, time);
        return db.insert(TABLE_NAME, null, values);
    }

    public long update(int id, String name, String singer, float time){
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(SINGER, singer);
        values.put(TIME, time);
        String where = ID + " = "  + id;
        return myDB.update(TABLE_NAME, values, where, null);
    }

    public long delete(int id){
        String where = ID + " = "  + id;
        return myDB.delete(TABLE_NAME, where, null);
    }

    public Cursor DisplayAllSortedByTime() {
        if (myDB != null && myDB.isOpen()) {
            String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + TIME + " ASC";
            return myDB.rawQuery(query, null);
        }
        return null;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor searchSongsByName(String searchText) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE ?";
        String[] selectionArgs = {"%" + searchText + "%"};
        return myDB.rawQuery(query, selectionArgs);
    }

    public int  getCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlqr = "Select Count(*) from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlqr, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return  count;
    }
}
