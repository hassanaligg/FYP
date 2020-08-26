package com.example.rescue1122.adminmodule;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rescue1122.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class CreateWorker extends AppCompatActivity {
    CircularProgressButton registerbtn;
    EditText fullname;
    EditText username;
    EditText Cnic;
    EditText password;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_worker);
        registerbtn=findViewById(R.id.register);
        fullname=findViewById(R.id.name);
        username=findViewById(R.id.username);
        Cnic=findViewById(R.id.cnic);
        password=findViewById(R.id.pas);
        database=FirebaseDatabase.getInstance();
    }

    public void Register(View view){
        final String name=fullname.getText().toString();
        final String user=username.getText().toString();
        final String id=Cnic.getText().toString();
        final String pass=password.getText().toString();

        if(name.isEmpty()){
            fullname.setError("Enter Name First");
            fullname.requestFocus();
        }
        if(user.isEmpty()){
            username.setError("Enter Username First");
            username.requestFocus();
        }
        if(id.isEmpty()){
            Cnic.setError("Enter Cnic First");
            Cnic.requestFocus();
        }
        if(pass.isEmpty()){
            password.setError("Enter Password First");
            password.requestFocus();
        }
        if(name.isEmpty() && user.isEmpty() && id.isEmpty() && pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_LONG).show();
            fullname.requestFocus();
        }
        if(!(name.isEmpty() && user.isEmpty() && id.isEmpty() && pass.isEmpty())){
            myRef = database.getReference("Worker");
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userNameRef = rootRef.child("Worker");
            Query queries=userNameRef.orderByChild("UserName").equalTo(user.toLowerCase());
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()) {
                        username.setError("Username Already Exist");
                        username.requestFocus();
                    }
                    else{
                        String child=username.getText().toString();
                        myRef=database.getReference("Worker").child(child);
                        myRef.child("FullName").setValue(name);
                        myRef.child("UserName").setValue(user);
                        myRef.child("Cnic").setValue(id);
                        myRef.child("Password").setValue(pass);
                        fullname.getText().clear();
                        username.getText().clear();
                        Cnic.getText().clear();
                        password.getText().clear();
                        Toast.makeText(getApplicationContext(),"Successfully Created Worker",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            queries.addListenerForSingleValueEvent(eventListener);

        }

    }
}
