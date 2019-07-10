package com.adriyansahefendinoor.myhotel.paket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LAB-DKV on 10/07/2019.
 */

public class PaketDAOImp extends SQLiteOpenHelper implements PaketDAO {

    public PaketDAOImp(Context ctx){
        super(ctx, "db_hotel", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_paket(id INTEGER PRIMARY KEY, nama TEXT, harga TEXT, lama TEXT, kamar TEXT)");

        db.execSQL("INSERT INTO tbl_paket VALUES (1, 'Paket Pribadi', '500000', '3 Hari', 'Standar')");
        db.execSQL("INSERT INTO tbl_paket VALUES (2, 'Paket Bisnis', '2500000', '5 Hari', 'Superior')");
        db.execSQL("INSERT INTO tbl_paket VALUES (3, 'Paket Liburan', '3500000', '7 Hari', 'VIP' )");
        db.execSQL("INSERT INTO tbl_paket VALUES (4, 'Paket Honeymoon', '15000000', '30 Hari', 'Deluxe')");

        db.execSQL("CREATE TABLE tbl_profil(id INTEGER PRIMARY KEY, nama TEXT, alamat TEXT, username TEXT, password TEXT, saldo TEXT)");
        db.execSQL("CREATE TABLE tbl_pemesanan(id_pemesanan INTEGER PRIMARY KEY, nama TEXT, tanggal DATETIME DEFAULT CURRENT_TIMESTAMP, harga, id INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE tbl_paket");
        db.execSQL("DROP TABLE tbl_profil");
    }

    @Override
    public Cursor readPaket() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_paket",null);
    }

    @Override
    public boolean create(Paket paket) {
        return false;
    }

    @Override
    public boolean update(Paket paket) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Cursor readPemesanan() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_pemesanan",null);
    }

    @Override
    public boolean createPemesanan(Pemesanan pemesanan) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_pemesanan VALUES ('"+pemesanan.getId()+"','"+pemesanan.getNama()+"','"+pemesanan.getTanggal()+"','"+pemesanan.getHarga()+"','"+pemesanan.getNik()+"')");

        return true;
    }

    @Override
    public boolean updatePemesanan(Pemesanan pemesanan) {
        return false;
    }

    @Override
    public Cursor readProfil() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM tbl_profil",null);
    }

    @Override
    public boolean createProfil(profil profil) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbl_profil VALUES ('"+profil.getId()+"','"+profil.getNama()+"','"+profil.getAlamat()+"','"+profil.getUsername()+"','"+profil.getPassword()+"','"+profil.getSaldo()+"')");
        return true;
    }

    @Override
    public boolean updateProfil(profil profil) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tbl_profil SET nama='"+profil.getNama()+"', alamat='"+profil.getAlamat()+"', username='"+profil.getUsername()+"', password='"+profil.getPassword()+"', saldo='"+profil.getSaldo()+"' WHERE id='"+profil.getId()+"'");
        return true;
    }

    @Override
    public boolean updateSaldo(profil profil) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE tbl_profil SET saldo='"+profil.getSaldo()+"' WHERE id='"+profil.getId()+"'");
        return true;
    }
}
