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
    public static final String ORDER_TABLE = "Order_table";
    public static final String REST_COL_1 = "ID";
    public static final String REST_COL_2 = "NAME";
    public static final String REST_COL_3 = "ADRESS";
    public static final String REST_COL_4 = "TELEPHONE";
    public static final String REST_COL_5 = "TYPE";
    public static final String FRIENDS_COL_1 = "ID";
    public static final String FRIENDS_COL_2 = "NAME";
    public static final String FRIENDS_COL_3 = "TELEPHONE";
    public static final String ORDER_COL_1 = "ID";
    public static final String ORDER_COL_2 = "FRIENDS";
    public static final String ORDER_COL_3 = "RESTURANT";
    public static final String ORDER_COL_4 = "TIME";




    private static final String CREATE_TABLE = "CREATE TABLE " +
            RESTURANT_TABLE + "(" + REST_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + REST_COL_2 + " TEXT," + REST_COL_3 + " TEXT," + REST_COL_4 + " TEXT," + REST_COL_5 + " TEXT" + ")";

    private static final String CREATE_TABLE2 = "CREATE TABLE " +
            FRIENDS_TABLE + "(" + FRIENDS_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FRIENDS_COL_2 + " TEXT," + FRIENDS_COL_3 + " TEXT" + ")";

    private static final String CREATE_TABLE3 = "CREATE TABLE " +
            ORDER_TABLE + "(" + ORDER_COL_1 + " INTEGER,"
            + ORDER_COL_2 + " TEXT," + ORDER_COL_3 + " TEXT," + ORDER_COL_4 + " TEXT" + ")";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + RESTURANT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FRIENDS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        onCreate(db);
    }

    public long getHighestOrderID(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT MAX(" + ORDER_COL_1 +") FROM " + ORDER_TABLE,null);
        if (data.moveToFirst())
            return data.getLong(0);
        return 0;
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

    public void addDataOrder(long ID, String name, String resturant, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_COL_1, ID);
        contentValues.put(ORDER_COL_2, name);
        contentValues.put(ORDER_COL_3, resturant);
        contentValues.put(ORDER_COL_4, time);
        db.insert(ORDER_TABLE,null, contentValues);
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

    public void removeDataFriend(long id_inn){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FRIENDS_TABLE, REST_COL_1 + " =? ", new String[] {Long.toString(id_inn)});
        db.close();
    }

    public void removeDataOrder(long id_inn){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ORDER_TABLE, ORDER_COL_1 + " =? ", new String[] {Long.toString(id_inn)});
        db.close();
    }

    public void removeDataResturante(long id_inn){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESTURANT_TABLE, REST_COL_1 + " =? ", new String[] {Long.toString(id_inn)});
        db.close();
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


    public Cursor viewDataOrder(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + ORDER_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor viewDataResturant(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + RESTURANT_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateTable(long id, String newName, String oldName, String newTlf, String oldTlf){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + FRIENDS_TABLE + " SET " + FRIENDS_COL_2 +
                " = '" + newName + "' WHERE " + FRIENDS_COL_1 + " = '" + id + "'" +
                " AND " + FRIENDS_COL_2 + " = '" + oldName + "'";

        String query2 = "UPDATE " + FRIENDS_TABLE + " SET " + FRIENDS_COL_3 +
                " = '" + newTlf + "' WHERE " + FRIENDS_COL_1 + " = '" + id + "'" +
                " AND " + FRIENDS_COL_3 + " = '" + oldTlf + "'";
        db.execSQL(query);
        db.execSQL(query2);
    }

    public void updateTableResturante(long id, String newName, String oldName, String newTlf, String oldTlf,
                                      String newAdress, String oldAdress, String newType, String oldType){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + RESTURANT_TABLE + " SET " + REST_COL_2 +
                " = '" + newName + "' WHERE " + REST_COL_1 + " = '" + id + "'" +
                " AND " + REST_COL_2 + " = '" + oldName + "'";

        String query3 = "UPDATE " + RESTURANT_TABLE + " SET " + REST_COL_3 +
                " = '" + newAdress + "' WHERE " + REST_COL_1 + " = '" + id + "'" +
                " AND " + REST_COL_3 + " = '" + oldAdress + "'";

        String query2 = "UPDATE " + RESTURANT_TABLE + " SET " + REST_COL_4 +
                " = '" + newTlf + "' WHERE " + REST_COL_1 + " = '" + id + "'" +
                " AND " + REST_COL_4 + " = '" + oldTlf + "'";

        String query4 = "UPDATE " + RESTURANT_TABLE + " SET " + REST_COL_5 +
                " = '" + newType + "' WHERE " + REST_COL_1 + " = '" + id + "'" +
                " AND " + REST_COL_5 + " = '" + oldType + "'";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }


    //SQL statement -->  1 setning update




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















