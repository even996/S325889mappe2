package com.example.s325889mappe2;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResturanteActivity extends Activity {

    private ListView listView;

    private EditText name, adress, telefone, type;

    private Button addBtn, removeBtn, editBtn;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante);
        listView = findViewById(R.id.listView_resturante);
        name = findViewById(R.id.editText_name);
        adress = findViewById(R.id.editText_adress);
        telefone = findViewById(R.id.editText_telephone);
        type = findViewById(R.id.editText_type);
        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editBtn = findViewById(R.id.button_edit);
        db = new Database(this);
        add();
    }

    public void add(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResturanteActivity.this, "Resturante Added", Toast.LENGTH_SHORT).show();
                db.addDataResturant(name.getText().toString(), adress.getText().toString(),
                        telefone.getText().toString(),type.getText().toString());
            }
        });
    }

/*

    public void addItems(String name, String adress, String telefone, String type){
        Cursor cursor = new Cursor();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("yoyoyoyo");
        arrayList.add("JASO");
        arrayList.add("Even");
        arrayList.add("ATHI");
        arrayList.add("KIM");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }
    
 */




}
