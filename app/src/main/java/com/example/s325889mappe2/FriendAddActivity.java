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
    private ImageButton addImageBtn;
    private Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_add);
        name = findViewById(R.id.editText_name);
        telefone = findViewById(R.id.editText_telephone);
        addImageBtn = findViewById(R.id.add_image_button);
        db = new Database(this);
        Add();

    }


    public void Add(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(telefone.getText().toString().length()==8 && name.getText().toString().length() >= 1){
                    Toast.makeText(FriendAddActivity.this, "Venn lagt til", Toast.LENGTH_SHORT).show();
                    db.addDataFriend(name.getText().toString(), telefone.getText().toString());
                    name.setText("");
                    telefone.setText("");
                }
                else {
                    Toast.makeText(FriendAddActivity.this, "Nummeret må ha 8 siffer, eller navn mangler", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        IntentBack();
    }



    public void IntentBack(){
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
        finish();

    }







}
