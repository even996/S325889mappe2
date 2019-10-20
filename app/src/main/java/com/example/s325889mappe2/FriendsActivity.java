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

public class FriendsActivity extends Activity {


    private ListView listView;
    private Button addBtn, removeBtn, editBtn;
    private Database db;

    private ArrayList<Kontakt> listItems;
    ArrayAdapter arrayAdapter;
    Kontakt kontakt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        listView = findViewById(R.id.listView_resturante);
        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editBtn = findViewById(R.id.button_edit);
        db = new Database(this);

        listItems = new ArrayList<>();

        viewData();
        //showData();
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
        Intent intent = new Intent(this, FriendAddActivity.class);
        startActivity(intent);
    }

    public void editIntent(long id, String name, String tlf){
        Intent editIntent = new Intent(this, EditFriends.class);
        editIntent.putExtra("ID", id);
        editIntent.putExtra("NAME", name);
        editIntent.putExtra("TLF", tlf);
        startActivity(editIntent);
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
            int i = 0;
            while (cursor.moveToNext()){

                kontakt = new Kontakt(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                listItems.add(kontakt);
                i++;
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




}
