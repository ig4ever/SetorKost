package com.rakhmat.setorkost.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("setorkost.db")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
