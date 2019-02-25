package com.rakhmat.setorkost.activity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UbahPenghuniActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_penghuni);
        context = this;
        activity = this;

        //Realm Setup
        Realm.init(UbahPenghuniActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final Spinner spinnerTipeRumah = (Spinner) findViewById(R.id.spinner_tipe_rumah);
        final Spinner spinnerNomorKamar = (Spinner) findViewById(R.id.spinner_nomor_kamar);
        EditText editTextNama = findViewById(R.id.edit_text_nama_penghuni);
        EditText editTextUmur = findViewById(R.id.edit_text_umur_penghuni);
        EditText editTextPekerjaan = findViewById(R.id.edit_text_pekerjaan_penghuni);
        EditText editTextTanggalMasuk = findViewById(R.id.edit_text_tanggal_masuk);

        String[] itemsTipeRumah = new String[]{
                "Nomor Kamar",
                "30/17C",
                "31/17C",
                "33/17C"
        };

        AdapterSpinner.getInstance().adapterSpinnerCustom(this, spinnerTipeRumah, spinnerNomorKamar, R.layout.tipe_rumah_item, getIntent().getStringExtra("TIPE_RUMAH"), getIntent().getStringExtra("NOMOR_KAMAR"), itemsTipeRumah);
        editTextNama.setText(getIntent().getStringExtra("NAMA"));
        editTextUmur.setText(getIntent().getStringExtra("UMUR"));
        editTextPekerjaan.setText(getIntent().getStringExtra("PEKERJAAN"));
        editTextTanggalMasuk.setText(getIntent().getStringExtra("TANGGAL_MASUK"));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        TextView textView = view.findViewById(R.id.title_custom_action_bar);
        textView.setText("Ubah Penghuni");

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

        Button ubahButton = findViewById(R.id.button_ubah_penghuni);
        ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextNamaPenghuni = findViewById(R.id.edit_text_nama_penghuni);
                EditText editTextUmurPenghuni = findViewById(R.id.edit_text_umur_penghuni);
                EditText editTextPekerjaanPenghuni = findViewById(R.id.edit_text_pekerjaan_penghuni);
                EditText editTextTanggalMasukPenghuni = findViewById(R.id.edit_text_tanggal_masuk);

                TextView errorTextNomorKamar = (TextView) spinnerNomorKamar.getSelectedView();
                TextView errorTextTipeRumah = (TextView) spinnerTipeRumah.getSelectedView();

                String nomorKamarText = spinnerNomorKamar.getSelectedItem().toString();
                String tipeRumahText = spinnerTipeRumah.getSelectedItem().toString();
                String namaPenghuniText = editTextNamaPenghuni.getText().toString();
                String umurPenghuniText = editTextUmurPenghuni.getText().toString();
                String pekerjaanPenghuniText = editTextPekerjaanPenghuni.getText().toString();
                String tanggalMasukPenghuniText = editTextTanggalMasukPenghuni.getText().toString();
                int idPenghuni = getIntent().getIntExtra("ID_PENGHUNI", 0);

                if(nomorKamarText.trim().equalsIgnoreCase("") || nomorKamarText.trim().equalsIgnoreCase("Nomor Kamar")){
                    errorTextNomorKamar.setError("Pilih Nomor Kamar");
                }
                if(tipeRumahText.trim().equalsIgnoreCase("") || tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah")){
                    errorTextTipeRumah.setError("Pilih Tipe Rumah");
                }
                if(namaPenghuniText.trim().equalsIgnoreCase("")){
                    editTextNamaPenghuni.setError("Masukkan Nama Penghuni");
                }
                if(umurPenghuniText.trim().equalsIgnoreCase("")){
                    editTextUmurPenghuni.setError("Masukkan Umur Penghuni");
                }
                if(pekerjaanPenghuniText.trim().equalsIgnoreCase("")){
                    editTextPekerjaanPenghuni.setError("Masukkan Pekerjaan Penghuni");
                }
                if(tanggalMasukPenghuniText.trim().equalsIgnoreCase("")){
                    editTextTanggalMasukPenghuni.setError("Masukkan Tanggal Masuk Penghuni");
                }

                if ((!nomorKamarText.trim().equalsIgnoreCase("") && !nomorKamarText.trim().equalsIgnoreCase("Nomor Kamar"))
                        && (!tipeRumahText.trim().equalsIgnoreCase("") && !tipeRumahText.trim().equalsIgnoreCase("Tipe Rumah"))
                        && !namaPenghuniText.trim().equalsIgnoreCase("")
                        && !umurPenghuniText.trim().equalsIgnoreCase("")
                        && !pekerjaanPenghuniText.trim().equalsIgnoreCase("")
                        && !tanggalMasukPenghuniText.trim().equalsIgnoreCase("")){

                    Kamar kamar = realmHelper.getAllKamar(tipeRumahText, nomorKamarText);
                    realmHelper.updatePenghuni(idPenghuni, namaPenghuniText, umurPenghuniText, pekerjaanPenghuniText, tipeRumahText, kamar.getHargaKamar(), nomorKamarText, tanggalMasukPenghuniText);

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
