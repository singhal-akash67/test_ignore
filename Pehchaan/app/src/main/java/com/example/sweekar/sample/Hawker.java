package com.example.sweekar.sample;

/**
 * Created by akash on 16/4/18.
 */

public class Hawker {
    public String hawkerid;
    public String hawkername;
    public String area;
    public String phonenumber;
    public int isverified;
    public String gender;
    public String experiece;
    public int totalexpectedcustomers;
    public int repeatexpectedcustomers;
    public Hawker(String hawkerid,String hawkername,String area,String phonenumber)
    {
        this.hawkerid=hawkerid;
        this.hawkername=hawkername;
        this.area=area;
        this.phonenumber=phonenumber;

    }
    public Hawker(String hawkerid,String hawkername,String area,String phonenumber,int isverified,String gender,String experiece,int totalexpectedcustomers, int repeatexpectedcustomers)
    {
        this.hawkerid=hawkerid;
        this.hawkername=hawkername;
        this.area=area;
        this.phonenumber=phonenumber;
        this.isverified=1;
        this.gender=gender;
        this.experiece=experiece;
        this.totalexpectedcustomers=totalexpectedcustomers;
        this.repeatexpectedcustomers=repeatexpectedcustomers;
    }

    public Hawker()
    {

    }


}
