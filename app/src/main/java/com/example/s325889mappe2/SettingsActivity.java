package com.example.s325889mappe2;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class SettingsActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Button notificationOn, notificationOff, smsOn, smsOff, setTime;
    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn, settingsImageBtn;
    TextView settingsTitle, timeChossen;
    int i = 1;
    int j = 1;


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
        timeChossen=findViewById(R.id.time_Choosen_TextView);
        setTime=findViewById(R.id.setTime);
        settingsImageBtn = findViewById(R.id.settings_image_button);
        smsOn = findViewById(R.id.sms_on);
        smsOff = findViewById(R.id.sms_off);
        String time = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("TIME","");
        timeChossen.setText(time);
        notifiOn();
        notifiOff();
        smsOn();
        smsOff();
        goToResturante();
        goToOrders();
        goToFriends();
        goToSettings();
        onSetTime();
        changeColorNotifi(i);
        changeColorSms(j);
        System.out.println(i);
        setCorrectColors();

    }

    public void setCorrectColors(){
        String smsON = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPSMS","");
        String NotifON = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPNOTIFI","");
        if (smsON.equals("ON")){
            changeColorSms(2);
        }else{
            changeColorSms(1);
        }
        if (NotifON.equals("ON")){
            changeColorNotifi(2);
        }else{
            changeColorNotifi(1);
        }
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String hour = hourOfDay + "";
        String min = minute + "";
        if (hourOfDay < 10)
            hour = "0" + hourOfDay;
        if (minute < 10)
            min = "0" + minute;
        String choosen = hour + ":" + min;
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("TIME",choosen).apply();
        timeChossen.setText(choosen);
    }

    public void onSetTime(){
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment timePicker = new ChooseTimeFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
           }
        });
    }


    @Override
    public void onBackPressed() {
        backIntent();
    }

    public void changeColorNotifi(int i){
        if(i == 1){
            notificationOff.setTextColor(Color.GREEN);
            notificationOn.setTextColor(Color.BLACK);
        }
        if(i == 2){
            notificationOn.setTextColor(Color.GREEN);
            notificationOff.setTextColor(Color.BLACK);
        }

        System.out.println(i);

    }


    public void changeColorSms(int j){
        if(j == 1){
            smsOff.setTextColor(Color.GREEN);
            smsOn.setTextColor(Color.BLACK);
        }
        if(j == 2) {
            smsOff.setTextColor(Color.BLACK);
            smsOn.setTextColor(Color.GREEN);
        }

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
                changeColorNotifi(2);
                i = 2;
            }
        });
    }

    public void notifiOff(){
        notificationOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(2);
                changeColorNotifi(1);
                i=1;
            }
        });
    }

    public void smsOn(){
        smsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(3);
                changeColorSms(2);
            }
        });
    }

    public void smsOff(){
        smsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options(4);
                changeColorSms(1);
            }
        });
    }



    public void options(int j) {
        String notifiPref, smsPref;
        String notfiOn= "ON";
        String notfiOff= "OFF";
        String smsON = "ON";
        String smsOff = "OFF";

        switch (j) {
            case 1:
                System.out.println(smsON);
                Intent intent = new Intent();
                notifiPref = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getString("PREPNOTIFI","");
                getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPNOTIFI",notfiOn).apply();
                intent.setAction("com.example.servicebroadcast.notifikasjonbroadcast");
                sendBroadcast(intent);

                break;
            case 2:
                System.out.println(smsOff);
                notifiPref = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getString("PREPNOTIFI","");
                getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPNOTIFI", notfiOff).apply();

                Intent i = new Intent(this, MinService.class);
                PendingIntent pInent = PendingIntent.getService(this, 0, i, 0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarm != null) {
                    alarm.cancel(pInent);
                }
                stopService(i);
                break;

            case 3:
                if (checkPermission(Manifest.permission.SEND_SMS)){
                    smsPref = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getString("PREPSMS","");
                    getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPSMS",smsON).apply();
                    Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT).show();
                    intent = new Intent();
                    intent.setAction("com.example.servicebroadcast.smsbroadcast");
                    sendBroadcast(intent);
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
                    if (checkPermission(Manifest.permission.SEND_SMS)){
                        smsPref = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getString("PREPSMS","");
                        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPSMS",smsON).apply();
                        Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT).show();
                        intent = new Intent();
                        intent.setAction("com.example.servicebroadcast.smsbroadcast");
                        sendBroadcast(intent);
                    }
                }
                break;
            case 4:
                smsPref = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getString("PREPSMS","");
                getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREPSMS", smsOff).apply();
                Intent i2 = new Intent(this, SMSService.class);
                PendingIntent pInent2 = PendingIntent.getService(this, 0, i2, 0);
                AlarmManager alarm2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarm2 != null) {
                    stopService(i2);
                    alarm2.cancel(pInent2);
                }
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
