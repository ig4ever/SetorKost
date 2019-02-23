package com.rakhmat.setorkost.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UbahKamarActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_kamar);
        context = this;
        activity = this;

        //Realm Setup
        Realm.init(UbahKamarActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final Spinner spinnerTipeRumah = (Spinner) findViewById(R.id.spinner_tipe_rumah);
        EditText editTextNomorKamar = findViewById(R.id.edit_text_nomor_kamar);
        EditText editTextHargaKamar = findViewById(R.id.edit_text_harga_kamar);

        String[] items = new String[]{
                "Tipe Rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };
        AdapterSpinner.getInstance().adapterSpinner(this, spinnerTipeRumah, R.layout.tipe_rumah_item, getIntent().getStringExtra("TIPE_RUMAH"), items);
        editTextNomorKamar.setText(getIntent().getStringExtra("NOMOR_KAMAR"));
        editTextHargaKamar.setText(getIntent().getStringExtra("HARGA_KAMAR"));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        TextView textView = view.findViewById(R.id.title_custom_action_bar);
        textView.setText("Ubah Kamar");

        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager =
                        (InputMethodManager) context.
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
            }
        });

        Button ubahButton = findViewById(R.id.button_ubah_kamar);
        ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNomorKamar = findViewById(R.id.edit_text_nomor_kamar);
                EditText editTextHargaKamar = findViewById(R.id.edit_text_harga_kamar);

                TextView errorTextTipeRumah = (TextView) spinnerTipeRumah.getSelectedView();

                String tipeRumahText = spinnerTipeRumah.getSelectedItem().toString();
                String nomorKamarText = editTextNomorKamar.getText().toString();
                String hargaKamarText = editTextHargaKamar.getText().toString();
                int idKamar = getIntent().getIntExtra("ID_KAMAR", 0);

                if(tipeRumahText.trim().equalsIgnoreCase("") || tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah")){
                    errorTextTipeRumah.setError("Pilih Tipe Rumah");
                }
                if(nomorKamarText.trim().equalsIgnoreCase("")){
                    editTextNomorKamar.setError("Masukkan Nomor Kamar");
                }
                if(hargaKamarText.trim().equalsIgnoreCase("")){
                    editTextHargaKamar.setError("Masukkan Harga Kamar");
                }

                if ((!tipeRumahText.trim().equalsIgnoreCase("") && !tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah"))
                        && !nomorKamarText.trim().equalsIgnoreCase("")
                        && !hargaKamarText.trim().equalsIgnoreCase("")){

                    realmHelper.updateKamar(idKamar, tipeRumahText, nomorKamarText, hargaKamarText);
                    InputMethodManager inputManager =
                            (InputMethodManager) context.
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(
                            activity.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    finish();

                    //Toast.makeText(activity, nomorKamarText + " " + hargaKamarText, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
