package com.example.rescue1122.usermodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rescue1122.Alert;
import com.example.rescue1122.R;
import com.example.rescue1122.SingleShotLocationProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserLocationSend extends AppCompatActivity {
    Button button;
    private DatabaseReference mDatabase;
    String Service;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location_send);

        getSupportActionBar().setTitle("Send Your Location");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));


        Service = getIntent().getStringExtra("Service");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        editText = findViewById(R.id.title);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                showRationale();
            } else {
                // do request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 8);
            }
        }

//        GPSTracker gps = new GPSTracker(this);
//        double latitude = gps.getLatitude();
//        double longitude = gps.getLongitude();
        SingleShotLocationProvider.requestSingleUpdate(getApplicationContext(),
                new SingleShotLocationProvider.LocationCallback() {
                    @Override
                    public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        final String lat = String.valueOf(location.latitude);
                        final String lon = String.valueOf(location.longitude);
                        button = findViewById(R.id.sendloc);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (editText.getText().toString().equals("")) {
                                    Toast.makeText(getApplicationContext(), "Title can not be empty", Toast.LENGTH_LONG).show();
                                } else {


                                    String key = mDatabase.child("alerts").push().getKey();
                                    DatabaseReference databaseReference = mDatabase.child("alerts").child(key);
                                    Alert alert = new Alert(lat, lon, "Pending", key, Service, editText.getText().toString());
                                    databaseReference.setValue(alert).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Your location Was Sucessfully Sent", Toast.LENGTH_LONG).show();
                                            button.setVisibility(View.GONE);
                                            finish();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Start your code
            SingleShotLocationProvider.requestSingleUpdate(getApplicationContext(),
                    new SingleShotLocationProvider.LocationCallback() {
                        @Override
                        public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                            final String lat = String.valueOf(location.latitude);
                            final String lon = String.valueOf(location.latitude);
                            Toast.makeText(getApplicationContext(), "Latitude" + lat, Toast.LENGTH_LONG).show();
                            button = findViewById(R.id.sendloc);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (editText.getText().toString().equals("")) {
                                        Toast.makeText(getApplicationContext(), "Title can not be empty", Toast.LENGTH_LONG).show();
                                    } else {


                                        String key = mDatabase.child("alerts").push().getKey();
                                        DatabaseReference databaseReference = mDatabase.child("alerts").child(key);
                                        Alert alert = new Alert(lat, lon, "Pending", key, Service, editText.getText().toString());
                                        databaseReference.setValue(alert).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getApplicationContext(), "Sucessfully inserted", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    });
        } else {
            //Show snackbar
            Toast.makeText(getApplicationContext(), "Error please turn on your location", Toast.LENGTH_LONG).show();
        }
    }
}