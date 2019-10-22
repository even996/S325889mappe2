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

public class FriendsActivity2 extends Activity {


    private ListView listView;
    private Database db;

    private ArrayList<Kontakt> listItems;
    ArrayAdapter arrayAdapter;
    Kontakt kontakt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends2);
        listView = findViewById(R.id.listView_resturante);
        db = new Database(this);

        listItems = new ArrayList<>();

        viewData();
        //showData();
        onSelect();
    }


    @Override
    public void onBackPressed() {
        backIntent();
    }

    public void backIntent(){
        Intent homeIntent = new Intent(this, OrdersActivity.class);
        startActivity(homeIntent);
        finish();
    }




    public void nextIntent(){
        Intent intent = new Intent(this, FriendAddActivity.class);
        startActivity(intent);
    }

    public void selectIntent(String friend){
        Intent orderIntent = new Intent(this, ResturanteActivity2.class);
        orderIntent.putExtra("NAME", friend);
        startActivity(orderIntent);

    }


    public void onSelect(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FriendsActivity2.this, "FriendsList clicked", Toast.LENGTH_SHORT).show();
                Kontakt kontakt= (Kontakt) adapterView.getItemAtPosition(i);
                long id = kontakt.getID();
                String name = kontakt.getNavn();
                selectIntent(name);

            }
        });
    }


    private void viewData(){
        Cursor cursor = db.viewDataFriends();

        if(cursor.getCount() == 0){
            Toast.makeText(FriendsActivity2.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){

                kontakt = new Kontakt(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                listItems.add(kontakt);
            }
            CustomAdapter adapter = new CustomAdapter(this, R.layout.list_adapter, listItems);
            listView.setAdapter(adapter);
        }
    }





}
