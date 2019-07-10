package com.adriyansahefendinoor.myhotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homescreen extends AppCompatActivity {

    private Button prohotel, datareser, pemesanan, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        prohotel = findViewById(R.id.prohotel);
        datareser = findViewById(R.id.datareser);
        pemesanan = findViewById(R.id.pemesanan);
        logout = findViewById(R.id.logout);

        prohotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, ProfilActivity.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                Homescreen.this.startActivity(intent);
            }
        });

        datareser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, DataPemesanan.class);
                Homescreen.this.startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, MainActivity.class);
                Homescreen.this.startActivity(intent);
            }
        });

        pemesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homescreen.this, booking.class);
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("saldo",getIntent().getStringExtra("saldo"));
                Homescreen.this.startActivity(intent);
            }
        });
    }
}
