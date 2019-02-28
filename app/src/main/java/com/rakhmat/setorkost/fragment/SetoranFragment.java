package com.rakhmat.setorkost.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.rakhmat.setorkost.AdapterSpinner;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.SimpleDividerItemDecoration;
import com.rakhmat.setorkost.adapters.ListSetoranAdapter;
import com.rakhmat.setorkost.model.Setoran;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SetoranFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private List<Setoran> setoran = new ArrayList<>();
    private RecyclerView recyclerView;
    private Realm realm;
    private RealmHelper realmHelper;
    private Context context;
    ListSetoranAdapter listSetoranAdapter;
    private Spinner spinner;
    private Spinner spinnerPeriode;
    private EditText editTextFilterNomorKamar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setoran, container, false);
        context = view.getContext();

        //Realm Setup
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        Calendar c = Calendar.getInstance();
        Calendar cOld = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String tempDate = format.format(c.getTime());
        String oldDate = format.format(cOld.getTime());
        String [] dateParts = tempDate.split("/");
        String [] oldDateParts = oldDate.split("/");
        String month = dateParts[1];
        String year = dateParts[2];
        String oldMonth = oldDateParts[1];
        String oldYear = oldDateParts[2];

        for (int i = 0; i < 12; i++){
            try {
                c.setTime(format.parse(tempDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dateParts = tempDate.split("/");
            month = dateParts[1];
            year = dateParts[2];

            if (!year.equals(oldYear)){
                break;
            }

            String charAtZeroMonth = Character.toString(month.charAt(0));
            String charAtZeroOldMonth = Character.toString(oldMonth.charAt(0));
            String charAtZeroYear = Character.toString(year.charAt(0));
            String charAtZeroOldYear = Character.toString(oldYear.charAt(0));

            if (charAtZeroMonth.equals("0")){
                month = Character.toString(month.charAt(1));
            }
            if (charAtZeroOldMonth.equals("0")){
                oldMonth = Character.toString(oldMonth.charAt(1));
            }
            if (charAtZeroYear.equals("0")){
                year = Character.toString(year.charAt(1));
            }
            if (charAtZeroOldYear.equals("0")){
                oldYear = Character.toString(oldYear.charAt(1));
            }

            for (int j = 0; j < realmHelper.getAllSetoran().size(); j++){
                if (Integer.parseInt(month) <= Integer.parseInt(oldMonth)
                        && realmHelper.getAllSetoran().get(j).getTanggalMasuk().split("/")[2].equals((charAtZeroOldYear.equals("0"))?"0"+oldYear:oldYear)){

                    if (Integer.parseInt(month) <= Integer.parseInt(oldMonth)
                            && realmHelper.getAllSetoran().get(j).getTanggalMasuk().split("/")[1].equals((charAtZeroMonth.equals("0"))?"0"+month:month)
                            && realmHelper.getAllSetoran().get(j).getStatus().equals("Belum Setor")) {

                        if(((charAtZeroMonth.equals("0"))?"0"+month:month).equals("01")){
                            month = "Januari";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("02")){
                            month = "Februari";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("03")){
                            month = "Maret";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("04")){
                            month = "April";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("05")){
                            month = "Mei";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("06")){
                            month = "Juni";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("07")){
                            month = "Juli";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("08")){
                            month = "Agustus";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("09")){
                            month = "September";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("10")){
                            month = "Oktober";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("11")){
                            month = "November";
                        }else if (((charAtZeroMonth.equals("0"))?"0"+month:month).equals("12")){
                            month = "Desember";
                        }

                        setoran.addAll(realmHelper.getAllSetoran(month + " " + oldYear, "Belum Setor"));

                        month = dateParts[1];
                        year = dateParts[2];
                    }

                    if (Integer.parseInt(month) == Integer.parseInt(oldMonth)
                            &&realmHelper.getAllSetoran().get(j).getTanggalMasuk().split("/")[1].equals((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth)
                            && realmHelper.getAllSetoran().get(j).getStatus().equals("Sudah Setor")) {
                        if(((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("01")){
                            oldMonth = "Januari";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("02")){
                            oldMonth = "Februari";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("03")){
                            oldMonth = "Maret";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("04")){
                            oldMonth = "April";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("05")){
                            oldMonth = "Mei";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("06")){
                            oldMonth = "Juni";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("07")){
                            oldMonth = "Juli";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("08")){
                            oldMonth = "Agustus";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("09")){
                            oldMonth = "September";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("10")){
                            oldMonth = "Oktober";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("11")){
                            oldMonth = "November";
                        }else if (((charAtZeroOldMonth.equals("0"))?"0"+oldMonth:oldMonth).equals("12")){
                            oldMonth = "Desember";
                        }

                        setoran.addAll(realmHelper.getAllSetoran(oldMonth + " " + oldYear, "Sudah Setor"));

                        oldMonth = oldDateParts[1];
                        oldYear = oldDateParts[2];
                    }
                }
            }

            c.add(Calendar.DATE, -30);
            tempDate = format.format(c.getTime());
        }

        recyclerView = view.findViewById(R.id.rv_setoran);

        spinner = (Spinner) view.findViewById(R.id.spinner_filter_tipe_rumah);
        spinnerPeriode = (Spinner) view.findViewById(R.id.spinner_filter_tanggal);

        editTextFilterNomorKamar = (EditText) view.findViewById(R.id.edit_text_filter_nama_penghuni_atau_nomor_kamar);

        String[] items = new String[]{
                "Filter tipe rumah",
                "30/17C",
                "31/17C",
                "33/17C"
        };

        String[] itemsPeriode = new String[]{
                "Filter periode",
                "Januari " + oldYear,
                "Februari "+ oldYear,
                "Maret "+ oldYear,
                "April "+ oldYear,
                "Mei "+ oldYear,
                "Juni "+ oldYear,
                "Juli "+ oldYear,
                "Agustus "+ oldYear,
                "September "+ oldYear,
                "Oktober "+ oldYear,
                "November "+ oldYear,
                "Desember "+ oldYear,
        };

        AdapterSpinner.getInstance().adapterSpinner(view, spinnerPeriode, R.layout.filter_tipe_rumah_item, itemsPeriode);
        AdapterSpinner.getInstance().adapterSpinner(view, spinner, R.layout.filter_tipe_rumah_item, items);

        System.out.println(oldMonth + "ss");
        if (oldMonth.equals("1")){
            spinnerPeriode.setSelection(1);

        }else if (oldMonth.equals("2")){
            spinnerPeriode.setSelection(2);

        }else if (oldMonth.equals("3")){
            spinnerPeriode.setSelection(3);

        }else if (oldMonth.equals("4")){
            spinnerPeriode.setSelection(4);

        }else if (oldMonth.equals("5")){
            spinnerPeriode.setSelection(5);

        }else if (oldMonth.equals("6")){
            spinnerPeriode.setSelection(6);

        }else if (oldMonth.equals("7")){
            spinnerPeriode.setSelection(7);

        }else if (oldMonth.equals("8")){
            spinnerPeriode.setSelection(8);

        }else if (oldMonth.equals("9")){
            spinnerPeriode.setSelection(9);

        }else if (oldMonth.equals("10")){
            spinnerPeriode.setSelection(10);

        }else if (oldMonth.equals("11")){
            spinnerPeriode.setSelection(11);

        }else if (oldMonth.equals("12")){
            spinnerPeriode.setSelection(12);

        }

        spinner.setOnItemSelectedListener(this);
        spinnerPeriode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    listSetoranAdapter.getFilter().filter(spinnerPeriode.getSelectedItem().toString());

                    InputFilter filter = new InputFilter() {
                        public CharSequence filter(CharSequence source, int start, int end,
                                                   Spanned dest, int dstart, int dend) {

                            if (String.valueOf(source).equals("")){
                                listSetoranAdapter.getFilter().filter("");
                                listSetoranAdapter.getFilter().filter(spinnerPeriode.getSelectedItem().toString());
                            }else {
                                for (int i = start; i < end; i++) {
                                    listSetoranAdapter.getFilter().filter(String.valueOf(source));
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
        });

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        recyclerView.setHasFixedSize(true);
        showRecyclerList();

        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (String.valueOf(source).equals("")){
                    listSetoranAdapter.getFilter().filter("");
                }else {
                    for (int i = start; i < end; i++) {
                        listSetoranAdapter.getFilter().filter(String.valueOf(source));
                    }
                }
                return null;
            }
        };
        editTextFilterNomorKamar.setFilters(new InputFilter[] { filter });

        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listSetoranAdapter = new ListSetoranAdapter(context, realm, realm.where(Setoran.class).findAll());
        recyclerView.setAdapter(listSetoranAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setoran = realmHelper.getAllSetoran();
        listSetoranAdapter.notifyDataSetChanged();
    }

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
            listSetoranAdapter.getFilter().filter(spinner.getSelectedItem().toString());

            InputFilter filter = new InputFilter() {
                public CharSequence filter(CharSequence source, int start, int end,
                                           Spanned dest, int dstart, int dend) {

                    if (String.valueOf(source).equals("")){
                        listSetoranAdapter.getFilter().filter("");
                        listSetoranAdapter.getFilter().filter(spinner.getSelectedItem().toString());
                    }else {
                        for (int i = start; i < end; i++) {
                            listSetoranAdapter.getFilter().filter(String.valueOf(source));
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
