package com.example.sweekar.sample;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import static android.content.Context.NOTIFICATION_SERVICE;

public class alarmreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification=new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.pehchaan_icon);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Daily Report");
        Date objDate = new Date(); // Current System Date and time is assigned to objDate
        String strDateFormat = "hh:mm:ss a dd-MMM-yyyy"; //Date format is Specified
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
        String time=objSDF.format(objDate).toLowerCase();
        notification.setContentText(time);
        Intent iintent=new Intent(context,Notificationatnight.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, iintent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        NotificationManager nf=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        nf.notify(0,notification.build());
    }
}
