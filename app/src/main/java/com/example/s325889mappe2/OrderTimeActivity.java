package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class OrderTimeActivity extends Activity {

    EditText editTime;
    String nameFriend, tlfFriend;
    String nameResturant;
    private Database db;
    ImageButton addButtonImage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_time);
        editTime=findViewById(R.id.editText_time);
        addButtonImage=findViewById(R.id.add_image_button);
        Intent recivedIntent = getIntent();
        nameFriend = recivedIntent.getStringExtra("NAMEFRIEND");
        nameResturant = recivedIntent.getStringExtra("NAMERESTURANT");
        tlfFriend = recivedIntent.getStringExtra("TLF");
        db = new Database(this);
        addButton();

    }


    public void addButton(){
        addButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrder();
            }
        });
    }

    public void addOrder(){
        String tlf,name,rest;
        db.addDataOrder(nameFriend, nameResturant, editTime.getText().toString());
        tlf = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFTLF","");
        name = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFNAME","");
        rest = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFREST","");
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFTLF",tlf + "\n" + tlfFriend).apply();
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFNAME",name + "\n" + nameFriend).apply();
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFREST",rest + "\n" + nameResturant).apply();
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intentBack();
    }

    public void intentBack(){
        Intent intent = new Intent(this, ResturanteActivity2.class);
        startActivity(intent);
        finish();
    }


}
