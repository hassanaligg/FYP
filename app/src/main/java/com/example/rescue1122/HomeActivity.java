package com.example.rescue1122;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.rescue1122.R;
import com.example.rescue1122.adminmodule.AdminLogin;
import com.example.rescue1122.usermodule.UserLogin;
import com.example.rescue1122.workermodule.WorkerLogin;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void admin(View view) {
        startActivity(new Intent(getApplicationContext(), AdminLogin.class));

    }
    public void worker(View view) {
        startActivity(new Intent(getApplicationContext(), WorkerLogin.class));

    }
    public void user(View view) {
        startActivity(new Intent(getApplicationContext(), UserLogin.class));

    }
}
