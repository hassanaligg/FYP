package com.example.rescue1122.usermodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rescue1122.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

import static android.text.TextUtils.isEmpty;

public class UserSignup extends AppCompatActivity {

    EditText name,email,passw,cnic;
    CircularProgressButton signup;
    FirebaseAuth mFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        mFirebase = FirebaseAuth.getInstance();
        name =findViewById(R.id.name);
        email = findViewById(R.id.email);
        cnic=findViewById(R.id.cnic);
        passw = findViewById(R.id.pas);
        signup = findViewById(R.id.register);
    }
    public void signup(View view){
        String nam=name.getText().toString();
        String cni=cnic.getText().toString();
        String em=email.getText().toString();
        String pwsd=passw.getText().toString();
        if(isEmpty(nam)){
            name.setError("Enter name");
            name.requestFocus();
        }
        if(isEmpty(em)){
            email.setError("Enter emial");
            email.requestFocus();
        }
        if(isEmpty(cni)){
            cnic.setError("Enter Cnic");
            cnic.requestFocus();
        }
        if(isEmpty(pwsd)){
            passw.setError("Enter Password");
            passw.requestFocus();
        }

        if (nam.isEmpty() && em.isEmpty() && cni.isEmpty() && pwsd.isEmpty()){

            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            name.requestFocus();
        }
        else if(!((nam.isEmpty() && em.isEmpty() && cni.isEmpty() && pwsd.isEmpty()))){
            mFirebase.createUserWithEmailAndPassword(em,pwsd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(UserSignup.this,"login failed",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(getApplicationContext(), UserMain.class));
                        finish();
                    }
                }
            });
        }
        else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }


    }


}
