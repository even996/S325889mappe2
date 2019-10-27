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

public class ResturanteActivity extends Activity {

    private ListView listView;
    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn;


    private ImageButton addImageBtn;
    private ArrayList<Restaurant> listItems;
    ArrayAdapter arrayAdapter;
    Restaurant restaurant;



    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante);
        listView = findViewById(R.id.listView_resturante);
        addImageBtn = findViewById(R.id.add_image_button);
        listItems = new ArrayList<>();
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_image_button);
        orderImageBtn = findViewById(R.id.order_image_button);
        settingsImageBtn = findViewById(R.id.settings_image_button);
        db = new Database(this);
        listItems = new ArrayList<>();
        viewData();
       // showData();
        goToAdd();
        onEdit();
        goToFriends();
        goToOrders();
        goToSettings();
        goToResturante();
    }

    private void viewData(){
        Cursor cursor = db.viewDataResturant();
        if(cursor.getCount() == 0){
            Toast.makeText(ResturanteActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){
                restaurant = new Restaurant(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                listItems.add(restaurant);

            }
            ResturantCustomAdapter adapter = new ResturantCustomAdapter(this, R.layout.list_adapter2, listItems);
            listView.setAdapter(adapter);
        }
    }

    public void goToResturante(){
        resturantImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(1);
            }
        });
    }

    public void goToFriends(){
        friendsImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(2);
            }
        });
    }

    public void goToOrders(){
        orderImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(3);
            }
        });
    }

    public void goToSettings(){
        settingsImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(4);
            }
        });
    }


    @Override
    public void onBackPressed() {
        backIntent();
    }



    public void backIntent(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }


    public void editIntent(long id, String name, String tlf, String adress, String type){
        Intent editIntent = new Intent(this, EditResturant.class);
        editIntent.putExtra("ID", id);
        editIntent.putExtra("NAME", name);
        editIntent.putExtra("TLF", tlf);
        editIntent.putExtra("ADRESS", adress);
        editIntent.putExtra("TYPE", type);
        startActivity(editIntent);
    }

    public void onEdit(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ResturanteActivity.this, "FriendsList clicked", Toast.LENGTH_SHORT).show();
                Restaurant restaurant = (Restaurant) adapterView.getItemAtPosition(i);
                long id = restaurant.getID();
                String name = restaurant.getNavn();
                String tlf = restaurant.getTelefonNr();
                String adress = restaurant.getAddresse();
                String type = restaurant.getType();
                editIntent(id,name,tlf, adress, type);

            }
        });
    }





    public void goToAdd(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent();

            }
        });
    }

    public void nextIntent(){
        finish();
        Intent intent = new Intent(this, ResturantAddActivity.class);
        startActivity(intent);
    }




    public void showData(){
        List<Restaurant> restaurants = db.finnAlleResturant();
        ArrayList<String> navn = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            navn.add(restaurant.getNavn());
        }
        ArrayAdapter <String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,navn);

        //ArrayAdapter <Kontakt> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kontakts);
        listView.setAdapter(itemsAdapter);

    }

    public void nextIntent(int i){
        switch (i){
            case 1:
                Intent intent = new Intent(this, ResturanteActivity.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                Intent intent2 = new Intent(this, FriendsActivity.class);
                startActivity(intent2);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(this, OrdersActivity.class);
                startActivity(intent3);
                finish();
                break;
            case 4:
                Intent intent4 = new Intent(this, SettingsActivity.class);
                startActivity(intent4);
                finish();
            default:
                break;
        }

    }



}
