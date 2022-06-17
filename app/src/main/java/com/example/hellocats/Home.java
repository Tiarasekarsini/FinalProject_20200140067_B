package com.example.hellocats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
    ImageView MyCats, Jadwal,Diagnosis,Riwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyCats = findViewById(R.id.mycats);
        MyCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, MyCats_Utama.class);
                startActivity(i);
            }
        });

        Jadwal = findViewById(R.id.jadwal);
        Jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Jadwal_Utama.class);
                startActivity(i);
            }
        });
        Diagnosis = findViewById(R.id.diagnosis);
        Diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Diagnosa_Utama.class);
                startActivity(i);
            }
        });
        Riwayat = findViewById(R.id.riwayat);
        Riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Riwayat_Diagnosa.class);
                startActivity(i);
            }
        });
        }
    }


