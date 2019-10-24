package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResturanteActivity2 extends Activity {

    private ListView listView;

    private ArrayList<Restaurant> listItems;
    ArrayAdapter arrayAdapter;
    Restaurant restaurant;

    long ID;
    String nameFriend, tlfFriend;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante2);
        listView = findViewById(R.id.listView_resturante);
        listItems = new ArrayList<>();
        db = new Database(this);
        listItems = new ArrayList<>();
        viewData();
       // showData();
        onSelect();
        Intent recivedIntent = getIntent();
        nameFriend= recivedIntent.getStringExtra("NAME");
        tlfFriend = recivedIntent.getStringExtra("TLF");
    }

    private void viewData(){
        Cursor cursor = db.viewDataResturant();
        if(cursor.getCount() == 0){
            Toast.makeText(ResturanteActivity2.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){

                restaurant = new Restaurant(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                listItems.add(restaurant);

            }
            CustomAdapter2 adapter = new CustomAdapter2(this, R.layout.list_adapter2, listItems);
            listView.setAdapter(adapter);
        }
    }


    @Override
    public void onBackPressed() {
        backIntent();
    }



    public void backIntent(){
        Intent homeIntent = new Intent(this, FriendsActivity2.class);
        startActivity(homeIntent);
        finish();
    }


    public void selectIntent(String resturant){
        //db.addDataOrder(nameFriend, resturant);
        Intent orderIntent = new Intent(this, OrderTimeActivity.class);
        orderIntent.putExtra("NAMEFRIEND", nameFriend);
        orderIntent.putExtra("TLF",tlfFriend);
        orderIntent.putExtra("NAMERESTURANT", resturant);
        startActivity(orderIntent);
        finish();
    }

    public void onSelect(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ResturanteActivity2.this, "FriendsList clicked", Toast.LENGTH_SHORT).show();
                Restaurant restaurant = (Restaurant) adapterView.getItemAtPosition(i);
                long id = restaurant.getID();
                String name = restaurant.getNavn();
                String tlf = restaurant.getTelefonNr();
                String adress = restaurant.getAddresse();
                String type = restaurant.getType();
                selectIntent(name);
            }
        });
    }



}
