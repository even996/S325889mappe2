package com.example.s325889mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class FriendsCustomAdapter extends ArrayAdapter {


    private LayoutInflater inflater;
    private ArrayList<Kontakt> kontakts;
    private int ID;


    public FriendsCustomAdapter(Context context, int textViewResourceId, ArrayList<Kontakt> kontakts) {
        super(context,textViewResourceId,kontakts);
        this.kontakts=kontakts;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ID = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(ID, null);

        Kontakt kontakt = kontakts.get(position);
        if (kontakt != null) {
            TextView name = (TextView) convertView.findViewById(R.id.textView_name);
            TextView tlf = (TextView) convertView.findViewById(R.id.textView_telefon);
            if (name != null) {
                name.setText(kontakt.getNavn());
            }
            if (tlf != null) {
                tlf.setText(kontakt.getTelefon());
            }
        }

        return convertView;
    }

}
















