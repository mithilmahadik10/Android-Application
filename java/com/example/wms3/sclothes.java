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

public class sclothes extends AppCompatActivity {

    EditText quantity, time, address;
    MaterialButton csbtn;
    ImageButton btn1;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sclothes);

        db = FirebaseFirestore.getInstance();
        quantity = findViewById(R.id.cquantity);
        time = findViewById(R.id.ctime);
        address = findViewById(R.id.caddress);
        csbtn = findViewById(R.id.csbtn);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), supplier.class);
                startActivity(intent);
                finish();
            }
        });
        csbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Quantity = quantity.getText().toString();
                String Time = time.getText().toString();
                String Address = address.getText().toString();
                Map<String,Object> sclothes = new HashMap<>();
                sclothes.put("Quantity",Quantity);
                sclothes.put("Time",Time );
                sclothes.put("Address",Address);
                if (TextUtils.isEmpty(Quantity) || TextUtils.isEmpty(Time) || TextUtils.isEmpty(Address)) {
                    // Handle empty fields appropriately
                    Toast.makeText(sclothes.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.collection("sclothes")
                        .add(sclothes)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(sclothes.this,"SUBMITTED successful",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(sclothes.this,"SUBMIT unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(getApplicationContext(), supplier.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
