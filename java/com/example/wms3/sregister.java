package com.example.wms3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sregister extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegister;
    ImageButton btn1;
    TextView mLogin;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String supplierID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sregister);

        mFullName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegister = findViewById(R.id.button);
        mLogin = findViewById(R.id.View3);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), slogin.class);
                startActivity(intent);
                finish();
            }
        });
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String name = mFullName.getText().toString();
                String phone = mPhone.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                    // Handle empty fields appropriately
                    Toast.makeText(sregister.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be at least 6 characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // User created successfully, set up user profile
                            Toast.makeText(sregister.this, "User Created", Toast.LENGTH_SHORT).show();

                            supplierID = fAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = fstore.collection("supplier").document(supplierID);
                            Map<String, Object> supplier = new HashMap<>();
                            supplier.put("fname", name);
                            supplier.put("email", email);
                            supplier.put("phone", phone);

                            documentReference.set(supplier).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "onSuccess: user Profile is created for " + supplierID);

                                }
                            });
                        } else {
                            Toast.makeText(sregister.this, "ERROR !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(getApplicationContext(), slogin.class));
                finish();
            }
        });
    }
}