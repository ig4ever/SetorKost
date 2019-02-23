package com.rakhmat.setorkost.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.activity.TambahPenghuniActivity;
import com.rakhmat.setorkost.adapters.ListPenghuniAdapter;
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.SimpleDividerItemDecoration;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class PenghuniFragment extends Fragment {
    private List<Penghuni> penghuni = new ArrayList<>();
    private RecyclerView recyclerView;
    private Realm realm;
    private RealmHelper realmHelper;
    private Context context;
    private FloatingActionButton buttonTambahPenghuni;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penghuni, container, false);
        context = view.getContext();

        //Realm Setup
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        penghuni = realmHelper.getAllPenghuni();

        recyclerView = view.findViewById(R.id.rv_penghuni);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_filter_tipe_rumah);
        String[] items = new String[]{
                "Filter tipe rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };
        AdapterSpinner.getInstance().adapterSpinner(view, spinner, R.layout.filter_tipe_rumah_item, items);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        recyclerView.setHasFixedSize(true);
        showRecyclerList();

        buttonTambahPenghuni = view.findViewById(R.id.button_tambah_penghuni);
        buttonTambahPenghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TambahPenghuniActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ListPenghuniAdapter listPenghuniAdapter = new ListPenghuniAdapter(context, penghuni);
        recyclerView.setAdapter(listPenghuniAdapter);
    }
}
