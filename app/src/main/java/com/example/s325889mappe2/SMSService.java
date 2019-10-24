package com.example.s325889mappe2;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SMSService extends Service {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(getApplicationContext(), "SENDER SMS N", Toast.LENGTH_SHORT).show();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String tlf, name, rest,message;
        ArrayList<Kontakt> kontaktArrayList = new ArrayList<>();
        if (checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            tlf = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFTLF","");
            name = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFNAME","");
            rest = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFREST","");
            String[] tlfArray = tlf.split("\n");
            String[] nameArray = name.split("\n");
            Kontakt kontakt;
            for (int i = 1; i<tlfArray.length; i++) {
                System.out.println("navn: " + nameArray[i] + " tlf " + tlfArray[i] + " INNE I ARRAYLOOP");
                kontakt = new Kontakt(nameArray[i],tlfArray[i]);
                kontaktArrayList.add(kontakt);
            }
            name = null;
            for (Kontakt kontakter : kontaktArrayList){
                for (Kontakt kont : kontaktArrayList) {
                    if (kont != kontakter) {
                        name += kont.navn + ",";
                        //smsManager.sendTextMessage(kont.telefon, null, "", null, null);
                    }
                }
                message = "You have an appointment with " + name + " today at " + rest;
                System.out.println(kontakter.getTelefon() + " navn: " + name + " rest " + rest + "I FOREACH LOOP BEFORE SMS");
                smsManager.sendTextMessage(kontakter.getTelefon(), null, message, null, null);
            }
        }

        return super.onStartCommand(intent,flags,startId);
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
