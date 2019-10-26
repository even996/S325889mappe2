package com.example.s325889mappe2;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class OrderTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String nameFriend, tlfFriend;
    String nameResturant;
    private Database db;
    ImageButton  nextButtonImage, addButtonImage;
    TextView textViewTime;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_time);
        nextButtonImage=findViewById(R.id.next_image_button);
        addButtonImage=findViewById(R.id.add_image_button);
        textViewTime = findViewById(R.id.textView_time_choosen);
        Intent recivedIntent = getIntent();
        nameFriend = recivedIntent.getStringExtra("NAMEFRIEND");
        nameResturant = recivedIntent.getStringExtra("NAMERESTURANT");
        tlfFriend = recivedIntent.getStringExtra("TLF");
        db = new Database(this);
        nextButton();
        showCalender();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month);
        calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calender.getTime());
        textViewTime.setText(currentDateString);
        System.out.println(currentDateString);



    }

    public void showCalender(){
        addButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DateChooserDialog();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }




    public void nextButton(){
        nextButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrder();
            }
        });
    }

    public void addOrder(){
        String tlf,name,rest;
        db.addDataOrder(nameFriend, nameResturant, textViewTime.getText().toString());
        tlf = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFTLF","");
        name = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFNAME","");
        rest = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFREST","");
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFTLF",tlf + "\n" + tlfFriend).apply();
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFNAME",name + "\n" + nameFriend).apply();
        getSharedPreferences("PREFERENCES",MODE_PRIVATE).edit().putString("PREFREST",rest + "\n" + nameResturant).apply();
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intentBack();
    }

    public void intentBack(){
        Intent intent = new Intent(this, ResturanteActivity2.class);
        startActivity(intent);
        finish();
    }


}
