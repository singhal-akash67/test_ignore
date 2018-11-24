package com.example.sweekar.sample;

public class Referral {
    public int referralno;
    public String hawkerid;
    public int referredby;
    public int referredto;
    public Referral()
    {

    }
    public Referral(int referralno,String hawkerid,int referredby,int referredto)
    {
        this.referralno=referralno;
        this.hawkerid=hawkerid;
        this.referredby=referredby;
        this.referredto=referredto;
    }
    public Referral(String hawkerid,int referredby,int referredto)
    {
        this.referralno=referralno;
        this.hawkerid=hawkerid;
        this.referredby=referredby;
        this.referredto=referredto;

    }
}
