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
        String time = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("TIME","");
        if (currentTime.equals(time)){


        ArrayList<Kontakt> kontaktArrayList = new ArrayList<>();
            if (checkPermission(Manifest.permission.SEND_SMS)) {
                String message = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("MESSAGE","");
                SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
                SmsManager smsManager = SmsManager.getDefault();
                Database db = new Database(this);
                Cursor cursor = db.getPhoneNumbers(sdf2.format(new Date()));
                while (cursor.moveToNext()) {
                    smsManager.sendTextMessage(cursor.getString(0), null, message, null, null);
                }
                db.close();

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
