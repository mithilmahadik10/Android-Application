package com.example.wms3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class slogin extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mRegister;
    TextView mLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String supplierID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mRegister = findViewById(R.id.button);
        mLogin = findViewById(R.id.View3);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), sregister.class);
                startActivity(intent);
                finish();
            }
        });
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

            mRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = mEmail.getText().toString();
                    String pass = mPassword.getText().toString();
                    if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (!pass.isEmpty()) {
                            fAuth.signInWithEmailAndPassword(email, pass)
                                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                        @Override
                                        public void onSuccess(AuthResult authResult) {
                                            Toast.makeText(slogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(slogin.this, supplier.class));
                                            finish();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(slogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            mPassword.setError("Empty fields are not allowed");
                        }
                    } else if (email.isEmpty()) {
                        mPassword.setError("Empty fields are not allowed");
                    } else {
                        mEmail.setError("Please enter correct email");
                    }
                }
            });
            if (fAuth.getCurrentUser() != null){
                startActivity(new Intent(getApplicationContext(), supplier.class));
                finish();
        }
    }}