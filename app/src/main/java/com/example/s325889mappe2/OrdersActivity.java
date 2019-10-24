package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrdersActivity extends Activity {


    private ListView listView;
    ImageButton buttonAdd;
    Order order;
    long ID;
    String name;

    private Database db;
    private ArrayList<Order> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        listView = findViewById(R.id.listView_resturante);
        buttonAdd = findViewById(R.id.add_image_button);
        listItems= new ArrayList<>();

        db = new Database(this);
        viewData();
        goToAdd();

    }


    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, MainActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void goToAdd(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent();
            }
        });
    }

    public void nextIntent(){
        Intent intent = new Intent(this, FriendsActivity2.class);
        startActivity(intent);
        finish();
    }

    private void viewData(){
        Cursor cursor = db.viewDataOrder();

        if(cursor.getCount() == 0){
            Toast.makeText(OrdersActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()){
                order = new Order(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                listItems.add(order);
            }
            CustomAdapter3 adapter = new CustomAdapter3(this, R.layout.list_adapter3, listItems);
            listView.setAdapter(adapter);
        }
    }

}
