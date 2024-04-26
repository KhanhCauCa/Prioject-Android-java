package com.example.lesson2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class MySQL extends SQLiteOpenHelper {

    private static String DBName = "mydb.db";
    private  static  int VERSION = 1;
    private static String TABLE_NAME = "Transcript";
    private static  String ID = "Id";
    private static String NAME = "Name";
    private static String IDENTIFICATION_NUMBER = "Identification_number";
    private static String MATH = "Math";
    private static String PHYSICAL = "Physical";
    private static String CHEMISTRY = "Chemistry";
    private SQLiteDatabase myDB;

    public MySQL(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    public  String getIDENTIFICATION_NUMBER() {
        return IDENTIFICATION_NUMBER;
    }

    public static void setIDENTIFICATION_NUMBER(String identificationNumber) {
        IDENTIFICATION_NUMBER = identificationNumber;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        MySQL.ID = ID;
    }

    public  String getNAME() {
        return NAME;
    }

    public static void setNAME(String NAME) {
        MySQL.NAME = NAME;
    }

    public  String getMATH() {
        return MATH;
    }

    public static void setMATH(String MATH) {
        MySQL.MATH = MATH;
    }

    public  String getPHYSICAL() {
        return PHYSICAL;
    }

    public static void setPHYSICAL(String PHYSICAL) {
        MySQL.PHYSICAL = PHYSICAL;
    }

    public  String getCHEMISTRY() {
        return CHEMISTRY;
    }

    public static void setCHEMISTRY(String CHEMISTRY) {
        MySQL.CHEMISTRY = CHEMISTRY;
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
                + IDENTIFICATION_NUMBER + " text not null, "
                + NAME + " text not null, "
                + MATH + " real not null, "
                + PHYSICAL + " real not null,"
                + CHEMISTRY + " real not null"
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

    public long insert(String identification_number,String name,float math,float physical,float chemistry){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IDENTIFICATION_NUMBER, identification_number);
        values.put(NAME, name);
        values.put(MATH, math);
        values.put(PHYSICAL, physical);
        values.put(CHEMISTRY, chemistry);
        return db.insert(TABLE_NAME, null, values);
    }

    public long update(int id, String identification_number, String name,float math,float physical,float chemistry){
        ContentValues values = new ContentValues();
        values.put(IDENTIFICATION_NUMBER, identification_number);
        values.put(NAME, name);
        values.put(MATH, math);
        values.put(PHYSICAL, physical);
        values.put(CHEMISTRY, chemistry);
        String where = ID + " = "  + id;
        return myDB.update(TABLE_NAME, values, where, null);
    }

    public long delete(int id){
        String where = ID + " = "  + id;
        return myDB.delete(TABLE_NAME, where, null);
    }

//    public Cursor DisplayAllSortedByScore() {
//        if (myDB != null && myDB.isOpen()) {
//            String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + TIME + " ASC";
//            return myDB.rawQuery(query, null);
//        }
//        return null;
//    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor searchByIdentificationNumber(String searchText) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + IDENTIFICATION_NUMBER + " LIKE ?";
        String[] selectionArgs = {"%" + searchText + "%"};
        return myDB.rawQuery(query, selectionArgs);
    }
    public Cursor searchByName(String searchText) {
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

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

}
