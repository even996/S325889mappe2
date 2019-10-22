package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditFriends extends Activity {


    EditText editName, editTlf;
    private ImageButton editImageBtn, removeImageBtn;
    Database db;
    Long ID;
    String name;
    String telefon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_edit);
        editName = findViewById(R.id.editText_name);
        editTlf= findViewById(R.id.editText_telephone);
        removeImageBtn = findViewById(R.id.remove_image_button);
        editImageBtn = findViewById(R.id.edit_image_button);
        db = new Database(this);

        Intent recivedIntent = getIntent();
        ID= recivedIntent.getLongExtra("ID", 1);
        name= recivedIntent.getStringExtra("NAME");
        telefon= recivedIntent.getStringExtra("TLF");
        editName.setText(name);
        editTlf.setText(telefon);

        Edit();
        removeFriend();

    }

    @Override
    public void onBackPressed() {
       intentBack();
    }

    public void Edit(){
        editImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditFriends.this, "Venner Editet", Toast.LENGTH_SHORT).show();
                db.updateTable(ID, editName.getText().toString(), name, editTlf.getText().toString(), telefon);
            }
        });
    }

    public void removeFriend(){
        removeImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeDataFriend(ID);
                intentBack();
                finish();
            }
        });
    }




    public void intentBack(){
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
        finish();


    }


}
