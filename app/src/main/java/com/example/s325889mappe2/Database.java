package com.example.s325889mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "DATABASE.db";
    public static final String CONTACT_TABLE = "Resturant_table";
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
        db.execSQL("create table " + CONTACT_TABLE + " (" + REST_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REST_COL_2 + " TEXT," + REST_COL_3 + " TEXT, " + REST_COL_4 + " TEXT, "  + REST_COL_5 + " TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        onCreate(db);
    }


    public void addDataResturant(String name, String adress, String telefone, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REST_COL_2, name);
        contentValues.put(REST_COL_3, adress);
        contentValues.put(REST_COL_4, telefone);
        contentValues.put(REST_COL_5, type);
        db.insert(CONTACT_TABLE,null, contentValues);
//        getDatabase(name);
    }

    public void getDatabase(String name){
        Cursor data = this.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext())
            listData.add(data.getString(1));

        SQLiteDatabase db = this.getWritableDatabase();
        //ResultSet rs = db.execSQL("SELECT " + REST_COL_2 + " FROM " + CONTACT_TABLE + " WHERE " + REST_COL_2 + " = " + "\"" + name + "\"");
        db.execSQL("SELECT " + REST_COL_2 + " FROM " + CONTACT_TABLE + " WHERE " + REST_COL_2 + " = " + "\"" + name + "\"" );
        System.out.println(name + "TISS");
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + CONTACT_TABLE;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public List<Kontakt> finnAlleKontakter(){
        List<Kontakt> kontaktList = new ArrayList<>();
        Kontakt kontakt;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CONTACT_TABLE,null);
        if (cursor.moveToFirst()){
            do {
                kontakt = new Kontakt(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                kontaktList.add(kontakt);
            }while(cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return kontaktList;
    }
}















