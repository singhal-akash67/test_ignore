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
    public String typeOfVendor;
    public Hawker(String hawkerid,String hawkername,String area,String phonenumber,String typeOfVendor)
    {
        this.hawkerid=hawkerid;
        this.hawkername=hawkername;
        this.area=area;
        this.phonenumber=phonenumber;
        this.typeOfVendor=typeOfVendor;

    }
    public Hawker(String hawkerid,String hawkername,String area,String phonenumber,int isverified,String gender,String experiece,int totalexpectedcustomers, int repeatexpectedcustomers,String type_of_vendor)
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
        this.typeOfVendor=type_of_vendor;
    }

    public Hawker()
    {

    }


}
