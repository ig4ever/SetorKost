package com.rakhmat.setorkost.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Setoran extends RealmObject {
    @PrimaryKey
    private int id;
    private String periode, nama, umur, pekerjaan, nomorKamar, tanggalMasuk, status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}