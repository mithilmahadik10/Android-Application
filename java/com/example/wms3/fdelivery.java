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

public class fdelivery extends AppCompatActivity {

    EditText Name, Email, Mobile;
    ImageButton btn1;
    MaterialButton dfbtn;
    FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdelivery);

        //Intent intent = getIntent();
        // String Address = intent.getStringExtra("Address");
        //Toast.makeText(this, "Address:" +Address, Toast.LENGTH_SHORT).show();

        db = FirebaseFirestore.getInstance();
        Name = findViewById(R.id.dfname);
        Email = findViewById(R.id.dfemail);
        Mobile = findViewById(R.id.dfmobile);
        dfbtn = findViewById(R.id.dfbtn);
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rfood.class);
                startActivity(intent);
                finish();
            }
        });
        dfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String mobile = Mobile.getText().toString();
                Map<String,Object> fdelivery = new HashMap<>();
                fdelivery.put("Name", name);
                fdelivery.put("Email",email);
                fdelivery.put("Mobile",mobile);

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile)) {
                    // Handle empty fields appropriately
                    Toast.makeText(fdelivery.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("fdelivery")
                        .add(fdelivery)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(fdelivery.this,"request sent",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(fdelivery.this,"unsuccessful in sending",Toast.LENGTH_SHORT).show();
                            }
                        });
                Intent intent = new Intent(getApplicationContext(), receiver.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
