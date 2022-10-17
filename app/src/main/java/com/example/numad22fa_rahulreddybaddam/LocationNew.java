package com.example.numad22fa_rahulreddybaddam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationNew extends AppCompatActivity {

    TextView text_latitude, text_longitude;
    Location currentLocation;
    Location recentLocation;
    List<Location> savedLocations;
    float[] d;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;
    LocationCallback locationCallback;

    Button Reset;

    TextView dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_new);
        d= new float[1];
        text_latitude = findViewById(R.id.tv_lat);
        text_longitude = findViewById(R.id.tv_lon);
        Reset = (Button) findViewById(R.id.Reset);
        dist = findViewById(R.id.distance);
        locationRequest = new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        savedLocations = new ArrayList<Location>();

        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                recentLocation = locationResult.getLastLocation();
                Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                        recentLocation.getLatitude(),recentLocation.getLongitude(),d);
                updateTextView(locationResult.getLastLocation());
            }
        };


        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location.distanceBetween(currentLocation.getLatitude(),currentLocation.getLongitude(),
                        currentLocation.getLatitude(),currentLocation.getLongitude(),d);
                String formattedString = Arrays.toString(d)
                        .replace(",", "")
                        .replace("[", "")
                        .replace("]", "")
                        .trim();
                dist.setText(formattedString);

            }
        });

        updateLocation();

    }



    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);


        updateLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 99) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateLocation();
            } else {
                Toast.makeText(this, "Permission Required", Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }



    private void updateLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (currentLocation == null ) {
                        currentLocation = location;
                    }
                        updateTextView(location);
                        startLocationUpdates();

                    }

            }
            );
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
        }
    }

    private void updateTextView(Location location) {
        text_latitude.setText(String.valueOf(location.getLatitude()));
        text_longitude.setText(String.valueOf(location.getLongitude()));
        String formattedString = Arrays.toString(d)
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
        dist.setText(formattedString);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("EXIT?");
        build.setMessage("Do you wanna exit")
                .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = build.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        int width = (int) (getResources().getDisplayMetrics().widthPixels*0.7);
        int height = (int) (getResources().getDisplayMetrics().heightPixels*0.32);
        alertDialog.getWindow().setLayout(width,height);

    }


}