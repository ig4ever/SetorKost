package com.rakhmat.setorkost.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakhmat.setorkost.AdapterSpinnerTipeRumah;
import com.rakhmat.setorkost.R;

public class SetoranFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setoran, container, false);
        AdapterSpinnerTipeRumah.getInstance().adapterSpinnerFilterTipeRumah(view);
        return view;
    }
}
