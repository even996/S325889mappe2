package com.example.s325889mappe2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class OrderTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String nameFriend, tlfFriend;
    String nameResturant;
    private Database db;
    ImageButton  nextButtonImage, addButtonImage;
    TextView textViewTime;
    String currentDateString;


    ArrayList<Kontakt> nameArrayList;




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
        nameArrayList = (ArrayList<Kontakt>) recivedIntent.getSerializableExtra("nameList");
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
        String currDate = "";
        currentDateString = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calender.getTime());
        String date[] = currentDateString.split("/");
        if (Integer.parseInt(date[0]) < 10){
            date[0] = "0" + date[0] + "/";
        }else {
            date[0] = date[0] + "/";
        }
        if (Integer.parseInt(date[1]) < 10){
            date[1] = "0" + date[1] + "/";
        }else{
            date[1] = date[1] + "/";
        }
        if (Integer.parseInt(date[2]) < 10){
            date[2] = "0" + date[2];
        }
        for (int i = 0; i<date.length; i++){
            currDate += date[i];
        }
        textViewTime.setText(currDate);

    }

    public void showCalender(){
        addButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DateChooserFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }




    public void nextButton(){
        nextButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textViewTime.getText().toString().equals(currentDateString)){
                    addOrder();
                }

            }
        });
    }

    public void addOrder(){
        long highestOrderID = db.getHighestOrderID();
        ++highestOrderID;
        for (Kontakt kontakt : nameArrayList){
            db.addDataOrder(highestOrderID,kontakt.getNavn(),nameResturant,textViewTime.getText().toString());
        }
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        intentBack();
    }

    public void intentBack(){
        Intent intent = new Intent(this, FriendsViewActivity.class);
        getIntent().removeExtra("nameList");
        getIntent().removeExtra("NAMEFRIEND");
        getIntent().removeExtra("NAMERESTURANT");
        getIntent().removeExtra("TLF");

        startActivity(intent);
        finish();
    }


}
