package com.example.sweekar.sample;

/**
 * Created by akash on 7/5/18.
 */

public class Transactionbackup {
    public String time;
    public int transaction_id;
    public int customer_id;
    public float totalprice;
    public String hawker_id;

    public Transactionbackup(String time,int customer_id,float totalprice,String hawker_id)
    {
        this.customer_id=customer_id;
        this.totalprice=totalprice;
        this.time=time;
        this.hawker_id=hawker_id;
    }
    public Transactionbackup()
    {

    }
}
