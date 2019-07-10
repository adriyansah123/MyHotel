package com.adriyansahefendinoor.myhotel;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.adriyansahefendinoor.myhotel.paket.PaketDAO;
import com.adriyansahefendinoor.myhotel.paket.PaketDAOImp;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPemesanan extends AppCompatActivity {

    private ListView listView;
    private PaketDAO paketDAO;
    private ArrayList<HashMap<String,String>> listPemesanan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pemesanan);

        paketDAO = new PaketDAOImp(this);
        init();
        readPaket();
    }

    private void init(){
        listView = (ListView) findViewById(R.id.listView);
    }

    private void readPaket(){
        Cursor c = paketDAO.readPemesanan();
        listPemesanan = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id", c.getString(0) );
                map.put("nama", c.getString(1) );
                map.put("tanggal", c.getString(2) );
                map.put("harga", c.getString(3) );
                listPemesanan.add(map);
            }while (c.moveToNext());
            SimpleAdapter adapter = new SimpleAdapter(
                    DataPemesanan.this,
                    listPemesanan,
                    R.layout.items2,
                    new String[]{"nama", "tanggal", "harga"},
                    new int[]{R.id.nama_paket,R.id.tanggal_pesan,R.id.harga_paket}
            );

        }
    }
}
