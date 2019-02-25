package com.rakhmat.setorkost.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Penghuni extends RealmObject {
    @PrimaryKey
    private int id;
    private String tipeKamar, hargaKamar, nama, umur, pekerjaan, nomorKamar, tanggalMasuk;

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public String getHargaKamar() {
        return hargaKamar;
    }

    public void setHargaKamar(String hargaKamar) {
        this.hargaKamar = hargaKamar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }
}
