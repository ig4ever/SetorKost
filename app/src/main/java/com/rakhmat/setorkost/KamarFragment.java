package com.rakhmat.setorkost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KamarFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kamar, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_kamar);
        AdapterSpinnerTipeRumah.getInstance().adapterSpinnerFilterTipeRumah(view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(view.getContext()));

        return view;
    }
}
