package com.rakhmat.setorkost.fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.activity.TambahKamarActivity;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.adapters.ListKamarAdapter;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.SimpleDividerItemDecoration;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class KamarFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private RecyclerView recyclerView;
    private Realm realm;
    private RealmHelper realmHelper;
    private Context context;
    private Activity activity;
    private FloatingActionButton buttonTambahKamar;
    private ListKamarAdapter listKamarAdapter;
    private Spinner spinner;
    private EditText editTextFilterNomorKamar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kamar, container, false);
        context = view.getContext();
        activity = getActivity();

        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        recyclerView = view.findViewById(R.id.rv_kamar);

        spinner = (Spinner) view.findViewById(R.id.spinner_filter_tipe_rumah);
        editTextFilterNomorKamar = (EditText) view.findViewById(R.id.edit_text_filter_nomor_kamar);

        String[] items = new String[]{
                "Filter tipe rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };
        AdapterSpinner.getInstance().adapterSpinner(view, spinner, R.layout.filter_tipe_rumah_item, items);
        spinner.setOnItemSelectedListener(this);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        recyclerView.setHasFixedSize(true);
        showRecyclerList();

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {

                if (String.valueOf(source).equals("")){
                    listKamarAdapter.getFilter().filter("");
                    listKamarAdapter.getFilter().filter(spinner.getSelectedItem().toString());
                }else {
                    for (int i = start; i < end; i++) {
                        listKamarAdapter.getFilter().filter(String.valueOf(source));
                    }
                }
                return null;
            }
        };

        editTextFilterNomorKamar.setFilters(new InputFilter[] { filter });

        buttonTambahKamar = view.findViewById(R.id.button_tambah_kamar);
        buttonTambahKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TambahKamarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listKamarAdapter = new ListKamarAdapter(context, realm, realm.where(Kamar.class).findAll());
        recyclerView.setAdapter(listKamarAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        listKamarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedItemText = (String) adapterView.getItemAtPosition(i);

        if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                || selectedItemText.equalsIgnoreCase("Filter tipe rumah")){
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
        }else {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
            listKamarAdapter.getFilter().filter(spinner.getSelectedItem().toString());

            InputFilter filter = new InputFilter() {
                public CharSequence filter(CharSequence source, int start, int end,
                                           Spanned dest, int dstart, int dend) {

                    if (String.valueOf(source).equals("")){
                        listKamarAdapter.getFilter().filter("");
                        listKamarAdapter.getFilter().filter(spinner.getSelectedItem().toString());
                    }else {
                        for (int i = start; i < end; i++) {
                            listKamarAdapter.getFilter().filter(String.valueOf(source));
                        }
                    }
                    return null;
                }
            };

            editTextFilterNomorKamar.setFilters(new InputFilter[] { filter });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
