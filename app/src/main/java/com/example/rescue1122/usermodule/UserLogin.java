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
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class UserLogin extends AppCompatActivity {

    EditText emailid, password;
    FirebaseAuth mFirebase;
    CircularProgressButton btn;
    private FirebaseAuth.AuthStateListener mAuthstatelistner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        btn = (CircularProgressButton) findViewById(R.id.btn_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlogin();
            }
        });

        mFirebase = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.mail);
        password = findViewById(R.id.pass);

        mAuthstatelistner = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseuser = mFirebase.getCurrentUser();
                if (mFirebaseuser != null) {
                    Toast.makeText(UserLogin.this, "You are loged in", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), UserMain.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(UserLogin.this, "Please login", Toast.LENGTH_LONG).show();
                }
            }
        };
    }
    public void newlogin() {
        btn.startAnimation();
        String email = emailid.getText().toString();
        String pwd = password.getText().toString();
        if (email.isEmpty()) {
            btn.revertAnimation();
            emailid.setError("Please enter email id");
            emailid.requestFocus();
        } else if (pwd.isEmpty()) {
            btn.revertAnimation();
            password.setError("Please enter Password");
            password.requestFocus();
        } else if (email.isEmpty() && pwd.isEmpty()) {
            btn.revertAnimation();
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
            emailid.requestFocus();
        } else if (!((email.isEmpty() && pwd.isEmpty()))) {
            mFirebase.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        btn.revertAnimation();
                        Toast.makeText(getApplicationContext(), "Error, please login again", Toast.LENGTH_LONG).show();
                    } else {
                        btn.revertAnimation();
                        Intent inttohome = new Intent(UserLogin.this, UserMain.class);
                        startActivity(inttohome);
                        finish();
                    }
                }
            });
        } else {
            btn.revertAnimation();
            Toast.makeText(UserLogin.this, "Error", Toast.LENGTH_LONG).show();

        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        mFirebase.addAuthStateListener(mAuthstatelistner);
    }
    public void Signup(View view) {
        startActivity(new Intent(getApplicationContext(), UserSignup.class));

    }

}