package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class FriendAddActivity extends Activity {


    private EditText name, telefone;
    private ImageButton addImageBtn, backImageBtn;
    private Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_add);
        name = findViewById(R.id.editText_name);
        telefone = findViewById(R.id.editText_telephone);
        backImageBtn = findViewById(R.id.back_image_button);
        addImageBtn = findViewById(R.id.add_image_button);
        db = new Database(this);
        Add();
        Back();

    }


    public void Add(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FriendAddActivity.this, "Venner Added", Toast.LENGTH_SHORT).show();
                db.addDataFriend(name.getText().toString(), telefone.getText().toString());
            }
        });
    }


    public void Back(){
        backImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentBack();
            }
        });
    }

    public void IntentBack(){
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);


    }







}
