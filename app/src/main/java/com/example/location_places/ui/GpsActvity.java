package com.example.location_places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.location_places.Manifest;
import com.example.location_places.R;

public class GpsActvity extends AppCompatActivity {

    private TextView txtLat;
    private TextView txtLong;

    private Location location;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.fragment_cadastro_local);

        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLong = (TextView) findViewById(R.id.txtLong);

        double Lat = 0.0;
        double Long= 0.0;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.MAPS_RECEIVE)!= PackageManager.PERMISSION_GRANTED){
        }else {
            locationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location != null) {
            Lat = location.getLatitude();
            Long = location.getLongitude();

        }

        txtLat.setText("Lat" + Lat );
        txtLong.setText("Long" + Long);



    }



}
