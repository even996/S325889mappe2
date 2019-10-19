package com.example.s325889mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter2 extends ArrayAdapter {


    private LayoutInflater inflater;
    private ArrayList<Restaurant> restaurants;
    private int ID;


    public CustomAdapter2(Context context, int textViewResourceId, ArrayList<Restaurant> restaurants) {
        super(context,textViewResourceId,restaurants);
        this.restaurants=restaurants;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ID = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(ID, null);

        Restaurant restaurant = restaurants.get(position);
        if (restaurant != null) {
            TextView name = (TextView) convertView.findViewById(R.id.textView_name_resturante);
            TextView tlf = (TextView) convertView.findViewById(R.id.textView_telefon_resturante);
            if (name != null) {
                name.setText(restaurant.getNavn());
            }
            if (tlf != null) {
                tlf.setText(restaurant.getTelefonNr());
            }
        }

        return convertView;
    }

}
















