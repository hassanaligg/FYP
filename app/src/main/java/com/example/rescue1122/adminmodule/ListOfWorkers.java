package com.example.rescue1122.adminmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.rescue1122.Alert;
import com.example.rescue1122.R;
import com.example.rescue1122.adapters.ListAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ListOfWorkers extends AppCompatActivity {
    private RecyclerView recyclerView;
    ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_workers);
        getSupportActionBar().setTitle("List Of Workers");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));

        recyclerView=findViewById(R.id.listrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Alert> options =
                new FirebaseRecyclerOptions.Builder<Alert>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Worker").limitToLast(50), Alert.class)
                        .build();

        adapter=new ListAdapter(options,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
