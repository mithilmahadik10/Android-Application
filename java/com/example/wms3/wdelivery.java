package com.example.wms3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class wdelivery extends AppCompatActivity {

    EditText Name, Email, Mobile;
    MaterialButton dwbtn;
    ImageButton btn1;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wdelivery);

        //Intent intent = getIntent();
        // String Address = intent.getStringExtra("Address");
        //Toast.makeText(this, "Address:" +Address, Toast.LENGTH_SHORT).show();

        db = FirebaseFirestore.getInstance();
        Name = findViewById(R.id.dwname);
        Email = findViewById(R.id.dwemail);
        Mobile = findViewById(R.id.dwmobile);
        dwbtn = findViewById(R.id.dwbtn);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rwaste.class);
                startActivity(intent);
                finish();
            }
        });
        dwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String mobile = Mobile.getText().toString();
                Map<String,Object> wdelivery = new HashMap<>();
                wdelivery.put("Name", name);
                wdelivery.put("Email",email);
                wdelivery.put("Mobile",mobile);

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile)) {
                    // Handle empty fields appropriately
                    Toast.makeText(wdelivery.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("wdelivery")
                        .add(wdelivery)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(wdelivery.this,"request sent",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(wdelivery.this,"unsuccessful in sending",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(getApplicationContext(), receiver.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
