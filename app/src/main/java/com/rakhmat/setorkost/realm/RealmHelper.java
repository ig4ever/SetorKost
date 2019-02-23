package com.rakhmat.setorkost.realm;

import android.util.Log;

import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.model.Setoran;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //region CRUD Data Kamar
    //menyimpan data kamar
    public void saveKamar(final Kamar kamarModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Kamar.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    kamarModel.setId(nextId);
                    realm.copyToRealm(kamarModel);
                }else {
                    Log.e("info", "execute : Database not exist");
                }
            }
        });
    }

    //mengambil data kamar
    public List<Kamar> getAllKamar(){
        RealmResults<Kamar> results = realm.where(Kamar.class).findAll();
        return results;
    }

    //update data kamar
    public void updateKamar(final Integer id, final String tipeKamar, final String nomorKamar, final String hargaKamar){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Kamar modelKamar = realm.where(Kamar.class)
                        .equalTo("id", id)
                        .findFirst();
                modelKamar.setTipeKamar(tipeKamar);
                modelKamar.setNomorKamar(nomorKamar);
                modelKamar.setHargaKamar(hargaKamar);
            }
        });
    }

    // untuk menghapus data kamar
    public void deleteKamar(Integer id){
        final RealmResults<Kamar> model = realm.where(Kamar.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
    //endregion

    //region CRUD Data Penghuni
    //menyimpan data penghuni
    public void savePenghuni(final Penghuni penghuniModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Penghuni.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    penghuniModel.setId(nextId);
                    realm.copyToRealm(penghuniModel);
                }else {
                    Log.e("info", "execute : Database not exist");
                }
            }
        });
    }

    //mengambil data penghuni
    public List<Penghuni> getAllPenghuni(){
        RealmResults<Penghuni> results = realm.where(Penghuni.class).findAll();
        return results;
    }

    //update data penghuni
    public void updatePenghuni(final Integer id, final String nama, final String umur, final String pekerjaan, final String nomorKamar, final String tanggalMasuk){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Penghuni modelPenghuni = realm.where(Penghuni.class)
                        .equalTo("id", id)
                        .findFirst();
                modelPenghuni.setNama(nama);
                modelPenghuni.setUmur(umur);
                modelPenghuni.setPekerjaan(pekerjaan);
                modelPenghuni.setNomorKamar(nomorKamar);
                modelPenghuni.setTanggalMasuk(tanggalMasuk);
            }
        });
    }

    // untuk menghapus data penghuni
    public void deletePenghuni(Integer id){
        final RealmResults<Penghuni> model = realm.where(Penghuni.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
    //endregion

    //region CRUD Data Setoran
    //menyimpan data setoran
    public void saveSetoran(final Setoran setoranModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Setoran.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    setoranModel.setId(nextId);
                    realm.copyToRealm(setoranModel);
                }else {
                    Log.e("info", "execute : Database not exist");
                }
            }
        });
    }

    //mengambil data setoran
    public List<Setoran> getAllSetoran(){
        RealmResults<Setoran> results = realm.where(Setoran.class).findAll();
        return results;
    }

    //update data setoran
    public void updateSetoran(final Integer id, final String periode, final String nama, final String umur, final String pekerjaan, final String nomorKamar, final String tanggalMasuk, final String status){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Setoran modelSetoran = realm.where(Setoran.class)
                        .equalTo("id", id)
                        .findFirst();
                modelSetoran.setPeriode(periode);
                modelSetoran.setNama(nama);
                modelSetoran.setUmur(umur);
                modelSetoran.setPekerjaan(pekerjaan);
                modelSetoran.setNomorKamar(nomorKamar);
                modelSetoran.setTanggalMasuk(tanggalMasuk);
                modelSetoran.setStatus(status);
            }
        });
    }

    // untuk menghapus data setoran
    public void deleteSetoran(Integer id){
        final RealmResults<Setoran> model = realm.where(Setoran.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
    //endregion
}
