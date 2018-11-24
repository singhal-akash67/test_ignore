package com.example.akash.customerside;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements AutomaticMessageRead.callbacktoinsertotp
{
    EditText address;
    String addressvalue;
    BroadcastReceiver br = null;
    EditText name;
    String namevalue;
    Button nextscreen;
    EditText otp;
    String otpvalue;
    EditText phonenumber;
    String phonenumbervalue;

    private void calltomainactivity(int paramInt)
    {
        if (paramInt == 1)
        {
            RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(this).getRequestQueue();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("http://www.pehchanindia.in/user/insertuserprofile.php?mobile=");
            localStringBuilder.append(this.phonenumbervalue);
            localStringBuilder.append("&name=");
            localStringBuilder.append(this.namevalue);
            localStringBuilder.append("&address=");
            localStringBuilder.append(this.addressvalue);
            String str = localStringBuilder.toString();
            JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject paramAnonymousJSONObject) {

                    try
                    {
                        if (paramAnonymousJSONObject.getString("success").equalsIgnoreCase("true"))
                        {
                            Login.this.getSharedPreferences("register", 0).edit().putInt("registervalue", 2).commit();
                            SharedPreferences.Editor localEditor = Login.this.getSharedPreferences("userdetails", 0).edit();
                            localEditor.putString("name", Login.this.namevalue);
                            localEditor.putString("address", Login.this.addressvalue);
                            localEditor.commit();
                            Login.this.calltomainactivity(0);
                            return;
                        }
                        if (paramAnonymousJSONObject.getString("success").equalsIgnoreCase("false")) {
                            Toast.makeText(Login.this, "Error with server Connect after some time", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }
                    catch (JSONException localJSONException)
                    {
                        localJSONException.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();


                }
            });

            localRequestQueue.add(jsonobject);
            return;
        }
        if (paramInt == 0)
        {
            Intent localIntent = new Intent(this, MainActivity.class);
            finish();
            startActivity(localIntent);
        }
    }

    private void init()
    {
        this.phonenumber = ((EditText)findViewById(R.id.phonenumber));
        this.nextscreen = ((Button)findViewById(R.id.otppage));
    }

    private void initforsecondscreen()
    {
        this.otp = ((EditText)findViewById(R.id.otp));
    }

    private void initforthirdscreen()
    {
        this.name = ((EditText)findViewById(R.id.name));
        this.address = ((EditText)findViewById(R.id.address));
    }

    private boolean isConnected()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void callbackregister(String paramString)
    {
        otp.setText(paramString);
        submitotp(new View(this));
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (getSharedPreferences("register", 0).getInt("registervalue", 0) == 1)
        {
            this.phonenumbervalue = getSharedPreferences("userdetails", 0).getString("mobile", null);
            setContentView(R.layout.userinfo);
            initforthirdscreen();
            return;
        }
        setContentView(R.layout.activity_login);
        init();
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
            new CallAPI(this.phonenumbervalue, this.otpvalue).execute(new Void[0]);
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
        new CallAPI(this.phonenumbervalue, this.otpvalue).execute(new Void[0]);
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

    public void otpscreen(View paramView)
    {
        this.phonenumbervalue = this.phonenumber.getText().toString();
        if ((!this.phonenumbervalue.equalsIgnoreCase("")) && (this.phonenumbervalue.length() == 10))
        {
            if (!isConnected())
            {
                Toast.makeText(this, "Switch on mobile data for otp verfication", Toast.LENGTH_SHORT).show();
                return;
            }
            setContentView(R.layout.otpverification);
            initforsecondscreen();
            sendotp(new View(this));
            return;
        }
        Toast.makeText(this, "invalid phonenumber", Toast.LENGTH_SHORT).show();
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
        new CallAPI(this.phonenumbervalue, this.otpvalue).execute(new Void[0]);
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

    public void submit(View paramView)
    {
        if (this.name.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.address.getText().toString().equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isConnected())
        {
            Toast.makeText(this, "Switch on mobile data for otp verfication", Toast.LENGTH_SHORT).show();
            return;
        }
        this.namevalue = this.name.getText().toString();
        this.addressvalue = this.address.getText().toString();
        calltomainactivity(1);
    }

    public void submitotp(View paramView)
    {
        if (this.br != null) {
            unregisterReceiver(this.br);
        }
        getSharedPreferences("register", 0).edit().putInt("registervalue", 1).commit();
        final SharedPreferences.Editor localEditor = getSharedPreferences("userdetails", 0).edit();
        localEditor.putString("mobile", this.phonenumbervalue).commit();
        RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(this).getRequestQueue();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://www.pehchanindia.in/user/loginapp.php?mobile=");
        localStringBuilder.append(this.phonenumbervalue);
        String str = localStringBuilder.toString();
        JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject paramAnonymousJSONObject) {
                try
                {
                    if (paramAnonymousJSONObject.getString("name").length() > 0)
                    {
                        Login.this.namevalue = paramAnonymousJSONObject.getString("name");
                        Login.this.addressvalue = paramAnonymousJSONObject.getString("location");
                        localEditor.putString("name", Login.this.namevalue).commit();
                        localEditor.putString("address", Login.this.addressvalue).commit();
                        Login.this.getSharedPreferences("register", 0).edit().putInt("registervalue", 2).commit();
                        Login.this.calltomainactivity(0);
                    }
                    else
                    {
                        Login.this.setContentView(R.layout.userinfo);
                        Login.this.initforthirdscreen();
                    }
                    return;
                }
                catch (JSONException localJSONException)
                {
                    localJSONException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        localRequestQueue.add(jsonobject);
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
            RequestQueue localRequestQueue = Volley.newRequestQueue(Login.this.getApplicationContext());
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