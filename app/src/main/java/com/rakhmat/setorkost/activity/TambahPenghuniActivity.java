package com.rakhmat.setorkost.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.R;

public class TambahPenghuniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_penghuni);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_nomor_kamar);
        AdapterSpinner.getInstance().adapterSpinner(this, spinner, "Nomor Kamar", R.layout.nomor_kamar_item);

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
                finish();
            }
        });
    }
}