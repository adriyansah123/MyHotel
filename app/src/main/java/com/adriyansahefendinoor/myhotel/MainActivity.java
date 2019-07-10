package com.adriyansahefendinoor.myhotel;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adriyansahefendinoor.myhotel.paket.PaketDAO;
import com.adriyansahefendinoor.myhotel.paket.PaketDAOImp;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin, btnRegister;
    private PaketDAO paketDAO;
    private ArrayList<HashMap<String, String>> listProfil;
    private int TAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paketDAO = new PaketDAOImp(this);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readProfil();

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void readProfil() {
        String id = "";
        String usernameKey = username.getText().toString();
        String passwordKey = password.getText().toString();

        Cursor c = paketDAO.readProfil();
        listProfil = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                if (usernameKey.equals(c.getString(3)) && passwordKey.equals(c.getString(4))) {
                        id = c.getString(0);
                    TAG = 1;
                }
            } while (c.moveToNext());
        }

        if (TAG == 1) {
            //jika login berhasil
            Toast.makeText(getApplicationContext(), "LOGIN SUKSES", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Homescreen.class);
            intent.putExtra("id",id);
            intent.putExtra("saldo",id);
            startActivity(intent);
            finish();
        } else {
            //jika login gagal
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Username atau Password Anda salah!").setNegativeButton("Retry", null).create().show();
        }

    }
}
