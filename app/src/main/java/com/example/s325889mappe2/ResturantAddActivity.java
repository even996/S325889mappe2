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
                Toast.makeText(ResturantAddActivity.this, "Resturante Added", Toast.LENGTH_SHORT).show();
                db.addDataResturant(name.getText().toString(), adress.getText().toString(),
                        telefone.getText().toString(),type.getText().toString());
                name.setText("");
                adress.setText("");
                telefone.setText("");
                type.setText("");


            }
        });
    }






}
