package com.example.numad22fa_rahulreddybaddam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class LocationNew extends AppCompatActivity {

    TextView tv_lat, tv_lon;
    Switch sw_locationUpdates;
    Location currentLocation;
    List<Location> savedLocations;
    double distance;
    int n;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    Button StartTracking, Reset;
    TextView trackingStatus, testt,dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_new);
        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);
        sw_locationUpdates = findViewById(R.id.sw_locationsupdates);
        StartTracking = (Button) findViewById(R.id.startTracking);
        Reset = (Button) findViewById(R.id.Reset);
        trackingStatus = findViewById(R.id.trackingStatus);
        testt =  findViewById(R.id.testt);
        dist = findViewById(R.id.distance);
        dist.setText("Start Tracking");
        locationRequest = new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        savedLocations = new ArrayList<Location>();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateUIValues(locationResult.getLastLocation());
            }
        };

        StartTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trackingStatus.setText("Tracking in Progress");
//                testt.setText(String.valueOf(savedLocations.get(0)));
                startLocationUpdates();

            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trackingStatus.setText("Tracking reset");
                updateGPS();
            }
        });


//        sw_locationUpdates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (sw_locationUpdates.isChecked()) {
//                    startLocationUpdates();
//                } else {
//                    stopLocationUpdates();
//                }
//            }
//        });

        updateGPS();

    }

    private void stopLocationUpdates() {
        tv_lon.setText("Not Tracking");
        tv_lat.setText("Not Tracking");
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        updateGPS();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 99:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    updateGPS();
                }
                else{
                    Toast.makeText(this,"Permission Required",Toast.LENGTH_LONG).show();
                    finish();

                }
                break;
        }
    }



    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    updateUIValues(location);

                    currentLocation = location;
                    savedLocations.add(currentLocation);
                    testt.setText(String.valueOf(currentLocation));
//                    n = savedLocations.size();
//
//                    if(savedLocations.get(n-1)!= null) {
//                        distance = calDistance(savedLocations.get(0).getLatitude(), savedLocations.get(0).getLongitude(),
//                                savedLocations.get(n - 1).getLatitude(), savedLocations.get(n - 1).getLongitude());
//                        dist.setText((int) distance);
//                    }

                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
            }
        }
    }

    private void updateUIValues(Location location) {
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));
        currentLocation = location;
        savedLocations.add(currentLocation);
//        int n = savedLocations.size();
//        distance = calDistance(savedLocations.get(0).getLatitude(),savedLocations.get(0).getLongitude(),
//                savedLocations.get(n-1).getLatitude(), savedLocations.get(n-1).getLongitude());
//        dist.setText((int) distance);
    }


    private static final double r2d = 180.0D / 3.141592653589793D;
    private static final double d2r = 3.141592653589793D / 180.0D;
    private static final double d2km = 111189.57696D * r2d;
    public static double calDistance(double lt1, double ln1, double lt2, double ln2) {
        double x = lt1 * d2r;
        double y = lt2 * d2r;
        return Math.acos( Math.sin(x) * Math.sin(y) + Math.cos(x) * Math.cos(y) * Math.cos(d2r * (ln1 - ln2))) * d2km;
    }
}