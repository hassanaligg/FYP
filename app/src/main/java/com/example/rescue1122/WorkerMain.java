package com.example.rescue1122;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.rescue1122.adapters.WorkerAlertAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WorkerMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WorkerAlertAdapter alertAdapter;
    public ArrayList<Alert> alertslist;
    public ArrayList temp=new ArrayList();
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);

        getSupportActionBar().setTitle("Emergency Details");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));

        alertslist=new ArrayList<>();
        recyclerView=findViewById(R.id.workerrecycler);

        alertAdapter=new WorkerAlertAdapter(getApplicationContext(),alertslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference().child("alerts");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {

                    Alert alert = snapshot.getValue(Alert.class);

                    if(alert.status.equals("Approved")) {


                        alertslist.add(alert); // add all data into list.

                        // Alert alert = snapshot.getValue(Alert.class);

                        //  alertslist.add(alert);
                        Log.d("xyz", alert.toString());
                        alertAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FirebaseRecyclerOptions<Alert> options =
                new FirebaseRecyclerOptions.Builder<Alert>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("alerts").limitToLast(50), Alert.class)
                        .build();

        recyclerView.setAdapter(alertAdapter);

    }


}
