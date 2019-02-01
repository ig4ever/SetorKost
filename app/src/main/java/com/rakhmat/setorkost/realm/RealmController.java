package com.rakhmat.setorkost.realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.rakhmat.setorkost.model.Kamar;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

    //clear all objects from Kamar.class
    public void clearAll() {
        realm.beginTransaction();
        realm.delete(Kamar.class);
        realm.commitTransaction();
    }

    //find all objects in the Kamar.class
    public RealmResults<Kamar> getKamar() {
        return realm.where(Kamar.class).findAll();
    }

    //query a single item with the given id
    public Kamar getKamar(String id) {
        return realm.where(Kamar.class).equalTo("id", id).findFirst();
    }

    //query example
    public RealmResults<Kamar> queryedKamar(String tipeKamar, String nomorKamar) {
        return realm.where(Kamar.class)
                .contains("tipeKamar", tipeKamar)
                .or()
                .contains("nomorKamar", nomorKamar)
                .findAll();

    }
}
