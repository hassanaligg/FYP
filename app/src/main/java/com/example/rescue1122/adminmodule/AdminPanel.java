package com.example.rescue1122.adminmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rescue1122.R;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
    }

    public void Create(View view){
        Intent intent=new Intent(getApplicationContext(), CreateWorker.class);
        startActivity(intent);
    }
    public void list(View view){
        Intent intent=new Intent(getApplicationContext(), ListOfWorkers.class);
        startActivity(intent);
    }
    public void checkemer(View view){
        Intent intent=new Intent(getApplicationContext(), CheckEmergencyAdmin.class);
        startActivity(intent);

    }
    public void sendtips(View view){
        Intent intent=new Intent(getApplicationContext(), EmergencyTips.class);
        startActivity(intent);
    }
}
