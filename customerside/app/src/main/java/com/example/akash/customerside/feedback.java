package com.example.akash.customerside;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class feedback extends AppCompatActivity {
    RadioGroup emoticonresponse;
    EditText textualfeedback;
    Button submitfeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
    }
    private void init()
    {
        emoticonresponse=(RadioGroup)findViewById(R.id.emoticonvalue);
        textualfeedback=(EditText)findViewById(R.id.textualfeedback);
        submitfeedback=(Button)findViewById(R.id.submitfeedback);
        submitfeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int a=emoticonresponse.getCheckedRadioButtonId();
                        int emoticonvalue;
                        if(a==R.id.notsatisfied) {
                            emoticonvalue=3;
                        }
                        else if(a==R.id.satisfied) {
                            emoticonvalue=2;
                        }
                        else if(a==R.id.lovedit) {
                            emoticonvalue=1;
                        }
                        else {
                            emoticonvalue=0;
                        }
                        String textualfeedbacktext=((EditText)findViewById(R.id.textualfeedback)).getText().toString();
                        RequestQueue localRequestQueue = SingletonVolleyclass.getInstance(feedback.this).getRequestQueue();
                        StringBuilder localStringBuilder = new StringBuilder();
                        localStringBuilder.append("http://www.pehchanindia.in/user/insertfeedbackfromandroid.php?mobile=");
                        localStringBuilder.append(getSharedPreferences("userdetails", 0).getString("mobile", null));
                        localStringBuilder.append("&emoticonvalue=");
                        localStringBuilder.append(emoticonvalue);
                        localStringBuilder.append("&textualffedback=");
                        localStringBuilder.append(textualfeedbacktext);
                        String str = localStringBuilder.toString();
                        JsonObjectRequest jsonobject=new JsonObjectRequest(Request.Method.GET, str, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject paramAnonymousJSONObject) {
                                try
                                {
                                    if (paramAnonymousJSONObject.getString("success").equalsIgnoreCase("true"))
                                    {
                                        Toast.makeText(feedback.this, "Thanksfor the feedback", Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }
                                    else
                                    {
                                        Toast.makeText(feedback.this, "Error with server", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(feedback.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                        });
                        localRequestQueue.add(jsonobject);
                    }
                });
    }
}
