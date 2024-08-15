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

public class sfood extends AppCompatActivity {

    EditText fooditem, quantity, time, address;
    MaterialButton fsbtn;
    ImageButton btn1;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfood);

        db = FirebaseFirestore.getInstance();
        fooditem = findViewById(R.id.food);
        quantity = findViewById(R.id.quantity);
        time = findViewById(R.id.time);
        address = findViewById(R.id.address);
        fsbtn = findViewById(R.id.fsbtn);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), supplier.class);
                startActivity(intent);
                finish();
            }
        });
        fsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Fooditems = fooditem.getText().toString();
                String Quantity = quantity.getText().toString();
                String Time = time.getText().toString();
                String Address = address.getText().toString();
                Map<String,Object> sfood = new HashMap<>();
                sfood.put("Fooditems", Fooditems);
                sfood.put("Quantity",Quantity);
                sfood.put("Time",Time );
                sfood.put("Address",Address);

                if (TextUtils.isEmpty(Fooditems) || TextUtils.isEmpty(Quantity) || TextUtils.isEmpty(Time) || TextUtils.isEmpty(Address)) {
                    // Handle empty fields appropriately
                    Toast.makeText(sfood.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.collection("sfood")
                        .add(sfood)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(sfood.this,"SUBMITTED successful",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(sfood.this,"SUBMIT unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(getApplicationContext(), supplier.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
