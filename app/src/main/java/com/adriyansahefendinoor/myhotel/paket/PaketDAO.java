package com.adriyansahefendinoor.myhotel.paket;

import android.database.Cursor;


/**
 * Created by LAB-DKV on 10/07/2019.
 */

public interface PaketDAO {
    Cursor readPaket();

    boolean create(Paket paket);
    boolean update (Paket paket);

    Cursor readProfil();
    boolean createProfil(profil profil);
    boolean updateProfil (profil profil);
    boolean updateSaldo (profil profil);
    boolean delete (int id);

    Cursor readPemesanan();
    boolean createPemesanan(Pemesanan pemesanan);
    boolean updatePemesanan(Pemesanan pemesanan);
}
