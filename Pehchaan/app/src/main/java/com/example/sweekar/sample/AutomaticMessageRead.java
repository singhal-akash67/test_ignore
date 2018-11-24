package com.example.sweekar.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class AutomaticMessageRead extends BroadcastReceiver {
    private callbacktoinsertotp temp;
    AutomaticMessageRead(Context temp)
    {
        this.temp=(callbacktoinsertotp) temp;

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        String strMessage = "";

        if ( extras != null )
        {
            Object[] smsextras = (Object[]) extras.get( "pdus" );

            for ( int i = 0; i < smsextras.length; i++ )
            {
                SmsMessage smsmsg = SmsMessage.createFromPdu((byte[])smsextras[i]);
                String strMsgBody = smsmsg.getMessageBody().toString();
                String otp=strMsgBody.substring(0,5);
                temp.callbackregister(otp);
            }

        }
    }
    interface callbacktoinsertotp
    {
        public void callbackregister(String message);
    }
}
