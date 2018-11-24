package com.example.akash.customerside;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class alarmreceiver
        extends BroadcastReceiver
{
    public void onReceive(Context paramContext, Intent paramIntent)
    {
        Intent localIntent = new Intent(paramContext, eatthisfruit.class);
        localIntent.putExtra("fruitname", paramIntent.getExtras().getString("fruitname"));
        localIntent.putExtra("extrainfo", paramIntent.getExtras().getString("extrainfo"));
        localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(paramContext, "notify_001");
        localBuilder.setContentIntent(PendingIntent.getActivity(paramContext, 0, localIntent, PendingIntent.FLAG_UPDATE_CURRENT));
        localBuilder.setSmallIcon(R.drawable.ic_menu_send);
        localBuilder.setContentTitle(paramIntent.getExtras().getString("title"));
        localBuilder.setContentText(paramIntent.getExtras().getString("body"));
        localBuilder.setPriority(2);
        NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            localNotificationManager.createNotificationChannel(new NotificationChannel("notify_001", "Channel human readable title", NotificationManager.IMPORTANCE_HIGH));
        }
        localNotificationManager.notify(0, localBuilder.build());
    }
}
