package com.example.akash.onsitedeliverycustomerside;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonVolleyclass
{
    private static Context mCtx;
    private static SingletonVolleyclass mInstance;
    private RequestQueue mRequestQueue;

    private SingletonVolleyclass(Context paramContext)
    {
        mCtx = paramContext;
        this.mRequestQueue = getRequestQueue();
    }

    public static SingletonVolleyclass getInstance(Context paramContext)
    {
        try
        {
            if (mInstance == null) {
                mInstance = new SingletonVolleyclass(paramContext);
            }
            SingletonVolleyclass localSingletonVolleyclass = mInstance;
            return localSingletonVolleyclass;
        }
        finally {}
    }

    public <T> void addToRequestQueue(Request<T> paramRequest)
    {
        getRequestQueue().add(paramRequest);
    }

    public RequestQueue getRequestQueue()
    {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return this.mRequestQueue;
    }
}