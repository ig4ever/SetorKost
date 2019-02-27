package com.rakhmat.setorkost.activity;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.model.Setoran;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class UbahSetoranActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    Context context;
    Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_setoran);
        context = this;
        activity = this;

        //Realm Setup
        Realm.init(UbahSetoranActivity.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        final TextView textViewNama = findViewById(R.id.value_nama_penghuni);
        final TextView textViewNomorKamar = findViewById(R.id.value_nomor_kamar);
        final TextView textViewTipeRumah = findViewById(R.id.value_tipe_rumah);
        final TextView textViewTagihan = findViewById(R.id.value_biaya_tagihan);
        final EditText editTextTanggalBayar = findViewById(R.id.edit_text_tanggal_setor);

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

                editTextTanggalBayar.setText(sdf.format(myCalendar.getTime()));
            }
        };

        editTextTanggalBayar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UbahSetoranActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = Double.parseDouble(getIntent().getStringExtra("HARGA_KAMAR"));
        String hargaKamar = formatter.format(myNumber);

        textViewNama.setText(getIntent().getStringExtra("NAMA"));
        textViewNomorKamar.setText(getIntent().getStringExtra("NOMOR_KAMAR"));
        textViewTipeRumah.setText(getIntent().getStringExtra("TIPE_RUMAH"));
        textViewTagihan.setText("Rp. " + hargaKamar);
        editTextTanggalBayar.setText(getIntent().getStringExtra("TANGGAL_TAGIHAN"));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        final TextView textView = view.findViewById(R.id.title_custom_action_bar);
        textView.setText("Ubah Setoran");

        ImageButton imageButton = view.findViewById(R.id.action_bar_back);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button buttonKonfirmasi = findViewById(R.id.button_konfirmasi);
        buttonKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idPenghuni = getIntent().getIntExtra("ID_SETORAN", 0);

                if(editTextTanggalBayar.getText().toString().trim().equalsIgnoreCase("")){
                    editTextTanggalBayar.setError("Masukkan Tanggal");
                }

                if (!editTextTanggalBayar.getText().toString().trim().equalsIgnoreCase("")){
                    realmHelper.updateSetoran(idPenghuni, "Sudah Setor", editTextTanggalBayar.getText().toString());

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
