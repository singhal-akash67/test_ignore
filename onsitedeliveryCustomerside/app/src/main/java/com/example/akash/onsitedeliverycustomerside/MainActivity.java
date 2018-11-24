package com.example.akash.onsitedeliverycustomerside;

import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    Firebase mfirbase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(getSharedPreferences("register",MODE_PRIVATE).getInt("register",0)==0)
        {
            finish();
            startActivity(new Intent(this,login.class));
        }
        else if(isConnected())
        {
            finish();
            startActivity(new Intent(this,MapsActivity.class));
        }
        else
        {

        }

    }
    private boolean isConnected()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
