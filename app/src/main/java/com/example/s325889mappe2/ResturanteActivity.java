package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ResturanteActivity extends Activity {

    private ListView listView;

    private Button addBtn, removeBtn, editBtn;
    private ArrayList<Restaurant> listItems;
    ArrayAdapter arrayAdapter;
    Restaurant restaurant;



    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante);
        listView = findViewById(R.id.listView_resturante);
        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editBtn = findViewById(R.id.button_edit);
        listItems = new ArrayList<>();
        db = new Database(this);
        listItems = new ArrayList<>();
        viewData();
       // showData();
        goToAdd();
        onEdit();
    }

    private void viewData(){
        Cursor cursor = db.viewDataResturant();
        if(cursor.getCount() == 0){
            Toast.makeText(ResturanteActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            int i = 0;
            while (cursor.moveToNext()){

                restaurant = new Restaurant(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4));
                listItems.add(restaurant);
                i++;
            }
            CustomAdapter2 adapter = new CustomAdapter2(this, R.layout.list_adapter2, listItems);
            listView.setAdapter(adapter);
        }
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
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent();

            }
        });
    }

    public void nextIntent(){
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



}
