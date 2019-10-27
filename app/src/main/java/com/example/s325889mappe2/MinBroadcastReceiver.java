package com.example.s325889mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MinBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, SettPeriodiskService.class);
        if (intent.getAction().equals("com.example.servicebroadcast.notifikasjonbroadcast"))
            i.putExtra("Broadcast","Notifikasjon");
        else if (intent.getAction().equals("com.example.servicebroadcast.smsbroadcast"))
            i.putExtra("Broadcast","SMS");
        if (i != null) {
            context.stopService(i);
            context.startService(i);
        }
    }
}
