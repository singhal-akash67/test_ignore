package com.example.akash.onsitedeliverycustomerside;

public class VendorCoordinates {
    float latitude;
    float longitude;
    VendorCoordinates()
    {

    }
    VendorCoordinates(String latitude,String longitude)
    {
        this.latitude=Float.valueOf(latitude);
        this.longitude=Float.valueOf(longitude);
    }
}
