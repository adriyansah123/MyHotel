package com.adriyansahefendinoor.myhotel;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.adriyansahefendinoor.myhotel.paket.PaketDAO;
import com.adriyansahefendinoor.myhotel.paket.PaketDAOImp;

import java.util.ArrayList;
import java.util.HashMap;

public class booking extends AppCompatActivity {

    private ListView listview;
    private PaketDAO paketDAO;
    private ArrayList<HashMap<String,String>> listPaket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        paketDAO = new PaketDAOImp(this);
        init();
        readPaket();
    }

    private void init(){
        listview = (ListView) findViewById(R.id.listview);
    }

    private void readPaket(){
        Cursor c = paketDAO.readPaket();
        listPaket = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id", c.getString(0) );
                map.put("nama", c.getString(1) );
                map.put("harga", c.getString(2) );
                map.put("lama", c.getString(3) );
                map.put("kamar", c.getString(4) );
                listPaket.add(map);
            }while (c.moveToNext());
        }
        SimpleAdapter adapter = new SimpleAdapter(
                booking.this,
                listPaket,
                R.layout.items,
                new String[]{"nama", "harga", "lama", "kamar"},
                new int[]{R.id.nama_paket,R.id.harga_paket,R.id.lama_paket,R.id.kamar}
        );
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int item, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(booking.this);
                builder.setTitle("Aksi");
                String[] aksi = {"Pilih","Batal"};
                builder.setItems(aksi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
//                                TODO : UPDATE
                                Intent intent = new Intent(booking.this,DetailActivity.class);
                                intent.putExtra("id",listPaket.get(item).get("id"));
                                intent.putExtra("nama",listPaket.get(item).get("nama"));
                                intent.putExtra("harga",listPaket.get(item).get("harga"));
                                intent.putExtra("lama",listPaket.get(item).get("lama"));
                                intent.putExtra("kamar",listPaket.get(item).get("kamar"));
                                intent.putExtra("id_profil",getIntent().getStringExtra("id"));
                                intent.putExtra("saldo",getIntent().getStringExtra("saldo"));
                                startActivity(intent);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });
    }
}
