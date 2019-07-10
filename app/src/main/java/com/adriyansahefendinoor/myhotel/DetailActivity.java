package com.adriyansahefendinoor.myhotel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adriyansahefendinoor.myhotel.paket.PaketDAO;
import com.adriyansahefendinoor.myhotel.paket.PaketDAOImp;
import com.adriyansahefendinoor.myhotel.paket.Pemesanan;
import com.adriyansahefendinoor.myhotel.paket.profil;

public class DetailActivity extends AppCompatActivity {

    private TextView tv_nama, tv_harga, tv_lama, tv_kamar;
    private Button btn_simpan, btn_batal;
    private PaketDAO paketDAO;
    private boolean TAG;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();

    }

    private void init(){
        i = getIntent();
        tv_nama = (TextView) findViewById(R.id.tv_nama);
        tv_harga = (TextView) findViewById(R.id.tv_harga);
        tv_lama = (TextView) findViewById(R.id.tv_lama);
        tv_kamar = (TextView) findViewById(R.id.tv_kamar);
        btn_simpan = (Button) findViewById(R.id.btn_simpan);
        btn_batal = (Button) findViewById(R.id.btn_batal);

        tv_nama.setText(i.getStringExtra("nama"));
        tv_harga.setText(i.getStringExtra("harga"));
        tv_lama.setText(i.getStringExtra("lama"));
        tv_kamar.setText(i.getStringExtra("kamar"));
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, Homescreen.class);
                DetailActivity.this.startActivity(intent);
            }
        });
    }

    private void create(){
        paketDAO = new PaketDAOImp(DetailActivity.this);

        Pemesanan pemesanan = new Pemesanan();
        pemesanan.setNama(tv_nama.getText().toString());
        pemesanan.setHarga(tv_harga.getText().toString());


        int saldo = Integer.parseInt(i.getStringExtra("saldo"));
        int harga = Integer.parseInt(i.getStringExtra("harga"));
        int hasil = saldo - harga;
        if (saldo<harga){

                Toast.makeText(this, "Saldo Anda Kurang", Toast.LENGTH_SHORT).show();

        }else if (saldo>=harga){
            if(paketDAO.createPemesanan(pemesanan)){
                pemesanan.setNama(tv_nama.getText().toString());
                pemesanan.setHarga(tv_harga.getText().toString());
                pemesanan.setNik(String.valueOf(Integer.parseInt(i.getStringExtra("id_profil"))));
                profil profil = new profil();
                if (paketDAO.updateSaldo(profil)){

                    profil.setSaldo(Integer.toString(hasil));
                    profil.setId(Integer.parseInt(i.getStringExtra("id_profil")));
                    Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailActivity.this, Homescreen.class));
                    finish();
                }
            }
        }
    }
}
