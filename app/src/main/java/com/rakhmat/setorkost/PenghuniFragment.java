package com.rakhmat.setorkost;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PenghuniFragment extends Fragment {
    private ArrayList<Penghuni> listPenghuni = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penghuni, container, false);
        recyclerView = view.findViewById(R.id.rv_penghuni);
        recyclerView.setNestedScrollingEnabled(false);
        AdapterSpinnerTipeRumah.getInstance().adapterSpinnerFilterTipeRumah(view);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));
        recyclerView.setHasFixedSize(true);
        listPenghuni.addAll(PenghuniData.getListData());
        showRecyclerList(view.getContext());

        return view;
    }

    private void showRecyclerList(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ListPenghuniAdapter listPenghuniAdapter = new ListPenghuniAdapter(context);
        listPenghuniAdapter.setListPenghuni(listPenghuni);
        recyclerView.setAdapter(listPenghuniAdapter);
    }
}
