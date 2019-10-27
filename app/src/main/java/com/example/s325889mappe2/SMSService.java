package com.example.s325889mappe2;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SMSService extends Service {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        System.out.println("Nå kjører vi");
        String time = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("TIME","");
        System.out.println("TIDEN ER SATT TIL: " + time);
        if (currentTime.equals(time)){
            System.out.println("TRUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + currentTime);


        System.out.println(currentTime + "@@@@@@@@@@@@@@@@@@@blaaaaaaaaaaaaaaaaaaaaa");
        //String tlf, name, rest,message;
        ArrayList<Kontakt> kontaktArrayList = new ArrayList<>();
            if (checkPermission(Manifest.permission.SEND_SMS)) {
                String message = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("MESSAGE","");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
                System.out.println(sdf2.format(new Date()) + " Dette er datoformatet som blir sendt inn til DB for sjekk");
                SmsManager smsManager = SmsManager.getDefault();
                Database db = new Database(this);
                Cursor cursor = db.getPhoneNumbers(sdf2.format(new Date()));
                while (cursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(), "SMS TIL " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                    System.out.println(cursor.getString(0) + " DETTE ER CURSOR GETSTRING");
                    smsManager.sendTextMessage(cursor.getString(0), null, message, null, null);
                    System.out.println("SKAL HA SENDT SMS NAA TIL EN PERSON");
                }
                db.close();
                /*tlf = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFTLF","");
                name = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFNAME","");
                rest = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREFREST","");
                String[] tlfArray = tlf.split("\n");
                String[] nameArray = name.split("\n");
                Kontakt kontakt;
                for (int i = 1; i<tlfArray.length; i++) {
                    //System.out.println("navn: " + nameArray[i] + " tlf " + tlfArray[i] + " INNE I ARRAYLOOP");
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
                    Date d = new Date();
                    //System.out.println(kontakter.getTelefon() + " navn: " + name + " rest " + rest + "I FOREACH LOOP BEFORE SMS");

                }*/
            }
        }
        this.stopSelf();
        return super.onStartCommand(intent,flags,startId);
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onDestroy() {
        System.out.println("DESTROYER SMS SERVICE");
        this.stopSelf();
        SettPeriodiskService.isSMSServiceRunning = false;
        super.onDestroy();
    }
}
