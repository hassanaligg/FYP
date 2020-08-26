package com.example.rescue1122.adminmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rescue1122.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmergencyTips extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_tips);
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("Tips");
    }

    public void tips(View view){
        EditText editText=findViewById(R.id.tips);
        String tip=editText.getText().toString();
        myRef.setValue(tip);
        Toast.makeText(this, "Succesfully Sent", Toast.LENGTH_SHORT).show();
        editText.getText().clear();
    }

}