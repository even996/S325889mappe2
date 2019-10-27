package com.example.s325889mappe2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderCustomAdapter extends ArrayAdapter {


    private LayoutInflater inflater;
    private ArrayList<Order> orders;
    private int ID;


    public OrderCustomAdapter(Context context, int textViewResourceId, ArrayList<Order> orders) {
        super(context,textViewResourceId,orders);
        this.orders=orders;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ID = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = inflater.inflate(ID, null);

        Order order = orders.get(position);
        if (order != null) {
            TextView friend = (TextView) convertView.findViewById(R.id.textView_order_friend);
            TextView time = (TextView) convertView.findViewById(R.id.textView_order_time);
            TextView resturant = (TextView) convertView.findViewById(R.id.textView_order_resturant);

            if (friend != null) {
                friend.setText(order.getFriend());
            }
            if (time != null) {
                time.setText(order.getTime());
            }

            if (resturant != null) {
                resturant.setText(order.getResturant());
            }

        }

        return convertView;
    }

}
















