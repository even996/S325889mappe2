package com.example.s325889mappe2;

import android.app.Activity;
import android.content.ComponentName;
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
    ImageButton buttonNext;

    private ArrayList<Kontakt> listItems;
    ArrayAdapter arrayAdapter;
    Kontakt kontakt;
    private int count= 0;
    private String name="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends2);
        listView = findViewById(R.id.listView_resturante);
        db = new Database(this);

        listItems = new ArrayList<>();
        buttonNext= findViewById(R.id.add_image_button);


        viewData();
        //showData();
        onSelect();
        next();
    }


    @Override
    public void onBackPressed() {
        backIntent();
    }

    public void startService(View v){
        /*Intent intent = new Intent(this, MinService.class);
        this.startService(intent);

         */
        Intent intent = new Intent();
        intent.setAction("com.example.servicebroadcast.mittbroadcast");
        sendBroadcast(intent);
    }

    public void backIntent(){
        Intent homeIntent = new Intent(this, OrdersActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void next(){
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(name);
            }
        });
    }


    public void nextIntent(String friend){
        Intent orderIntent = new Intent(this, ResturanteActivity2.class);
        orderIntent.putExtra("NAME", friend);
        System.out.println(friend);
        startActivity(orderIntent);
        finish();

    }


    public void onSelect(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FriendsActivity2.this, "Venn lagt til", Toast.LENGTH_SHORT).show();
                Kontakt kontakt= (Kontakt) adapterView.getItemAtPosition(i);
                long id = kontakt.getID();
                if(count != 0){
                    name += "\n";
                }
                name += kontakt.getNavn();
                System.out.println(name);
                count++;

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
