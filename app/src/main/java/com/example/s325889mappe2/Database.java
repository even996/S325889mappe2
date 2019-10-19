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
    public static final String RESTURANT_TABLE = "Resturant_table";
    public static final String FRIENDS_TABLE = "Friends_table";
    public static final String TABLE_NAME = "Order_table";
    public static final String REST_COL_1 = "ID";
    public static final String REST_COL_2 = "NAME";
    public static final String REST_COL_3 = "ADRESS";
    public static final String REST_COL_4 = "TELEPHONE";
    public static final String REST_COL_5 = "TYPE";
    public static final String FRIENDS_COL_1 = "ID";
    public static final String FRIENDS_COL_2 = "NAME";
    public static final String FRIENDS_COL_3 = "TELEPHONE";

    private static final String CREATE_TABLE = "CREATE TABLE " +
            RESTURANT_TABLE + "(" + REST_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + REST_COL_2 + " TEXT," + REST_COL_3 + " TEXT," + REST_COL_4 + " TEXT," + REST_COL_5 + " TEXT" + ")";

    private static final String CREATE_TABLE2 = "CREATE TABLE " +
            FRIENDS_TABLE + "(" + FRIENDS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FRIENDS_COL_2 + " TEXT," + FRIENDS_COL_3 + " TEXT" + ")";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTURANT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FRIENDS_TABLE);
        onCreate(db);
    }


    public void addDataResturant(String name, String adress, String telefone, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REST_COL_2, name);
        contentValues.put(REST_COL_3, adress);
        contentValues.put(REST_COL_4, telefone);
        contentValues.put(REST_COL_5, type);
        db.insert(RESTURANT_TABLE,null, contentValues);
//        getDatabase(name);
    }

    public void addDataFriend(String name,String telefone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FRIENDS_COL_2, name);
        contentValues.put(FRIENDS_COL_3, telefone);
        db.insert(FRIENDS_TABLE,null, contentValues);
//        getDatabase(name);
    }

    public void getDatabase(String name){
        Cursor data = this.getResturantData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext())
            listData.add(data.getString(1));

        SQLiteDatabase db = this.getWritableDatabase();
        //ResultSet rs = db.execSQL("SELECT " + REST_COL_2 + " FROM " + RESTURANT_TABLE + " WHERE " + REST_COL_2 + " = " + "\"" + name + "\"");
        db.execSQL("SELECT " + REST_COL_2 + " FROM " + RESTURANT_TABLE + " WHERE " + REST_COL_2 + " = " + "\"" + name + "\"" );
        System.out.println(name + "TISS");
    }


    public Cursor getResturantData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + RESTURANT_TABLE;
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    public Cursor viewDataFriends(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + FRIENDS_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor viewDataResturant(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + RESTURANT_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    public List<Kontakt> finnAlleKontakter(){
        List<Kontakt> kontaktList = new ArrayList<>();
        Kontakt kontakt;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FRIENDS_TABLE,null);
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



    public List<Restaurant> finnAlleResturant(){
            List<Restaurant> restaurantList = new ArrayList<>();
            Restaurant restaurant;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + RESTURANT_TABLE,null);
            if (cursor.moveToFirst()){
                do {
                    restaurant = new Restaurant(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4));
                    restaurantList.add(restaurant);
                }while(cursor.moveToNext());
                cursor.close();
                db.close();
            }
            return restaurantList;
    }
}















