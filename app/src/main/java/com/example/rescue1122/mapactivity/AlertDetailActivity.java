package com.example.rescue1122.mapactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rescue1122.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlertDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Double lat,longi;
    Button button;
    String id;
    String From;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_detail);
        From=getIntent().getStringExtra("From");
        id=getIntent().getStringExtra("id");
        button=findViewById(R.id.approve);
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("alerts").child(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(From.equals("Admin")){
                    status="Approved";
                }
                else{
                    status="Accepted";
                }
                databaseReference.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        lat=Double.parseDouble(getIntent().getStringExtra("Latitude"));
        longi=Double.parseDouble(getIntent().getStringExtra("Longitude"));
        if(From.equals("Admin")){
            button.setText("Approve");
        }
        else{
            button.setText("Accept");
        }

        Log.d("ABC",lat.toString());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera
        LatLng myloc = new LatLng(lat, longi);
        mMap.addMarker(new MarkerOptions().position(myloc).title("Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myloc,15));
    }
}
