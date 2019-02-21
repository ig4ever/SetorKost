package com.rakhmat.setorkost.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.fragment.KamarFragment;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.realm.RealmController;
import com.rakhmat.setorkost.realm.RealmHelper;

import org.w3c.dom.Text;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TambahKamarActivity extends AppCompatActivity  {
    Realm realm;
    RealmHelper realmHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kamar);

        //Realm Setup
        Realm.init(TambahKamarActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final Spinner spinnerTipeRumah = (Spinner) findViewById(R.id.spinner_tipe_rumah);
        AdapterSpinner.getInstance().adapterSpinner(this, spinnerTipeRumah, "Tipe Rumah", R.layout.tipe_rumah_item);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        TextView textView = view.findViewById(R.id.title_custom_action_bar);
        textView.setText("Tambah Kamar");

        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button tambahButton = findViewById(R.id.button_tambah);
        tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNomorKamar = findViewById(R.id.edit_text_nomor_kamar);
                EditText editTextHargaKamar = findViewById(R.id.edit_text_harga_kamar);

                TextView errorTextTipeRumah = (TextView) spinnerTipeRumah.getSelectedView();

                String tipeRumahText = spinnerTipeRumah.getSelectedItem().toString();
                String nomorKamarText = editTextNomorKamar.getText().toString();
                String hargaKamarText = editTextHargaKamar.getText().toString();

                if(tipeRumahText.trim().equalsIgnoreCase("") || tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah")){
                    errorTextTipeRumah.setError("Pilih Tipe Rumah");
                }
                if(nomorKamarText.trim().equalsIgnoreCase("")){
                    editTextNomorKamar.setError("Masukkan Nomor Kamar");
                }
                if(hargaKamarText.trim().equalsIgnoreCase("")){
                    editTextHargaKamar.setError("Masukkan Harga Kamar");
                }

                if ((!tipeRumahText.trim().equalsIgnoreCase("") || !tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah"))
                        && !nomorKamarText.trim().equalsIgnoreCase("")
                        && !hargaKamarText.trim().equalsIgnoreCase("")){
                    Kamar modelKamar = new Kamar();

                    modelKamar.setTipeKamar(tipeRumahText);
                    modelKamar.setNomorKamar(nomorKamarText);
                    modelKamar.setHargaKamar(hargaKamarText);
                    realmHelper.saveKamar(modelKamar);

                    finish();

                    //Toast.makeText(activity, nomorKamarText + " " + hargaKamarText, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
