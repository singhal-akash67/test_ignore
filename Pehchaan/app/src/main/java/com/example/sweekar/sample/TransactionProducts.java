package com.example.sweekar.sample;
/**
 * Created by akash on 15/4/18.
 */

public class TransactionProducts {
    int transactionid;
    String hawkerid;
    String productname;
    TransactionProducts()
    {

    }
    TransactionProducts(int transactionid,String hawkerid,Product temp)
    {
        this.transactionid=transactionid;
        this.productname=temp.name;
        this.hawkerid=hawkerid;

    }
}
