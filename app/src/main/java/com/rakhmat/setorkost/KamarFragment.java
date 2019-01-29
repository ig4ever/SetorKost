package com.rakhmat.setorkost;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class KamarFragment extends Fragment {
    private ArrayList<Kamar> listKamar = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kamar, container, false);
        recyclerView = view.findViewById(R.id.rv_kamar);
        AdapterSpinnerTipeRumah.getInstance().adapterSpinnerFilterTipeRumah(view);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));
        recyclerView.setHasFixedSize(true);
        listKamar.addAll(KamarData.getListData());
        showRecyclerList(view.getContext());

        return view;
    }

    private void showRecyclerList(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ListKamarAdapter listKamarAdapter = new ListKamarAdapter(context);
        listKamarAdapter.setListKamar(listKamar);
        recyclerView.setAdapter(listKamarAdapter);
    }
}
