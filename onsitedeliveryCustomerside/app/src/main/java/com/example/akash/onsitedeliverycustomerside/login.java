package com.example.akash.onsitedeliverycustomerside;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class login extends AppCompatActivity implements AutomaticMessageRead.callbacktoinsertotp{
    EditText name,mobilenumber;
    Spinner locality;
    Button submitforotpverification;

    String namevalue,mobilenumbervalue,localityvalue;
    String otpvalue;
    EditText otp;
    BroadcastReceiver br;
    Firebase mfirbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        Firebase.setAndroidContext(this);
        mfirbase=new Firebase("https://onsitedeliverycustomerside.firebaseio.com/");
    }
    private void init()
    {
        name=(EditText)findViewById(R.id.name);
        mobilenumber=(EditText)findViewById(R.id.mobilenumber);
        locality=(Spinner)findViewById(R.id.locality);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.localities));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locality.setAdapter(dataAdapter);
        locality.setSelection(0);
        submitforotpverification=(Button)findViewById(R.id.submitforotpverification);
    }
    public void setSubmitforotpverification(View v)
    {
        namevalue=name.getText().toString();
        mobilenumbervalue=mobilenumber.getText().toString();
        localityvalue=locality.getSelectedItem().toString();
        if(namevalue.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter name value", Toast.LENGTH_SHORT).show();
            return;

        }
        else if(mobilenumbervalue.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter address value", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(localityvalue.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Choose locality", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!isConnected())
        {
            Toast.makeText(this, "Switch on your mobile data", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            setContentView(R.layout.otpverification);
            initforsecondscreen();
            sendotp(new View(this));
        }

    }
    private void initforsecondscreen()
    {
        this.otp = ((EditText)findViewById(R.id.otp));
    }
    private boolean isConnected()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void sendotp(View paramView)
    {
        if ((ContextCompat.checkSelfPermission(this, "android.permission.READ_SMS") != 0) && (ContextCompat.checkSelfPermission(this, "android.permission.RECEIVE_SMS") != 0))
        {
            ActivityCompat.requestPermissions(this, new String[] { "android.permission.READ_SMS", "android.permission.RECEIVE_SMS" }, 100);
            return;
        }
        this.br = new AutomaticMessageRead(this);
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(this.br, localIntentFilter);
        this.otpvalue = String.valueOf(10000 + new Random().nextInt(89999));
        Log.e("otp", this.otpvalue);
        new CallAPI(this.mobilenumbervalue, this.otpvalue).execute(new Void[0]);
        final Button localButton = (Button)findViewById(R.id.sendotp);
        localButton.setEnabled(false);
        localButton.setAlpha(0.2F);
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                localButton.setEnabled(true);
                localButton.setAlpha(1.0F);
            }
        }, 120000L);
        Toast.makeText(this, "Enter otp upon receiving", Toast.LENGTH_SHORT).show();
    }
    public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
    {
        if (paramInt != 100) {
            return;
        }
        if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0) && (paramArrayOfInt[1] == 0))
        {
            this.br = new AutomaticMessageRead(this);
            IntentFilter localIntentFilter = new IntentFilter();
            localIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
            registerReceiver(this.br, localIntentFilter);
            this.otpvalue = String.valueOf(10000 + new Random().nextInt(89999));
            new CallAPI(this.mobilenumbervalue, this.otpvalue).execute(new Void[0]);
            final Button localButton2 = (Button)findViewById(R.id.sendotp);
            localButton2.setEnabled(false);
            localButton2.setAlpha(0.2F);
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    localButton2.setEnabled(true);
                    localButton2.setAlpha(1.0F);
                }
            }, 120000L);
            Toast.makeText(this, "Enter otp upon receiving", Toast.LENGTH_SHORT).show();
            return;
        }
        this.otpvalue = String.valueOf(10000 + new Random().nextInt(89999));
        new CallAPI(this.mobilenumbervalue, this.otpvalue).execute(new Void[0]);
        final Button localButton1 = (Button)findViewById(R.id.sendotp);
        localButton1.setEnabled(false);
        localButton1.setAlpha(0.2F);
        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                localButton1.setEnabled(true);
                localButton1.setAlpha(1.0F);
            }
        }, 120000L);
        Toast.makeText(this, "Enter otp upon receiving", Toast.LENGTH_SHORT).show();
    }
    public void callbackregister(String paramString)
    {
        otp.setText(paramString);
        submitotp(new View(this));
    }

    public void submitotp(View paramView)
    {
        /*if(otp.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter otp", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!otp.getText().toString().equalsIgnoreCase(otpvalue))
        {
            Toast.makeText(this, "Wrong otp", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if (this.br != null) {
            unregisterReceiver(this.br);
        }
        mfirbase.child("user").child(mobilenumbervalue).child("name").setValue(namevalue);
        mfirbase.child("user").child(mobilenumbervalue).child("mobilenumber").setValue(mobilenumbervalue);
        mfirbase.child("user").child(mobilenumbervalue).child("locality").setValue(localityvalue);

        SharedPreferences temp=getSharedPreferences("register",MODE_PRIVATE);
        SharedPreferences.Editor editor=temp.edit();
        editor.putInt("register",1);
        editor.commit();

        SharedPreferences userdetails=getSharedPreferences("userdetails",MODE_PRIVATE);
        SharedPreferences.Editor edi=userdetails.edit();
        edi.putString("name",namevalue);
        edi.putString("locality",localityvalue);
        edi.putString("mobile",mobilenumbervalue);
        edi.commit();
        finish();
        startActivity(new Intent(this,MainActivity.class));

    }

    public class CallAPI
            extends AsyncTask<Void, Void, Void>
    {
        String mobile_number;
        String otpvalue;

        CallAPI(String paramString1, String paramString2)
        {
            this.mobile_number = paramString1;
            this.otpvalue = paramString2;
        }

        protected Void doInBackground(Void... paramVarArgs)
        {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("https://2factor.in/API/V1/b51c2c85-8e7a-11e8-a895-0200cd936042/SMS/");
            localStringBuilder.append(this.mobile_number);
            localStringBuilder.append("/");
            localStringBuilder.append(this.otpvalue);
            String str = localStringBuilder.toString();
            RequestQueue localRequestQueue = Volley.newRequestQueue(login.this.getApplicationContext());
            JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            localRequestQueue.add(jsonobject);
            return null;
        }
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
    }


}

