package com.example.s325889mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class SettPeriodiskService extends Service {

    @Nullable
    @Override

    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null)
            return super.onStartCommand(intent,flags,startId);
        if (intent.getStringExtra("Broadcast").equals("Notifikasjon")){
            System.out.println("Kjører notifikasjon i periodisk");
            startNotification();
        }else if (intent.getStringExtra("Broadcast").equals("SMS") || getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPSMS","").equals("ON")){
            System.out.println("Kjører SMS i periodisk");
            startSMS();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void startNotification(){
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, MinService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
    }

    private void startSMS(){
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
