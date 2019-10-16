package com.example.s325889mappe2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResturanteActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante);
        listView = findViewById(R.id.listView_resturante);
        addItems();

    }

    public void addItems(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("yoyoyoyo");
        arrayList.add("JASO");
        arrayList.add("Even");
        arrayList.add("ATHI");
        arrayList.add("KIM");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }


}
