package com.rakhmat.setorkost;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.realm.RealmHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AdapterSpinner {
    private static AdapterSpinner adapterSpinnerInstance;
    Realm realm;
    RealmHelper realmHelper;
    private List<Kamar> kamar = new ArrayList<>();

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
                if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                        || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                        || selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Filter periode")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
                }else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
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
                if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                        || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                        || selectedItemText.equalsIgnoreCase("Filter tipe rumah")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
                }else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
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
                if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                        || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                        || selectedItemText.equalsIgnoreCase("Filter tipe rumah")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
                }else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
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

    public void adapterSpinnerCustom(final Context context, Spinner spinner, Spinner otherSpinner, @LayoutRes int layoutRes, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));
        final Spinner spinner2 = otherSpinner;

        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

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

                if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                        || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                        || selectedItemText.equalsIgnoreCase("Filter tipe rumah")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
                }else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }

                kamar = realmHelper.getAllKamar(selectedItemText);

                String[] itemsNomorKamar = new String[kamar.size() + 1];
                String[] temp = new String[kamar.size() + 1];

                int indexNomorKamar = 0;

                for (int x = 0; x < kamar.size() + 1 ; x++){
                    if (x == 0){
                        itemsNomorKamar[x] = "Nomor Kamar";
                    }else {
                        itemsNomorKamar[x] = kamar.get(indexNomorKamar).getNomorKamar();
                        indexNomorKamar++;
                    }

                    temp[x] = itemsNomorKamar[x];
                }
                Log.e("ss", temp[0]);

//                Log.e("ss", temp[0]);
                adapterSpinner(context, spinner2, R.layout.nomor_kamar_item, temp);

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

    public void adapterSpinnerCustom(final Context context, Spinner spinner, Spinner otherSpinner, @LayoutRes int layoutRes, String compareString1, final String compareString2, String[] items){

        final List<String> optionHouseType = new ArrayList<>(Arrays.asList(items));
        final Spinner spinner2 = otherSpinner;

        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

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
            if (compareString1.equals(items[i])) {
                int spinnerPosition = spinnerArrayAdapter.getPosition(compareString1);
                spinner.setSelection(spinnerPosition);
                break;
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                }

                kamar = realmHelper.getAllKamar(selectedItemText);

                String[] itemsNomorKamar = new String[kamar.size() + 1];
                String[] temp = new String[kamar.size() + 1];

                int indexNomorKamar = 0;

                for (int x = 0; x < kamar.size() + 1 ; x++){
                    if (x == 0){
                        itemsNomorKamar[x] = "Nomor Kamar";
                    }else {
                        itemsNomorKamar[x] = kamar.get(indexNomorKamar).getNomorKamar();
                        indexNomorKamar++;
                    }

                    temp[x] = itemsNomorKamar[x];
                }
                Log.e("ss", temp[0]);

//                Log.e("ss", temp[0]);
                adapterSpinner(context, spinner2, R.layout.nomor_kamar_item, compareString2, temp);

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
                if (selectedItemText.equalsIgnoreCase("Filter tipe rumah")
                        || selectedItemText.equalsIgnoreCase("Tipe Rumah")
                        || selectedItemText.equalsIgnoreCase("Nomor Kamar")
                        || selectedItemText.equalsIgnoreCase("Filter tipe rumah")){
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#929292"));
                }else {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                }
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
