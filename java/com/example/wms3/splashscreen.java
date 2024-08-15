package com.example.wms3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1700);

    }
}