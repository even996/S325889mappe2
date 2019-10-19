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
        showData();
        goToAdd();
        onEdit();
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

    public void onEdit(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ResturanteActivity.this, "Resturante Added", Toast.LENGTH_SHORT).show();
            }
        });
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
