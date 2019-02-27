package com.rakhmat.setorkost.activity;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.model.Setoran;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TambahPenghuniActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penghuni);

        context = this;
        activity = this;

        //Realm Setup
        Realm.init(TambahPenghuniActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final Spinner spinnerTipeRumah = (Spinner) findViewById(R.id.spinner_tipe_rumah);
        final Spinner spinnerNomorKamar = (Spinner) findViewById(R.id.spinner_nomor_kamar);
        final EditText editTextTanggalMasukPenghuni = findViewById(R.id.edit_text_tanggal_masuk);

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                editTextTanggalMasukPenghuni.setText(sdf.format(myCalendar.getTime()));
            }
        };

        editTextTanggalMasukPenghuni.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(TambahPenghuniActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String[] itemsTipeRumah = new String[]{
                "Tipe Rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };

        AdapterSpinner.getInstance().adapterSpinnerCustom(this, spinnerTipeRumah, spinnerNomorKamar, R.layout.tipe_rumah_item, itemsTipeRumah);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        TextView textView = view.findViewById(R.id.title_custom_action_bar);
        textView.setText("Tambah Penghuni");

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

        Button tambahButton = findViewById(R.id.button_tambah_penghuni);
        tambahButton.setOnClickListener(new View.OnClickListener() {
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
                    Penghuni modelPenghuni = new Penghuni();

                    modelPenghuni.setTipeKamar(tipeRumahText);
                    Kamar kamar = realmHelper.getAllKamar(tipeRumahText, nomorKamarText);
                    modelPenghuni.setHargaKamar(kamar.getHargaKamar());
                    modelPenghuni.setNama(namaPenghuniText);
                    modelPenghuni.setUmur(umurPenghuniText);
                    modelPenghuni.setPekerjaan(pekerjaanPenghuniText);
                    modelPenghuni.setNomorKamar(nomorKamarText);
                    modelPenghuni.setTanggalMasuk(tanggalMasukPenghuniText);
                    realmHelper.savePenghuni(modelPenghuni);

                    String tempDate;
                    String stringDate = tanggalMasukPenghuniText;
                    tempDate = stringDate;
                    Date currentdate = null;
                    Calendar c;
                    SimpleDateFormat format1;
                    SimpleDateFormat format2;

                    String [] dateParts = stringDate.split("/");
                    String day = dateParts[0];
                    String month = dateParts[1];
                    String year = dateParts[2];

                    for (int i = 0; i < 12; i++){
                        if (year.equals(tanggalMasukPenghuniText.split("/")[2])){

                            Setoran modelSetoran = new Setoran();
                            if(tempDate.split("/")[1].equals("01")){
                                month = "Januari";
                            }else if (tempDate.split("/")[1].equals("02")){
                                month = "Februari";
                            }else if (tempDate.split("/")[1].equals("03")){
                                month = "Maret";
                            }else if (tempDate.split("/")[1].equals("04")){
                                month = "April";
                            }else if (tempDate.split("/")[1].equals("05")){
                                month = "Mei";
                            }else if (tempDate.split("/")[1].equals("06")){
                                month = "Juni";
                            }else if (tempDate.split("/")[1].equals("07")){
                                month = "Juli";
                            }else if (tempDate.split("/")[1].equals("08")){
                                month = "Agustus";
                            }else if (tempDate.split("/")[1].equals("09")){
                                month = "September";
                            }else if (tempDate.split("/")[1].equals("10")){
                                month = "Oktober";
                            }else if (tempDate.split("/")[1].equals("11")){
                                month = "November";
                            }else if (tempDate.split("/")[1].equals("12")){
                                month = "Desember";
                            }
                            modelSetoran.setPeriode(month + " " + year);
                            modelSetoran.setTipeKamar(tipeRumahText);
                            modelSetoran.setHargaKamar(kamar.getHargaKamar());
                            modelSetoran.setNama(namaPenghuniText);
                            modelSetoran.setUmur(umurPenghuniText);
                            modelSetoran.setPekerjaan(pekerjaanPenghuniText);
                            modelSetoran.setNomorKamar(nomorKamarText);
                            modelSetoran.setTanggalMasuk(tempDate);
                            modelSetoran.setStatus("Belum Setor");
                            modelSetoran.setTanggalBayar("-");
                            realmHelper.saveSetoran(modelSetoran);

                            format1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
                            c = Calendar.getInstance();
                            try {
                                c.setTime(format1.parse(tempDate));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            c.add(Calendar.DATE, 30);
                            format2 = new SimpleDateFormat("dd/MM/yy");
                            tempDate = format2.format(c.getTime());
                            dateParts = tempDate.split("/");
                            year = dateParts[2];
                        }else {
                            break;
                        }
                    }

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
