package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends Activity {


    private ListView listView;
    private ImageButton addImageBtn;
    private Database db;
    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn;


    private ArrayList<Kontakt> listItems;
    ArrayAdapter arrayAdapter;
    Kontakt kontakt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        listView = findViewById(R.id.listView_resturante);
        addImageBtn = findViewById(R.id.add_image_button);
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_image_button);
        orderImageBtn = findViewById(R.id.order_image_button);
        settingsImageBtn = findViewById(R.id.settings_image_button);
        db = new Database(this);

        listItems = new ArrayList<>();

        viewData();
        //showData();
        goToAdd();
        onEdit();
        goToResturante();
        goToOrders();
        goToFriends();
        goToSettings();
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



    public void goToAdd(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent();
            }
        });
    }

    public void nextIntent(){
        Intent intent = new Intent(this, FriendAddActivity.class);
        startActivity(intent);
        finish();
    }

    public void editIntent(long id, String name, String tlf){
        Intent editIntent = new Intent(this, EditFriends.class);
        editIntent.putExtra("ID", id);
        editIntent.putExtra("NAME", name);
        editIntent.putExtra("TLF", tlf);
        startActivity(editIntent);
        finish();
    }

    public void onEdit(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FriendsActivity.this, "FriendsList clicked", Toast.LENGTH_SHORT).show();
                Kontakt kontakt= (Kontakt) adapterView.getItemAtPosition(i);
                long id = kontakt.getID();
                String name = kontakt.getNavn();
                String tlf = kontakt.getTelefon();
                editIntent(id,name,tlf);

            }
        });
    }


    private void viewData(){
        Cursor cursor = db.viewDataFriends();

        if(cursor.getCount() == 0){
            Toast.makeText(FriendsActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){

                kontakt = new Kontakt(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                listItems.add(kontakt);
            }
            CustomAdapter adapter = new CustomAdapter(this, R.layout.list_adapter, listItems);
            listView.setAdapter(adapter);
        }
    }


    public void showData(){

        List<Kontakt> kontakts = db.finnAlleKontakter();
        ArrayList<String> navn = new ArrayList<>();
        ArrayList<String> tlf = new ArrayList<>();

        for (Kontakt kontakt : kontakts){
            navn.add(kontakt.getNavn());
        }
        /*
        String navnet = "";
        for (Kontakt kontakt : kontakts){
            navnet += kontakt.getNavn();
            navnet += " ";
            navnet += kontakt.getTelefon();
            navnet += "\n";
        }
        navn.add(navnet);

         */




        ArrayAdapter <String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,navn);


        //ArrayAdapter <Kontakt> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kontakts);
        listView.setAdapter(itemsAdapter);

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
