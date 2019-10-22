package com.example.s325889mappe2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class Order {

    long id;
    String friend;
    String resturant;
    String time;


    public Order(long id, String friend, String resturant, String time) {
        this.id = id;
        this.friend = friend;
        this.resturant = resturant;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getResturant() {
        return resturant;
    }

    public void setResturant(String resturant) {
        this.resturant = resturant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
