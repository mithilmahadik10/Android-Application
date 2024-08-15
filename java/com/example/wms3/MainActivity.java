package com.example.wms3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton sender;
    private ImageButton receiver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender = (ImageButton) findViewById(R.id.Sender);
        receiver = (ImageButton) findViewById(R.id.Receiver);

        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensender();
            }
        });

        receiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openreceiver();
            }
        });
    }
    public void opensender(){
        Intent intent= new Intent(this,com.example.wms3.sregister.class);
        startActivity(intent);
    }
    public void openreceiver(){
        Intent intent= new Intent(this,com.example.wms3.rregister.class);
        startActivity(intent);
    }
}