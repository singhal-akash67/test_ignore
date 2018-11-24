
package com.example.sweekar.sample;
/**
 * Created by akash on 15/4/18.
 */

public class Transaction {
    public String time;
    public int transaction_id;
    public int customer_id;
    public float totalprice;
    public String hawker_id;

    public Transaction(String time,int customer_id,float totalprice,String hawker_id)
    {
        this.customer_id=customer_id;
        this.totalprice=totalprice;
        this.time=time;
        this.hawker_id=hawker_id;
    }
    public Transaction()
    {

    }

}
