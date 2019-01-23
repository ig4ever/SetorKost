package com.rakhmat.setorkost;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterSpinnerTipeRumah {
    private static AdapterSpinnerTipeRumah adapterSpinnerTipeRumahInstance;

    private AdapterSpinnerTipeRumah(){ }

    public static AdapterSpinnerTipeRumah getInstance(){
        if (adapterSpinnerTipeRumahInstance == null){
            adapterSpinnerTipeRumahInstance = new AdapterSpinnerTipeRumah();
        }

        return adapterSpinnerTipeRumahInstance;
    }

    public void adapterSpinnerFilterTipeRumah(View v){
        final Spinner spinner = (Spinner) v.findViewById(R.id.spinner_filter_tipe_rumah);

        String[] houseType = new String[]{
                "Filter tipe rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(houseType));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.filter_tipe_rumah_item, optionHouseType){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0)
                    return false;
                else
                    return true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) v;
                float scale = v.getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (10*scale + 0.5f);
                tv.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

                if (position == 0)
                    tv.setTextColor(Color.parseColor("#929292"));
                else
                    tv.setTextColor(Color.BLACK);
                return v;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.filter_tipe_rumah_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(i > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (view.getContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
