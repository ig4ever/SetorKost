package com.rakhmat.setorkost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PenghuniFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penghuni, container, false);
        AdapterSpinnerTipeRumah.getInstance().adapterSpinnerFilterTipeRumah(view);
        return view;
    }
}
