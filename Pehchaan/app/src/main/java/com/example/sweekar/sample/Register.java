package com.example.sweekar.sample;

import android.Manifest;
import android.app.PendingIntent;
import android.app.VoiceInteractor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;


public class Register extends AppCompatActivity implements AutomaticMessageRead.callbacktoinsertotp {
    Databaseh db;
    String otpvalue;
    BroadcastReceiver br;
    Hawker newhawker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new Databaseh(this);
        Spinner spinner=(Spinner)findViewById(R.id.area);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.areastring));
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        Spinner spinnerfortypeofvendor=(Spinner)findViewById(R.id.type);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.typeofvendor));
        spinnerfortypeofvendor.setAdapter(adapter2);
        spinnerfortypeofvendor.setSelection(0);
    }
    public void sendotp(View v)
    {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if(!isConnected)
        {
            Toast.makeText(this, "Switch on mobile data for otp verfication", Toast.LENGTH_SHORT).show();
            return;
        }
        int a=new Random().nextInt(89999)+10000;
        otpvalue=String.valueOf(a);

        new CallAPI(newhawker.phonenumber,otpvalue).execute();
        final Button sendotp=(Button)findViewById(R.id.sendotp);
        sendotp.setEnabled(false);
        sendotp.setAlpha((float) 0.2);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendotp.setEnabled(true);
                sendotp.setAlpha(1);
            }
        }, 120000);
        Toast.makeText(this, "Enter otp upon receiving", Toast.LENGTH_SHORT).show();
         br = new AutomaticMessageRead(this);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(br, intentFilter);
    }

    @Override
    public void callbackregister(String message) {
        EditText otp=(EditText)findViewById(R.id.otp);
        otp.setText(message);
        forotpscreen();
    }

    public void submit(View v) {
        RadioGroup gender = (RadioGroup) findViewById(R.id.gender);
        int id=gender.getCheckedRadioButtonId();
        RadioButton genderbutton=(RadioButton)findViewById(id);
        Spinner experience = (Spinner) findViewById(R.id.experience);
        EditText totalexpectedcustomers = (EditText) findViewById(R.id.totalexpectedcustomers);
        EditText repeatexpectedcustomers=(EditText)findViewById(R.id.repeatexpectedcustomers);

        if (experience.getSelectedItem().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter experience", Toast.LENGTH_SHORT).show();
        } else if (totalexpectedcustomers.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the total expected customers", Toast.LENGTH_SHORT).show();
        } else if (repeatexpectedcustomers.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the repeat expected customers", Toast.LENGTH_SHORT).show();
        }
        else {
            newhawker.gender=genderbutton.getText().toString();
            newhawker.experiece=experience.getSelectedItem().toString();
            newhawker.totalexpectedcustomers=Integer.valueOf(totalexpectedcustomers.getText().toString());
            newhawker.repeatexpectedcustomers=Integer.valueOf(repeatexpectedcustomers.getText().toString());
            db.addHawker(newhawker);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).commit();
            finish();
            Intent intent = new Intent(this, FruitSelectionPage.class);
            startActivity(intent);
        }


    }
    public void forotpscreen()
    {
        EditText otp=(EditText)findViewById(R.id.otp);
       /* if(otp.getText().toString().equalsIgnoreCase(otpvalue)==false)
        {
            Toast.makeText(this, "Wrong otp", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
        */
            newhawker.isverified = 1;
            unregisterReceiver(br);
            setContentView(R.layout.finalregisterationscreen);
            Spinner spinner=(Spinner)findViewById(R.id.experience);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.experiencestring));
            spinner.setAdapter(adapter);
            spinner.setSelection(0);
        /*}*/
    }
    public void forotpscreen(View v)/*If user enters otp manually*/
    {
        EditText otp=(EditText)findViewById(R.id.otp);
      /* if(otp.getText().toString().equalsIgnoreCase(otpvalue)==false)
        {
            Toast.makeText(this, "Wrong otp", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
        */
            newhawker.isverified = 1;
            unregisterReceiver(br);
            setContentView(R.layout.finalregisterationscreen);
        Spinner spinner=(Spinner)findViewById(R.id.experience);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.experiencestring));
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        //}
    }
    public void next(View v)
    {

        Spinner area = (Spinner) findViewById(R.id.area);
        EditText name = (EditText) findViewById(R.id.hawkername);
        EditText hawkerid=(EditText)findViewById(R.id.hawkerid);
        EditText mobile_number=(EditText)findViewById(R.id.phonenumber);
        Spinner type=(Spinner)findViewById(R.id.type);


        if(hawkerid.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter id", Toast.LENGTH_SHORT).show();
        }
        else if (name.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else if (area.getSelectedItem().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the area", Toast.LENGTH_SHORT).show();
        }
        else if(mobile_number.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Mobile no. should be of 10 digits", Toast.LENGTH_SHORT).show();
        }
        else if(mobile_number.getText().toString().length()!=10)
        {
            Toast.makeText(this, "Mobile no. should be of 10 digits", Toast.LENGTH_SHORT).show();

        }

        else {
            Log.e("SAd",type.getSelectedItem().toString());
            newhawker =new Hawker(hawkerid.getText().toString(),name.getText().toString(), area.getSelectedItem().toString(),mobile_number.getText().toString(),type.getSelectedItem().toString());
            setContentView(R.layout.registerationpart2);
            sendotp(new View(this));

        }

    }
    @Override
    public void onBackPressed()
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }


    public class CallAPI extends AsyncTask< Void,Void,Void> {

    String mobile_number;
    String otpvalue;
    CallAPI(String mobile_number,String otpvalue)
    {
        this.mobile_number=mobile_number;
        this.otpvalue=otpvalue;
    }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String urlString = "https://2factor.in/API/V1/b51c2c85-8e7a-11e8-a895-0200cd936042/SMS/" +mobile_number+ "/" +otpvalue;

            RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, urlString, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(jsonobject);
            return null;
        }
    }
}
