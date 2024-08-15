package com.example.wms3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class supplier extends AppCompatActivity {

    private ImageButton wbtn, fbtn, cbtn,btn;
    FirebaseAuth fAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);

        btn = findViewById(R.id.btn);
        fAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                Intent intent = new Intent(supplier.this, slogin.class);
                startActivity(intent);
                finish();
            }
        });
        wbtn = findViewById(R.id.wbtn);

        wbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), swaste.class);
                startActivity(intent);
                finish();
            }
        });

        fbtn = findViewById(R.id.fbtn);

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sfood.class);
                startActivity(intent);
                finish();
            }
        });

        cbtn = findViewById(R.id.cbtn);

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sclothes.class);
                startActivity(intent);
                finish();
            }
        });

    }
}