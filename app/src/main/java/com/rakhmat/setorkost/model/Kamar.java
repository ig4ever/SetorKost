package com.rakhmat.setorkost.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kamar extends RealmObject {
    @PrimaryKey
    private int id;

    private String tipeKamar, nomorKamar, hargaKamar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getHargaKamar() {
        return hargaKamar;
    }

    public void setHargaKamar(String hargaKamar) {
        this.hargaKamar = hargaKamar;
    }
}
