package com.example.wms3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class receiver extends AppCompatActivity {

    private ImageButton rwbtn, rfbtn, rcbtn,btn;
    FirebaseAuth fAuth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        btn = findViewById(R.id.btn);
        fAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                Intent intent = new Intent(receiver.this, rlogin.class);
                startActivity(intent);
                finish();
            }
        });
        rwbtn = findViewById(R.id.rwbtn);

        rwbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rwaste.class);
                startActivity(intent);
                finish();
            }
        });

        rfbtn = findViewById(R.id.rfbtn);

        rfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rfood.class);
                startActivity(intent);
                finish();
            }
        });

        rcbtn = findViewById(R.id.rcbtn);

        rcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), rclothes.class);
                startActivity(intent);
                finish();
            }
        });

    }
}