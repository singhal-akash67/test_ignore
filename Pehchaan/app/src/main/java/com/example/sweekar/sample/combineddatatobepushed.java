package com.example.sweekar.sample;

import android.widget.Spinner;

public class combineddatatobepushed {
    public String hawkerid;
    public String hawkername;
    public String area;
    public String phonenumber;
    public String time;
    public int transaction_id;
    public int customer_id;
    public float totalprice;
    String productname;
    public combineddatatobepushed(String hawkerid, String hawkername,String name, String phonenumber,String time,int transaction_id,int customer_id,float totalprice,String productname)
    {
        this.hawkerid=hawkerid;
        this.hawkername=hawkername;
        this.area=area;
        this.phonenumber=phonenumber;
        this.time=time;
        this.transaction_id=transaction_id;
        this.customer_id=customer_id;
        this.totalprice=totalprice;
        this.productname=productname;
    }
    public combineddatatobepushed()
    {}
}
