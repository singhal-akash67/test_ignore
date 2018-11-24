package com.example.akash.onsitedeliverycustomerside;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class AutomaticMessageRead
        extends BroadcastReceiver
{
    private callbacktoinsertotp temp;

    AutomaticMessageRead(Context paramContext)
    {
        this.temp = ((callbacktoinsertotp)paramContext);
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
        Bundle localBundle = paramIntent.getExtras();
        if (localBundle != null)
        {
            Object[] arrayOfObject = (Object[])localBundle.get("pdus");
            for (int i = 0; i < arrayOfObject.length; i++)
            {
                String str = SmsMessage.createFromPdu((byte[])arrayOfObject[i]).getMessageBody().toString().substring(0, 5);
                this.temp.callbackregister(str);
            }
        }
    }

    static abstract interface callbacktoinsertotp
    {
        public abstract void callbackregister(String paramString);
    }
}