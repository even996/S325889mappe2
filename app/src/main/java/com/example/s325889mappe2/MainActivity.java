package com.example.s325889mappe2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn;
    private Database dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new Database(this);
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_image_button);
        orderImageBtn = findViewById(R.id.order_image_button);
        settingsImageBtn = findViewById(R.id.settings_image_button);


        Toolbar myToolBar = (Toolbar)findViewById(R.id.mintoolbar);
        myToolBar.setTitle("");
        myToolBar.inflateMenu(R.menu.menu_hoved);
        setActionBar(myToolBar);
        goToResturante();
        goToOrders();
        goToFriends();
        goToSettings();

        if(!getSharedPreferences("PREFERENCES",MODE_PRIVATE).contains("PREPSMS"))
            getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPSMS","OFF").apply();
        if(!getSharedPreferences("PREFERENCES",MODE_PRIVATE).contains("PREPNOTIFI"))
            getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPNOTIFI","OFF").apply();
        if (!getSharedPreferences("PREFERENCES",MODE_PRIVATE).contains("TIME"))
            getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("TIME","16:00").apply();
        if (!getSharedPreferences("PREFERENCES",MODE_PRIVATE).contains("MESSAGE"))
            getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("MESSAGE","Hei! \n\nikke glem at vi har en middagsavtale i dag!").apply();


        //if (getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPSMS","")){
        //};

        //getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("","NO");
        //getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("","NO");

    }






    public void goToResturante(){
        resturantImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextIntent(1);
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

    public void goToOrders(){
        orderImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(3);
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

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }


    // GODE rå er dyre hvertfall fra jaso, send data til db --> fra db til arraylist --> arrayadapther
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
