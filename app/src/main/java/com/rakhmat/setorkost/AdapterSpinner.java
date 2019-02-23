package com.rakhmat.setorkost;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
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

public class AdapterSpinner {
    private static AdapterSpinner adapterSpinnerInstance;

    private AdapterSpinner(){ }

    public static AdapterSpinner getInstance(){
        if (adapterSpinnerInstance == null){
            adapterSpinnerInstance = new AdapterSpinner();
        }

        return adapterSpinnerInstance;
    }

    public void adapterSpinner(View v, Spinner spinner, @LayoutRes int layoutRes, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(v.getContext(), layoutRes, optionHouseType){
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
                int dp = 10;
                float scale = v.getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (dp*scale + 0.5f);
                tv.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

                if (position == 0)
                    tv.setTextColor(Color.parseColor("#929292"));
                else
                    tv.setTextColor(Color.BLACK);
                return v;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(layoutRes);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(i > 0){
                    // Notify the selected item text
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void adapterSpinner(View v, Spinner spinner, @LayoutRes int layoutRes, String compareString, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(v.getContext(), layoutRes, optionHouseType){
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
                int dp = 10;
                float scale = v.getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (dp*scale + 0.5f);
                tv.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

                if (position == 0)
                    tv.setTextColor(Color.parseColor("#929292"));
                else
                    tv.setTextColor(Color.BLACK);
                return v;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(layoutRes);
        spinner.setAdapter(spinnerArrayAdapter);
        for (int i = 1; i < items.length; i++){
            if (compareString.equals(items[i])) {
                int spinnerPosition = spinnerArrayAdapter.getPosition(compareString);
                spinner.setSelection(spinnerPosition);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(i > 0){
                    // Notify the selected item text
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void adapterSpinner(Context context, Spinner spinner, @LayoutRes int layoutRes, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, layoutRes, optionHouseType){
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
                int dp = 10;
                float scale = v.getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (dp*scale + 0.5f);
                tv.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

                if (position == 0)
                    tv.setTextColor(Color.parseColor("#929292"));
                else
                    tv.setTextColor(Color.BLACK);
                return v;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(layoutRes);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(i > 0){
                    // Notify the selected item text
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void adapterSpinner(Context context, Spinner spinner, @LayoutRes int layoutRes, String compareString, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, layoutRes, optionHouseType){
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
                int dp = 10;
                float scale = v.getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (dp*scale + 0.5f);
                tv.setPadding(dpAsPixels, dpAsPixels, dpAsPixels, dpAsPixels);

                if (position == 0)
                    tv.setTextColor(Color.parseColor("#929292"));
                else
                    tv.setTextColor(Color.BLACK);
                return v;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(layoutRes);
        spinner.setAdapter(spinnerArrayAdapter);
        for (int i = 1; i < items.length; i++){
            if (compareString.equals(items[i])) {
                int spinnerPosition = spinnerArrayAdapter.getPosition(compareString);
                spinner.setSelection(spinnerPosition);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(i > 0){
                    // Notify the selected item text
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
