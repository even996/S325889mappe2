package com.example.s325889mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SettingsActivity extends Activity {

    Button notificationOn, notificationOff, smsOn, smsOff;
    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn;
    TextView settingsTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        notificationOn = findViewById(R.id.notification_on);
        notificationOff = findViewById(R.id.notification_off);
        settingsTitle = findViewById(R.id.settings_title);
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_image_button);
        orderImageBtn = findViewById(R.id.order_image_button);
        settingsImageBtn = findViewById(R.id.settings_image_button);
        smsOn = findViewById(R.id.sms_on);
        smsOff = findViewById(R.id.sms_off);
        notifiOn();
        notifiOff();
        smsOn();
        smsOff();

        goToResturante();
        goToOrders();
        goToFriends();
        goToSettings();

    }



    @Override
    public void onBackPressed() {
        backIntent();
    }


    public void backIntent(){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
        finish();
    }

    public void notifiOn(){
        notificationOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(1);
            }
        });
    }

    public void notifiOff(){
        notificationOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(2);
            }
        });
    }

    public void smsOn(){
        smsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(3);
            }
        });
    }

    public void smsOff(){
        smsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(4);
            }
        });
    }



    public void options(int j) {
        switch (j) {
            case 1:
                Intent intent = new Intent();
                intent.setAction("com.example.servicebroadcast.notifikasjonbroadcast");
                sendBroadcast(intent);
                break;
            case 2:
                Intent i = new Intent(this, MinService.class);
                PendingIntent pInent = PendingIntent.getService(this, 0, i, 0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarm != null) {
                    alarm.cancel(pInent);
                }
                break;

            case 3:
                if (checkPermission(Manifest.permission.SEND_SMS)){
                    Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT).show();
                    intent = new Intent();
                    intent.setAction("com.example.servicebroadcast.smsbroadcast");
                    sendBroadcast(intent);
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
                    if (checkPermission(Manifest.permission.SEND_SMS)){
                        Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT).show();
                        intent = new Intent();
                        intent.setAction("com.example.servicebroadcast.smsbroadcast");
                        sendBroadcast(intent);
                    }
                }
                break;
            case 4:
                System.out.println("yoyoyo");
                break;
            default:
                break;
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
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
