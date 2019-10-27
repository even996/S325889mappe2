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

    public static boolean isSMSServiceRunning = false;
    public static boolean isNotificationServiceRunning = false;
    @Nullable
    @Override

    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(isSMSServiceRunning + " DETTE ER SMSSERVICE RUNNING");
        if (intent == null)
            return super.onStartCommand(intent,flags,startId);

        String smsPREF = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPSMS","");
        String notifiPREF = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("PREPNOTIFI","");
        if (smsPREF.equals("ON")){
            startSMS();
        }else{
            Intent i = new Intent(this, SMSService.class);
            PendingIntent pInent = PendingIntent.getService(this, 0, i, 0);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarm != null) {
                alarm.cancel(pInent);
            }
            stopService(i);
        }
        if (notifiPREF.equals("ON")){
            startNotification();
        }else{
            Intent i = new Intent(this, MinService.class);
            PendingIntent pInent = PendingIntent.getService(this, 0, i, 0);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarm != null) {
                alarm.cancel(pInent);
            }
            stopService(i);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void startNotification(){
        isNotificationServiceRunning = true;
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, MinService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
    }

    private void startSMS(){
        isSMSServiceRunning = true;
        Calendar cal = Calendar.getInstance();
        Intent i = new Intent(this, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(this,0,i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000, pintent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
