package com.example.s325889mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "DATABASE.db";
    public static final String TABLE_NAME = "Resturant_table";
    public static final String TABLE_NAME2 = "Friends_table";
    public static final String TABLE_NAME3 = "Order_table";
    public static final String REST_COL_1 = "ID";
    public static final String REST_COL_2 = "NAME";
    public static final String REST_COL_3 = "ADRESS";
    public static final String REST_COL_4 = "TELEPHONE";
    public static final String REST_COL_5 = "TYPE";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + REST_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REST_COL_2 + " TEXT," + REST_COL_3 + " TEXT, " + REST_COL_4 + " TEXT, "  + REST_COL_5 + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addDataResturant(String name, String adress, String telefone, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REST_COL_2, name);
        contentValues.put(REST_COL_3, adress);
        contentValues.put(REST_COL_4, telefone);
        contentValues.put(REST_COL_5, type);
        db.insert(TABLE_NAME,null, contentValues);
    }

}















