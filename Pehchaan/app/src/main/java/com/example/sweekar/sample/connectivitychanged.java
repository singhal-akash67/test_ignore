package com.example.sweekar.sample;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;
import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;
public class connectivitychanged extends BroadcastReceiver{
    Firebase mfirbase;
    Databaseh db;
    SharedPreferences sharedPreferences;
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            db = new Databaseh(context);
            Firebase.setAndroidContext(context);
            sharedPreferences = context.getSharedPreferences("mypreferences", MODE_PRIVATE);
            mfirbase = new Firebase("https://pehchaan-f3f07.firebaseio.com/");
            String currentdate = DateFormat.getDateInstance().format(new Date());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String previousdate = sharedPreferences.getString("currentdate", NULL);
            if (previousdate == NULL || previousdate.equalsIgnoreCase(currentdate)) {
                editor.remove("currentdate");
                List<com.example.sweekar.sample.Transaction> transactions=db.getTransactions();
                for(com.example.sweekar.sample.Transaction temp:transactions) {
                    String k=temp.hawker_id+String.valueOf(temp.transaction_id);
                    mfirbase.child("transactions").child(String.valueOf(k)).setValue(temp);
                }
                Hawker hawker=db.findHawker();

                    mfirbase.child("hawker").child(String.valueOf(hawker.hawkerid)).setValue(hawker);

                List<com.example.sweekar.sample.TransactionProducts> transactionProducts=db.getTransactionProducts();
                for(com.example.sweekar.sample.TransactionProducts temp:transactionProducts) {
                    String k=temp.hawkerid+String.valueOf(temp.transactionid)+temp.productname;
                    Log.e("productname",k);
                    mfirbase.child("producttransaction").child(k).child("hawkerid").setValue(temp.hawkerid);
                    mfirbase.child("producttransaction").child(k).child("transactionid").setValue(temp.transactionid);
                    mfirbase.child("producttransaction").child(k).child("productname").setValue(temp.productname);
                }
                List<Referral> tempi=db.returnreferrals();
                for(com.example.sweekar.sample.Referral referral:tempi)
                {
                    String k=referral.hawkerid+String.valueOf(referral.referredby)+String.valueOf(referral.referredto);
                    mfirbase.child("referrals").child(k).setValue(referral);
                }
                editor.putString("currentdate", currentdate);
            }
        }
    }
}
