package com.example.s325889mappe2;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MinService extends Service {

    @Override

    public IBinder onBind(Intent arg0){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        String time = getSharedPreferences("PREFERENCES",MODE_PRIVATE).getString("TIME","");
        System.out.println("TIDEN ER SATT TIL: " + time);
        if (currentTime.equals(time)) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent i = new Intent(this, OrdersActivity.class);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);
            Notification notifikation = new Notification.Builder(this)
                    .setContentTitle("Se dine bestillinger")
                    .setSmallIcon(R.drawable.dinnericon)
                    .setContentIntent(pIntent).build();
            if (notifikation.flags != Notification.FLAG_AUTO_CANCEL)
                notificationManager.notify(0, notifikation);
        }
            this.stopSelf();
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public void onDestroy() {
        System.out.println("DESTROYER NOTIFICATION SERVICE");
        this.stopSelf();
        SettPeriodiskService.isNotificationServiceRunning = false;
        super.onDestroy();
    }
}
