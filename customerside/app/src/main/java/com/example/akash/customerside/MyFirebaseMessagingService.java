package com.example.akash.customerside;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;
import java.util.Map;

public class MyFirebaseMessagingService
        extends FirebaseMessagingService
{
    @RequiresApi(api=26)
    public void onMessageReceived(RemoteMessage paramRemoteMessage)
    {
        Intent localIntent = new Intent(this, eatthisfruit.class);
        if (paramRemoteMessage.getData().size() > 0)
        {
            if (paramRemoteMessage.getData().get("fruitname") != null) {
                localIntent.putExtra("fruitname", ((String)paramRemoteMessage.getData().get("fruitname")).toString());
            } else {
                localIntent.putExtra("fruitname", "Default");
            }
            if (paramRemoteMessage.getData().get("extrainfo") != null) {
                localIntent.putExtra("extrainfo", ((String)paramRemoteMessage.getData().get("fruitname")).toString());
            } else {
                localIntent.putExtra("extrainfo", "Fruits make u healthy");
            }
        }
        if (paramRemoteMessage.getNotification() != null)
        {
            if (paramRemoteMessage.getNotification().getTitle() != null) {
                localIntent.putExtra("title", paramRemoteMessage.getNotification().getTitle());
            } else {
                localIntent.putExtra("title", "Reminder");
            }
            if (paramRemoteMessage.getNotification().getBody() != null) {
                localIntent.putExtra("body", paramRemoteMessage.getNotification().getBody());
            } else {
                localIntent.putExtra("body", "Fruits make you healthy");
            }
            localIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(getApplicationContext(), "notify_001");
            localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, localIntent, PendingIntent.FLAG_UPDATE_CURRENT));
            localBuilder.setSmallIcon(R.drawable.ic_menu_send);
            localBuilder.setContentTitle(paramRemoteMessage.getNotification().getTitle());
            localBuilder.setContentText(paramRemoteMessage.getNotification().getBody());
            localBuilder.setPriority(2);
            NotificationManager localNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= 26) {
                localNotificationManager.createNotificationChannel(new NotificationChannel("notify_001", "Channel human readable title", NotificationManager.IMPORTANCE_HIGH));
            }
            localNotificationManager.notify(0, localBuilder.build());
        }
    }
}
