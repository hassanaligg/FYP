package com.example.rescue1122.adminmodule;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rescue1122.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class AdminLogin extends AppCompatActivity {

    CircularProgressButton loginbtn;
    EditText username;
    EditText password;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginbtn);

    }

    public void btnclick(View view){
        loginbtn.startAnimation();
        final String name=username.getText().toString();
        final String pass=password.getText().toString();
        if(name.isEmpty()) {
            loginbtn.revertAnimation();
            username.setError("Enter Username first");
            username.requestFocus();
        }
        if(pass.isEmpty()) {
            loginbtn.revertAnimation();
            password.setError("Enter Your Password");
            password.requestFocus();
        }
        if (name.isEmpty() && pass.isEmpty()){
            loginbtn.revertAnimation();
            Toast.makeText(getApplicationContext(), "Enter your Details First", Toast.LENGTH_SHORT).show();
            username.requestFocus();
        }
        if (!(name.isEmpty() && pass.isEmpty())){
             database = FirebaseDatabase.getInstance();
             myRef = database.getReference("Admin");
             myRef.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     String nam = snapshot.child("Username").getValue().toString();
                     String pas = snapshot.child("Pasword").getValue().toString();
                     if (name.equals(nam) && pass.equals(pas)) {
                         loginbtn.revertAnimation();
                         Intent intent = new Intent(getApplicationContext(), AdminPanel.class);
                         startActivity(intent);
                         finish();
                     } else if (!(name.equals(nam))) {
                         loginbtn.revertAnimation();
                         Toast.makeText(getApplicationContext(), "Wrong Username", Toast.LENGTH_SHORT).show();
                     } else if (!(pass.equals(pas))) {
                         loginbtn.revertAnimation();
                         Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                     } else {
                         loginbtn.revertAnimation();
                         Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });


        }

    }

}
