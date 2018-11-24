package com.example.akash.customerside;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

import static java.util.Calendar.DAY_OF_WEEK;


public class Orderformation extends AppCompatActivity implements Dialogfordaysselection.NoticeDialogListener
{
    EditText address;
    String addressvalue;
    ArrayList<Integer> daysselected;
    String daysvalue;
    EditText mobile;
    String mobilevalue;
    EditText name;
    String namevalue;
    RadioButton once;
    RadioGroup plantype;
    String plantypevalue;
    String typeofboxvalue = "";
    Spinner typeoforder;
    RadioButton weekly;
    String nextDate;
    private void init(String paramString)
    {
        this.name = ((EditText)findViewById(R.id.name));
        this.address = ((EditText)findViewById(R.id.address));
        this.mobile = ((EditText)findViewById(R.id.mobile));
        int i = 0;
        this.namevalue = getSharedPreferences("userdetails", 0).getString("name", null);
        this.addressvalue = getSharedPreferences("userdetails", 0).getString("address", null);
        this.mobilevalue = getSharedPreferences("userdetails", 0).getString("mobile", null);
        this.name.setText(this.namevalue);
        this.address.setText(this.addressvalue);
        this.mobile.setText(this.mobilevalue);
        this.typeoforder = ((Spinner)findViewById(R.id.typeoforder));
        ArrayAdapter localArrayAdapter = new ArrayAdapter(this,  android.R.layout.simple_list_item_1 , getResources().getStringArray(R.array.typesofboxex));
        this.typeoforder.setAdapter(localArrayAdapter);
            this.typeoforder.setSelection(0);
        this.plantype = ((RadioGroup)findViewById(R.id.plantype));
        this.plantype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
            {
                if (paramAnonymousInt == R.id.weekly)
                {
                    Dialogfordaysselection localDialogfordaysselection = new Dialogfordaysselection();
                    Bundle localBundle = new Bundle();
                    localBundle.putIntegerArrayList("daysselected", Orderformation.this.daysselected);
                    localDialogfordaysselection.setArguments(localBundle);
                    localDialogfordaysselection.show(Orderformation.this.getFragmentManager(), "dayspicker");
                }
            }
        });
        this.weekly = ((RadioButton)findViewById(R.id.weekly));
        this.once = ((RadioButton)findViewById(R.id.once));
    }

    public void createorder(View paramView)
    {
        this.typeofboxvalue = this.typeoforder.getSelectedItem().toString();
        String str1 = this.mobile.getText().toString();
        String str2 = this.name.getText().toString();
        String str3 = this.address.getText().toString();
        if (this.weekly.isChecked())
        {
            this.plantypevalue = this.weekly.getText().toString();
            this.daysvalue = "";
            Iterator localIterator = this.daysselected.iterator();
            while (localIterator.hasNext())
            {
                int i = ((Integer)localIterator.next()).intValue()+1;
                StringBuilder localStringBuilder2 = new StringBuilder();
                localStringBuilder2.append(this.daysvalue);
                localStringBuilder2.append(String.valueOf(i));
                localStringBuilder2.append(",");
                this.daysvalue = localStringBuilder2.toString();
            }

        }
        else {
            this.plantypevalue = this.once.getText().toString();
            this.daysvalue = null;
        }
        if(plantypevalue.equalsIgnoreCase("Once"))
        {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 1);
            Date temp=c.getTime();

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
             nextDate = df.format(temp);

        }
        else
        {
            Calendar c = Calendar.getInstance();
            int todayday = c.get(DAY_OF_WEEK);
            c.add(Calendar.DATE, 1);
            int day = c.get(DAY_OF_WEEK);
            int dayvalue=0;
            Iterator localIterator = this.daysselected.iterator();
            Log.e("temp", String.valueOf(((Integer)localIterator.next()).intValue())+c.getTime());

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


                if(daysselected.contains(dayvalue))
                {

                    Date temp=c.getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                    nextDate = df.format(temp);
                    break;
                }
                else {
                    c.add(Calendar.DATE,1);
                    day = c.get(DAY_OF_WEEK);
                }
            }



        }
        RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(this).getRequestQueue();
        StringBuilder localStringBuilder1 = new StringBuilder();
        localStringBuilder1.append("http://www.pehchanindia.in/user/InsertionOfOrders.php?mobile=");
        localStringBuilder1.append(this.mobilevalue);
        localStringBuilder1.append("&typeofplan=");
        localStringBuilder1.append(this.plantypevalue);
        localStringBuilder1.append("&typeofbox=");
        localStringBuilder1.append(this.typeofboxvalue);
        localStringBuilder1.append("&days=");
        localStringBuilder1.append(this.daysvalue);
        localStringBuilder1.append("&targetmobile=");
        localStringBuilder1.append(str1);
        localStringBuilder1.append("&targetname=");
        localStringBuilder1.append(str2);
        localStringBuilder1.append("&targetaddress=");
        localStringBuilder1.append(str3);
        localStringBuilder1.append("&dates=");
        localStringBuilder1.append(nextDate);
        String str4 = localStringBuilder1.toString();

        JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str4, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject paramAnonymousJSONObject) {
                try
                {
                    if (paramAnonymousJSONObject.getString("success").equalsIgnoreCase("true"))
                    {
                        FirebaseMessaging.getInstance().subscribeToTopic(typeofboxvalue.replace(" ",""))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                        Toast.makeText(Orderformation.this, "Order placed", Toast.LENGTH_SHORT).show();
                        Orderformation.this.finish();
                        Orderformation.this.startActivity(new Intent(Orderformation.this, MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(Orderformation.this, "Error with server", 0).show();
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
                Toast.makeText(Orderformation.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });















        localRequestQueue.add(jsonobject);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_orderformation);
        init(getIntent().getStringExtra(getResources().getString(R.string.typeoforder)));
        this.daysselected = null;
    }

    public void onDialogNegativeClick()
    {
        this.daysselected = null;
        this.once.setChecked(true);
    }

    public void onDialogPositiveClick(ArrayList paramArrayList)
    {
        this.daysselected = paramArrayList;
    }
}