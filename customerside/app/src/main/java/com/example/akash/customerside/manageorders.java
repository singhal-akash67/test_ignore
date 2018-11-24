package com.example.akash.customerside;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static java.util.Calendar.DAY_OF_WEEK;
public class manageorders extends AppCompatActivity  {
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isConnected())
        {
            Toast.makeText(this, "Internet not connected", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Downloads");
        progressDialog.setMessage("Downloading Data...");
        progressDialog.show();
        RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(this).getRequestQueue();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://pehchanindia.in/user/getordersforandroid.php?mobile=");
        localStringBuilder.append(getSharedPreferences("userdetails", 0).getString("mobile", null));
        String str = localStringBuilder.toString();
        JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(final JSONObject paramAnonymousJSONObject) {
                setContentView(R.layout.activity_manageorders);
                TextView ordersremainging=(TextView)findViewById(R.id.ordersremaining);
                final TextView nextdate=(TextView)findViewById(R.id.nextdate);
                Button cancelfornextdate=(Button)findViewById(R.id.cancelfortomorrow);
                LinearLayout preferences=(LinearLayout)findViewById(R.id.preferences);
                try {
                    if(paramAnonymousJSONObject.getString("status").equalsIgnoreCase("nothing"))
                    {
                        Toast.makeText(manageorders.this, "You don't have any orders", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else if(paramAnonymousJSONObject.getJSONObject("data").getString("bcount").equalsIgnoreCase("0"))
                    {
                        Toast.makeText(manageorders.this, "Your orders have exhausted", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else if (paramAnonymousJSONObject.getJSONObject("data").getString("SubscriptionId").equalsIgnoreCase("1")) {
                        ordersremainging.setText(paramAnonymousJSONObject.getJSONObject("data").getString("bcount"));
                        cancelfornextdate.setEnabled(false);
                        nextdate.setText(paramAnonymousJSONObject.getJSONObject("data").getString("nextdate"));
                        preferences.setVisibility(View.INVISIBLE);
                    }
                    else {
                        ordersremainging.setText(paramAnonymousJSONObject.getJSONObject("data").getString("bcount"));
                        nextdate.setText(paramAnonymousJSONObject.getJSONObject("data").getString("nextdate"));
                        if (Integer.valueOf(paramAnonymousJSONObject.getJSONObject("data").getString("bcount")) > 0) {
                            cancelfornextdate.setEnabled(true);
                            cancelfornextdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String nextdatevalue=nextdate.getText().toString();
                                    Date date=null;
                                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                                    try {
                                         date = df.parse(nextdatevalue);
                                        System.out.println(date);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Calendar c = Calendar.getInstance();
                                    c.setTime(date);

                                    int todayday = c.get(DAY_OF_WEEK);
                                    c.add(Calendar.DATE, 1);
                                    int day = c.get(DAY_OF_WEEK);
                                    int dayvalue=0;
                                    while(true) {
                                        if(day==Calendar.SUNDAY&&todayday<day)
                                        {
                                            dayvalue=6;
                                        }
                                        else if(day==Calendar.MONDAY&&todayday<day)
                                        {
                                            dayvalue=0;

                                        }
                                        else if(day==Calendar.TUESDAY&&todayday<day)
                                        {
                                            dayvalue=1;

                                        }
                                        else if(day==Calendar.WEDNESDAY&&todayday<day)
                                        {
                                            dayvalue=2;

                                        }
                                        else if(day==Calendar.THURSDAY&&todayday<day)
                                        {
                                            dayvalue=3;

                                        }
                                        else if(day==Calendar.FRIDAY&&todayday<day)
                                        {
                                            dayvalue=4;

                                        }
                                        else if(day==Calendar.SATURDAY&&todayday<day)
                                        {
                                            dayvalue=5;

                                        }

                                        if(day==Calendar.SUNDAY)
                                        {
                                            todayday=-1;
                                        }
                                        if(items.contains(String.valueOf(dayvalue)))
                                        {
                                            Date temp=c.getTime();
                                             df = new SimpleDateFormat("dd-MMM-yyyy");
                                            nextdatevalue = df.format(temp);
                                            break;
                                        }
                                        else {
                                            c.add(Calendar.DATE,1);
                                            day = c.get(DAY_OF_WEEK);
                                        }
                                    }
                                    RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(manageorders.this).getRequestQueue();
                                    StringBuilder localStringBuilder1 = new StringBuilder();
                                    localStringBuilder1.append("http://www.pehchanindia.in/user/updateorder.php?orderid=");
                                    try {
                                        localStringBuilder1.append(paramAnonymousJSONObject.getJSONObject("data").getString("ID"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    localStringBuilder1.append("&dates=");
                                    localStringBuilder1.append(nextdatevalue);
                                    String str4 = localStringBuilder1.toString();
                                    JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str4, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject paramAnonymousJSONObject) {
                                            finish();
                                            startActivity(new Intent(manageorders.this, MainActivity.class));
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(manageorders.this, error.toString(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    localRequestQueue.add(jsonobject);

                                }
                            });

                        }
                        items = Arrays.asList(paramAnonymousJSONObject.getJSONObject("data").getString("Days").split("\\s*,\\s*"));
                        CheckBox monday = (CheckBox) findViewById(R.id.Monday);
                        monday.setEnabled(false);
                        CheckBox tuesday = (CheckBox) findViewById(R.id.Tuesday);
                        tuesday.setEnabled(false);
                        CheckBox wednesday = (CheckBox) findViewById(R.id.Wednesday);
                        wednesday.setEnabled(false);
                        CheckBox thursday = (CheckBox) findViewById(R.id.Thursday);
                        thursday.setEnabled(false);
                        CheckBox friday = (CheckBox) findViewById(R.id.Friday);
                        friday.setEnabled(false);
                        for (String element : items) {
                            int temp = Integer.valueOf(element);
                            if (temp == 1) {
                                monday.setChecked(true);
                            } else if (temp == 2) {
                                tuesday.setChecked(true);
                            } else if (temp == 3) {
                                wednesday.setChecked(true);
                            } else if (temp == 4) {
                                thursday.setChecked(true);
                            } else if (temp == 5) {
                                friday.setChecked(true);
                            }
                        }
                    }

                    } catch(JSONException e){
                        e.printStackTrace();
                    }

                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        localRequestQueue.add(jsonobject);
    }

    private boolean isConnected()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


