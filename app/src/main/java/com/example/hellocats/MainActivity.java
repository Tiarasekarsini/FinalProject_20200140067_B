package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int loading = 4000;
    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Setelah waktu loading selesai maka akan berpindah ke halaman home (activity_home)
                Intent home = new Intent(MainActivity.this, Home.class);
                startActivity(home);
                finish();
            }
        },loading);
    }
}