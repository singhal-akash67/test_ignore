package com.example.akash.onsitedeliverycustomerside;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Firebase mfirbase;
    LatLng userlocation=null;
    LatLng templocationtobepointed=null;
    ArrayList<Marker> currentmarkers=new ArrayList<Marker>();


                @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
                            // Logic to handle location object
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        Firebase.setAndroidContext(this);
        mfirbase=new Firebase("https://onsitedeliverycustomerside.firebaseio.com/VendorCoordinates");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Iterable<DataSnapshot> user = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iter = user.iterator();
                for(Marker currentmarker:currentmarkers)
                {
                    currentmarker.remove();
                }
                while(iter.hasNext()){
                    DataSnapshot dataSnapshot1=iter.next();
                    VendorCoordinates temp=dataSnapshot1.getValue(VendorCoordinates.class);
                    LatLng currentlocation = new LatLng(temp.latitude, temp.longitude);
                    if(templocationtobepointed==null)
                    {
                        templocationtobepointed=currentlocation;
                        Toast.makeText(MapsActivity.this, "tyyu", Toast.LENGTH_SHORT).show();

                    }
                    Marker currentmarker=mMap.addMarker(new MarkerOptions().position(currentlocation).title(dataSnapshot1.getKey()));
                    currentmarkers.add(currentmarker);
                }
                if(userlocation!=null) {
                    mMap.addMarker(new MarkerOptions().position(userlocation).title("userlocation"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(userlocation));
                }
                else if(templocationtobepointed!=null) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(templocationtobepointed));
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        };
        mfirbase.addValueEventListener(postListener);
        mMap.setMinZoomPreference(13);
    }
    public void order(View v)
    {
        startActivity(new Intent(this,order.class));
    }
    public void recentremaps(View v)
    {
        if(userlocation!=null) {
            mMap.addMarker(new MarkerOptions().position(userlocation).title("userlocation"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(userlocation));
        }
        else if(templocationtobepointed!=null)
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(templocationtobepointed));
        }
    }
}
