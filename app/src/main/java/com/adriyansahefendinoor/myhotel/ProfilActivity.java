package com.adriyansahefendinoor.myhotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adriyansahefendinoor.myhotel.paket.PaketDAO;
import com.adriyansahefendinoor.myhotel.paket.PaketDAOImp;
import com.adriyansahefendinoor.myhotel.paket.profil;

public class ProfilActivity extends AppCompatActivity {

    private EditText tv_nama, tv_alamat, Username, Password, tv_saldo, tv_nik;
    private Button btnSimpan, btnKembali;
    private PaketDAO paketDAO;
    private boolean TAG = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
 paketDAO = new PaketDAOImp(this);
        init();

        Intent i = getIntent();
        if (TextUtils.isEmpty(i.getStringExtra("id"))){
            TAG = false;
        }else {
            tv_nik.setText(i.getStringExtra("id"));
            tv_nik.setEnabled(false);
            tv_nama.setText(i.getStringExtra("nama"));
            tv_alamat.setText(i.getStringExtra("alamat"));
            Password.setText(i.getStringExtra("email"));
            Username.setText(i.getStringExtra("telpon"));
            tv_saldo.setText(i.getStringExtra("saldo"));
            TAG = true;
        }
    }

    private void init(){
        tv_nik = (EditText) findViewById(R.id.tv_nik);
        tv_nama = (EditText) findViewById(R.id.tv_nama);
        tv_alamat = (EditText) findViewById(R.id.tv_alamat);
        Password = (EditText) findViewById(R.id.Password);
        Username = (EditText) findViewById(R.id.Username);
        tv_saldo = (EditText) findViewById(R.id.tv_saldo);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, MainActivity.class);
                ProfilActivity.this.startActivity(intent);
            }
        });
    }

    private void create(){
        if(TextUtils.isEmpty(tv_nik.getText().toString()) &&
                TextUtils.isEmpty(tv_nama.getText().toString()) &&
                TextUtils.isEmpty(tv_alamat.getText().toString()) &&
                TextUtils.isEmpty(Password.getText().toString()) &&
                TextUtils.isEmpty(Username.getText().toString()) &&
                TextUtils.isEmpty(tv_saldo.getText().toString())){
            Toast.makeText(this, "Inputan Masih Kosong", Toast.LENGTH_SHORT).show();

        }else{
            paketDAO = new PaketDAOImp(ProfilActivity.this);

            profil profil = new profil();
            profil.setId(Integer.parseInt(tv_nik.getText().toString()));
            profil.setNama(tv_nama.getText().toString());
            profil.setAlamat(tv_alamat.getText().toString());
            profil.setPassword(Password.getText().toString());
            profil.setUsername(Username.getText().toString());
            profil.setSaldo(tv_saldo.getText().toString());
            if (TAG){

                if(paketDAO.updateProfil(profil)){
                    Toast.makeText(this, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfilActivity.this, Homescreen.class));
                    finish();
                }
            }else {
                if(paketDAO.createProfil(profil)){
                    Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ProfilActivity.this, MainActivity.class));
                    finish();
                }
            }
        }



    }
}
