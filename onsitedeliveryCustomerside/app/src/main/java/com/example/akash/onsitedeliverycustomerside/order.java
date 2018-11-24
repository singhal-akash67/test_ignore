package com.example.akash.onsitedeliverycustomerside;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class order extends AppCompatActivity {
   List<String> a=new ArrayList<String>();
   List<String> cart=new ArrayList<String>();
   Firebase mfirbase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        a.add("apple");
        a.add("banana");
        a.add("chikoo");
        a.add("coconut");
        a.add("orange");
        a.add("dates");
        a.add("mangopa");
        a.add("mangoda");
        a.add("proe");
        a.add("mangoke");
        a.add("watermelon");




        LinearLayout fruitscontainer = (LinearLayout) findViewById(R.id.fruits);
        Iterator iter = a.iterator();
        while (iter.hasNext()) {
            LinearLayout fruitscolumn = new LinearLayout(this);
            fruitscolumn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            fruitscolumn.setOrientation(LinearLayout.HORIZONTAL);
            fruitscolumn.setWeightSum(8);
            for (int i = 0; i < 4; i++) {
                String fruitsname = (String) iter.next();

                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f
                );
                params.setMargins(0, 0, 8, 0);
                imageView.setLayoutParams(params);
                imageView.setTag(fruitsname);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        manipulatingcart(view);
                    }
                });
                if (fruitsname.equalsIgnoreCase("apple")) {
                    imageView.setImageResource(R.drawable.apple);

                } else if (fruitsname.equalsIgnoreCase("orange")) {
                    imageView.setImageResource(R.drawable.orange);

                } else if (fruitsname.equalsIgnoreCase("chikoo")) {
                    imageView.setImageResource(R.drawable.chikoo);

                } else if (fruitsname.equalsIgnoreCase("coconut")) {
                    imageView.setImageResource(R.drawable.coconut);

                } else if (fruitsname.equalsIgnoreCase("banana")) {
                    imageView.setImageResource(R.drawable.banana);


                } else if (fruitsname.equalsIgnoreCase("mango_neelam")) {
                    imageView.setImageResource(R.drawable.mango_neelam);

                } else if (fruitsname.equalsIgnoreCase("mangohafus")) {
                    imageView.setImageResource(R.drawable.mangohafus);

                } else if (fruitsname.equalsIgnoreCase("mangolangra")) {
                    imageView.setImageResource(R.drawable.mangolangra);

                } else if (fruitsname.equalsIgnoreCase("mangototapuri")) {
                    imageView.setImageResource(R.drawable.mangototapuri);

                } else if (fruitsname.equalsIgnoreCase("mangoda")) {
                    imageView.setImageResource(R.drawable.mangoda);

                } else if (fruitsname.equalsIgnoreCase("mangopa")) {
                    imageView.setImageResource(R.drawable.mangopa);

                } else if (fruitsname.equalsIgnoreCase("mangoke")) {
                    imageView.setImageResource(R.drawable.mangoke);

                } else if (fruitsname.equalsIgnoreCase("proe")) {
                    imageView.setImageResource(R.drawable.proe);



                } else if (fruitsname.equalsIgnoreCase("watermelon")) {
                    imageView.setImageResource(R.drawable.watermelon);

                } else if (fruitsname.equalsIgnoreCase("muskmelon")) {
                    imageView.setImageResource(R.drawable.muskmelon);

                } else if (fruitsname.equalsIgnoreCase("kiwi")) {
                    imageView.setImageResource(R.drawable.kiwi);

                } else if (fruitsname.equalsIgnoreCase("litchi")) {
                    imageView.setImageResource(R.drawable.litchi);

                } else if (fruitsname.equalsIgnoreCase("mangoba")) {
                    imageView.setImageResource(R.drawable.mangoba);

                } else if (fruitsname.equalsIgnoreCase("peer")) {
                    imageView.setImageResource(R.drawable.peer);

                } else if (fruitsname.equalsIgnoreCase("plum")) {
                    imageView.setImageResource(R.drawable.plum);

                } else if (fruitsname.equalsIgnoreCase("dates")) {
                    imageView.setImageResource(R.drawable.dates);

                }

                fruitscolumn.addView(imageView);
                if (iter.hasNext() == false) {
                    break;
                }
            }
            fruitscontainer.addView(fruitscolumn);
        }
        Firebase.setAndroidContext(this);
        mfirbase=new Firebase("https://onsitedeliverycustomerside.firebaseio.com/");

    }
    public void order(View v)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm a");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
        String localTime = date.format(currentLocalTime);
        RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(this).getRequestQueue();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://www.pehchanindia.in/user/firebasetest.php?mobile=");
        localStringBuilder.append(getSharedPreferences("userdetails",MODE_PRIVATE).getString("mobile","test"));
        localStringBuilder.append("&date=");
        localStringBuilder.append(df.format(Calendar.getInstance().getTime()));
        localStringBuilder.append("&time=");
        localStringBuilder.append(localTime);
        localStringBuilder.append("&locality=");
        localStringBuilder.append( getSharedPreferences("userdetails",MODE_PRIVATE).getString("locality","test"));
        localStringBuilder.append("&products=");
        localStringBuilder.append("apple");
        String str = localStringBuilder.toString();
        JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject paramAnonymousJSONObject) {
                Toast.makeText(order.this, "Sucess", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(order.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        localRequestQueue.add(jsonobject);
        finish();
    }
    public void manipulatingcart(View v)
    {
        LinearLayout fruits=(LinearLayout)findViewById(R.id.fruits);
        ImageView temp=(ImageView) v;
        String tag=String.valueOf(temp.getTag());
        for(String t:cart)
        {
            if(t.equalsIgnoreCase(tag))
            {
                cart.remove(t);
                temp.setBackgroundColor(Color.TRANSPARENT);
                return;
            }
        }
        cart.add(tag);
        temp.setBackgroundResource(R.color.colorPrimary);
        return;
    }


}
