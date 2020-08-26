package com.example.rescue1122.usermodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.rescue1122.HomeActivity;
import com.example.rescue1122.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        getSupportActionBar().setTitle("Rescue Services");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorAccent)));
    }

    public void ambulance(View view) {
        startActivity(new Intent(getApplicationContext(), UserLocationSend.class).putExtra("Service","Ambulance Service"));
    }
    public void Firebrigade(View view) {
        startActivity(new Intent(getApplicationContext(),UserLocationSend.class).putExtra("Service","Fire brigade Service"));
    }
    public void NaturalDisaster(View view) {
        startActivity(new Intent(getApplicationContext(),UserLocationSend.class).putExtra("Service","Natural Disaster"));
    }
    public void Crime(View view) {
        startActivity(new Intent(getApplicationContext(),UserLocationSend.class).putExtra("Service","Reported A Crime"));
    }
    public void check(View view) {
        startActivity(new Intent(getApplicationContext(), CheckTips.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.one){
            FirebaseAuth.getInstance().signOut();
            Intent intomain=new Intent(UserMain.this, HomeActivity.class);
            startActivity(intomain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




}
