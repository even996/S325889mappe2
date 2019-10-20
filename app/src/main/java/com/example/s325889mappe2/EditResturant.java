package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditResturant extends Activity {


    EditText editName, editTlf, editAdress, editType;
    Button btnBack, btnEdit, btnRemove;
    Database db;
    Long ID;
    String name;
    String telefon;
    String adress;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante_edit);
        editName = findViewById(R.id.editText_res_name);
        editTlf= findViewById(R.id.editText_res_telephone);
        editAdress= findViewById(R.id.editText_res_adress);
        editType= findViewById(R.id.editText_res_type);
        btnEdit= findViewById(R.id.button_edit);
        btnBack= findViewById(R.id.button_back);
        btnRemove = findViewById(R.id.button_remove);
        db = new Database(this);

        Intent recivedIntent = getIntent();
        ID = recivedIntent.getLongExtra("ID", 1);
        name = recivedIntent.getStringExtra("NAME");
        telefon = recivedIntent.getStringExtra("TLF");
        adress = recivedIntent.getStringExtra("ADRESS");
        type = recivedIntent.getStringExtra("TYPE");

        editName.setText(name);
        editTlf.setText(telefon);
        editAdress.setText(adress);
        editType.setText(type);

        EditRes();
        Back();
        removeFriend();

    }

    public void EditRes(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditResturant.this, "Resturante Editet", Toast.LENGTH_SHORT).show();
                db.updateTableResturante(ID, editName.getText().toString(), name, editTlf.getText().toString(), telefon,
                        editAdress.getText().toString(), adress, editType.getText().toString(), type);

            }
        });
    }

    public void removeFriend(){
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.removeDataResturante(ID);
                IntentBack();
            }
        });
    }


    public void Back(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentBack();
            }
        });
    }

    public void IntentBack(){
        Intent intent = new Intent(this, ResturanteActivity.class);
        startActivity(intent);


    }


}
