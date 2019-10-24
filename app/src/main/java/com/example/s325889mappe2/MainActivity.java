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

public class MainActivity extends AppCompatActivity {

    private ImageButton friendsImageBtn, resturantImageBtn, orderImageBtn;
    private Database dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new Database(this);
        friendsImageBtn = findViewById(R.id.friends_image_button);
        resturantImageBtn = findViewById(R.id.resturant_imagae_button);
        orderImageBtn = findViewById(R.id.order_image_button);

        Toolbar myToolBar = (Toolbar)findViewById(R.id.mintoolbar);

        myToolBar.inflateMenu(R.menu.menu_hoved);
        setActionBar(myToolBar);
        goToResturante();
        goToOrders();
        goToFriends();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hoved, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()){
            case R.id.on_notification:
                intent = new Intent();
                intent.setAction("com.example.servicebroadcast.notifikasjonbroadcast");
                sendBroadcast(intent);
                break;
            case R.id.off_notification:
                Intent i = new Intent(this, MinService.class);
                PendingIntent pInent = PendingIntent.getService(this, 0 ,i,0);
                AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if(alarm != null){
                    alarm.cancel(pInent);
                }
                break;
            case R.id.on_sms:
                if (checkPermission(Manifest.permission.SEND_SMS)){
                    Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT);
                    intent = new Intent();
                    intent.setAction("com.example.servicebroadcast.smsbroadcast");
                    sendBroadcast(intent);
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
                    if (checkPermission(Manifest.permission.SEND_SMS)){
                        Toast.makeText(this,"SMS-Service is on",Toast.LENGTH_SHORT);
                        intent = new Intent();
                        intent.setAction("com.example.servicebroadcast.smsbroadcast");
                        sendBroadcast(intent);
                    }
                }
                break;
            case R.id.off_sms:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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
                //finish(); funke
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
            default:
                break;
        }

    }

}
