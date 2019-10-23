package com.example.s325889mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MinBroadcastReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I order", Toast.LENGTH_SHORT).show();
        /*
        Intent i = new Intent(context,MinService.class);
        context.startService(i);
         */

        Intent i = new Intent(context, SettPeriodiskService.class);
        context.startService(i);
    }
}
