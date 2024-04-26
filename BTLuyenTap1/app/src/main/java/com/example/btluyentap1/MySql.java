package com.example.btluyentap1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySql extends SQLiteOpenHelper {
    public static  String DATABASE_NAME = "BT1.db";
    public static int VERSION = 1;
    public  static String TABLE = "BT1";
    public static String ID = "_id";
    public static String NAME = "name";
    public static String SINGER = "singer";
    public static String TIME = "timer";
    public SQLiteDatabase db;

    public String getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSINGER() {
        return SINGER;
    }

    public String getTIME() {
        return TIME;
    }

    public MySql(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NAME + " TEXT NOT NULL," +
                SINGER + " TEXT NOT NULL, " +
                TIME + " DOUBLE NOT NULL" + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onOpen(){
        db = getWritableDatabase();
    }

    public void onDelete(){
        if(db != null && db.isOpen()){
            db.close();
        }
    }
    public long insertSinger(String name, String singer, String timer)
    {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(SINGER, singer);
        contentValues.put(TIME, timer);
        return db.insert(TABLE,null,  contentValues);
    }

    public long updateSinger(String id, String name, String singer, String timer)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(SINGER, singer);
        contentValues.put(TIME, timer);
        String where = ID + " = " + id;
        return db.update(TABLE, contentValues, where, null);
    }

    public long deleteSinger(String id){
        String where = ID + " = " + id;
        return db.delete(TABLE, where, null);
    }

    public Cursor getAll(){
        db = getWritableDatabase();
        return db.rawQuery("select * from " + TABLE, null);
    }
}
