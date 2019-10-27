package com.example.s325889mappe2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class ResturantAddActivity extends Activity {


    private EditText name, adress, telefone, type;
    private ImageButton addImageBtn;
    private Database db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturante_add);
        name = findViewById(R.id.editText_name);
        adress = findViewById(R.id.editText_adress);
        telefone = findViewById(R.id.editText_telephone);
        type = findViewById(R.id.editText_type);
        addImageBtn = findViewById(R.id.add_image_button);
        db = new Database(this);
        Add();

    }



    @Override
    public void onBackPressed() {
        finish();
        IntentBack();
    }



    public void IntentBack(){
        Intent intent = new Intent(this, ResturanteActivity.class);
        startActivity(intent);
    }


    public void Add(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length() >= 1 && telefone.getText().toString().length() == 8
                && adress.getText().toString().length() >= 1 && type.getText().toString().length() >= 1 ){
                    Toast.makeText(ResturantAddActivity.this, "Restaurant lagt til", Toast.LENGTH_SHORT).show();
                    db.addDataResturant(name.getText().toString(), adress.getText().toString(),
                            telefone.getText().toString(),type.getText().toString());
                    name.setText("");
                    adress.setText("");
                    telefone.setText("");
                    type.setText("");
                } else {
                    Toast.makeText(ResturantAddActivity.this, "Telefon har ikke 8 siffer, eller et av feltene er ikke utfylt", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }






}
