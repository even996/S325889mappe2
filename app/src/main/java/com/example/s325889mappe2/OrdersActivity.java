package com.example.s325889mappe2;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrdersActivity extends Activity {


    private ListView listView;
    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn, deleteButton;
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
        deleteButton=findViewById(R.id.add_image_delete);
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_image_button);
        orderImageBtn = findViewById(R.id.order_image_button);
        settingsImageBtn = findViewById(R.id.settings_image_button);

        db = new Database(this);
        viewData();
        goToAdd();
        goToResturante();
        goToFriends();
        goToSettings();
        goToOrders();
        onSelect();
        remove();
        removeNotifi();

    }




    public void remove(){
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Order order : selectedContacts){
                    System.out.println("erwerewrwerewr");
                    db.removeDataOrder(order.getId());
                    nextIntent(3);
                    finish();
                }
            }
        });
    }

    public void removeNotifi(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }


    ArrayList<Order> selectedContacts = new ArrayList();
    public void onSelect(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Order order = (Order) adapterView.getItemAtPosition(i);
                if (selectedContacts.contains(order)){
                    //Toast.makeText(FriendsViewActivity.this, kontakt.getNavn() + " is removed", Toast.LENGTH_SHORT).show();
                    selectedContacts.remove(order);
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);


                }else {
                    selectedContacts.add(order);
                    //Toast.makeText(FriendsViewActivity.this, kontakt.getNavn() + " is added", Toast.LENGTH_SHORT).show();
                   listView.getChildAt(i).setBackgroundColor(Color.GREEN);
                }
            }
        });
    }

    public void goToResturante(){
        resturantImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(1);
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


    public void goToFriends(){
        friendsImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(2);
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
        Intent backIntent = new Intent(this, MainActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void goToAdd(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedContacts.clear();
                nextIntent();
            }
        });
    }

    public void nextIntent(){
        Intent intent = new Intent(this, FriendsViewActivity.class);
        startActivity(intent);
        finish();
    }


    private void viewData(){
        Cursor cursor = db.viewDataOrder();
        if(cursor.getCount() == 0){
            Toast.makeText(OrdersActivity.this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            //cursor.moveToNext();
            //order = new Order(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            //long lastKnownID = cursor.getLong(0);
            //String allNames = order.getFriend();
            //listItems.add(order);
            String allNames = "";
            while (cursor.moveToNext()){
                allNames += cursor.getString(1);
                long lastKnownID = cursor.getLong(0);
                //listItems.add(order);
                //listItems.indexOf(order);
                int position = cursor.getPosition();
                if (cursor.moveToNext()) {
                    if (cursor.getLong(0) != lastKnownID) {
                        cursor.moveToPosition(position);
                        order = new Order(cursor.getLong(0),allNames,cursor.getString(2),cursor.getString(3));
                        listItems.add(order);
                        allNames = "";
                    }else {
                        allNames += "\n";
                        cursor.moveToPosition(position);
                    }
                    //allNames += "\n" + cursor.getString(1);
                }else{
                    cursor.moveToPosition(position);
                    order = new Order(cursor.getLong(0),allNames,cursor.getString(2),cursor.getString(3));
                    listItems.add(order);
                    allNames = "";
                }
                //allNames += "\n" + cursor.getString(1);
                //order = new Order(cursor.getLong(0), allNames, cursor.getString(2), cursor.getString(3));
            }
            OrderCustomAdapter adapter = new OrderCustomAdapter(this, R.layout.list_adapter3, listItems);
            listView.setAdapter(adapter);
        }
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
