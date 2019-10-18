package com.example.s325889mappe2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FriendAddActivity extends Activity {


    private EditText name, telefone;
    private Button addBtn, removeBtn, editBtn;
    private Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_add);
        name = findViewById(R.id.editText_name);
        telefone = findViewById(R.id.editText_telephone);
        addBtn = findViewById(R.id.button_add);
        removeBtn = findViewById(R.id.button_remove);
        editBtn = findViewById(R.id.button_edit);
        db = new Database(this);
        Add();

    }


    public void Add(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FriendAddActivity.this, "Venner Added", Toast.LENGTH_SHORT).show();
                db.addDataFriend(name.getText().toString(), telefone.getText().toString());
            }
        });
    }






}
