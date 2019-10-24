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



public class MinService extends Service {

    @Override

    public IBinder onBind(Intent arg0){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, OrdersActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, 0);
        Notification notifikation = new Notification.Builder(this)
                .setContentTitle("Se dine bestillinger")
                .setContentText("Teksttekst")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent).build();
        if(notifikation.flags != Notification.FLAG_AUTO_CANCEL)
            notificationManager.notify(0, notifikation);
        return super.onStartCommand(intent,flags,startId);
    }
}
