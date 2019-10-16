package com.example.s325889mappe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnResturante, btnFriends, btnOrder;
    private Database dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new Database(this);
        btnResturante = findViewById(R.id.button_resturante);
        btnFriends = findViewById(R.id.button_venner);
        btnOrder = findViewById(R.id.nutton_bestilling);
        goToResturante();
        goToOrders();
        goToFriends();
    }


    public void goToResturante(){
        btnResturante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(1);
            }
        });
    }

    public void goToFriends(){
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(2);
            }
        });
    }

    public void goToOrders(){
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextIntent(3);
            }
        });
    }


    // GODE rå er dyre hvertfall fra jaso, send data til db --> fra db til arraylist --> arrayadapther
    public void nextIntent(int i){
        switch (i){
            case 1:
                Intent intent = new Intent(this, ResturanteActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(this, FriendsActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, OrdersActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }

    }

}
