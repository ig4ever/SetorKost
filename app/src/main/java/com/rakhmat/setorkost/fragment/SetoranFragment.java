package com.rakhmat.setorkost.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

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

public class SetoranFragment extends Fragment {
    private List<Setoran> setoran = new ArrayList<>();
    private RecyclerView recyclerView;
    private Realm realm;
    private RealmHelper realmHelper;
    private Context context;
    ListSetoranAdapter listSetoranAdapter;

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

        System.out.println(oldMonth + " " + oldYear);

//        setoran.addAll(realmHelper.getAllSetoran(oldMonth + " " + oldYear, "Sudah Setor"));

        recyclerView = view.findViewById(R.id.rv_setoran);

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

        return view;
    }

    private void showRecyclerList(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        listSetoranAdapter = new ListSetoranAdapter(context, setoran);
        recyclerView.setAdapter(listSetoranAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setoran = realmHelper.getAllSetoran();
        listSetoranAdapter.notifyDataSetChanged();
    }
}
