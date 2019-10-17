package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends Activity {


    private ListView listView;
    private Button addBtn, removeBtn, editBtn;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        listView = findViewById(R.id.listView_resturante);
        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editBtn = findViewById(R.id.button_edit);
        db = new Database(this);
        showData();
        add();
    }


    public void add(){
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
        List<Kontakt> kontakts = db.finnAlleKontakter();
        ArrayList<String> navn = new ArrayList<>();
        for (Kontakt kontakt : kontakts){
            navn.add(kontakt.getNavn());
        }
        ArrayAdapter <String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,navn);

        //ArrayAdapter <Kontakt> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kontakts);
        listView.setAdapter(itemsAdapter);

    }




}
