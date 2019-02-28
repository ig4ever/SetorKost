package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rakhmat.setorkost.activity.UbahPenghuniActivity;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmRecyclerViewAdapter;

public class ListPenghuniAdapter extends RealmRecyclerViewAdapter<Penghuni, ListPenghuniAdapter.CategoryViewHolder> implements Filterable {
    private Context context;
    Realm realm;
    RealmHelper realmHelper;
    String tipeRumah;
    String namaPenghuni;

    public ListPenghuniAdapter(Context context, Realm realm, OrderedRealmCollection<Penghuni> data) {
        super(data, true);
        this.context = context;
        this.realm = realm;
    }

    @NonNull
    @Override
    public ListPenghuniAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_penghuni, parent, false);
        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        return new ListPenghuniAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPenghuniAdapter.CategoryViewHolder holder, int position) {
        final Penghuni model = getData().get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = Double.parseDouble(model.getHargaKamar());
        final String hargaKamar = formatter.format(myNumber);

        holder.tvTipeRumah.setText("Rumah "+model.getTipeKamar());
        holder.tvHargaKamar.setText("Rp. "+hargaKamar);
        holder.tvNama.setText("Nama : " +model.getNama());
        holder.tvUmur.setText("Umur : " +model.getUmur());
        holder.tvPekerjaan.setText("Pekerjaan : " +model.getPekerjaan());
        holder.tvNomorKamar.setText("Kamar " +model.getNomorKamar());
        holder.tvTanggalMasuk.setText("Tanggal Masuk : " +model.getTanggalMasuk());

        holder.buttonHapusKamar.setOnTouchListener(new View.OnTouchListener(){
            long then = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    then = (Long) System.currentTimeMillis();
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(((Long) System.currentTimeMillis() - then) > 2100){
                        realmHelper.deleteSetoran(model.getNama(), model.getUmur(), model.getPekerjaan(), model.getNomorKamar(), model.getHargaKamar(), model.getTipeKamar());
                        realmHelper.deletePenghuni(model.getId());
                        notifyDataSetChanged();
                        return true;
                    }
                }
                return false;
            }
        });

        holder.buttonUbahPenghuni.setOnTouchListener(new View.OnTouchListener(){
            long then = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    then = (Long) System.currentTimeMillis();
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(((Long) System.currentTimeMillis() - then) > 2100){
                        Intent intent = new Intent(context, UbahPenghuniActivity.class);
                        intent.putExtra("ID_PENGHUNI", model.getId());
                        intent.putExtra("NAMA", model.getNama());
                        intent.putExtra("UMUR", model.getUmur());
                        intent.putExtra("PEKERJAAN", model.getPekerjaan());
                        intent.putExtra("TIPE_RUMAH", model.getTipeKamar());
                        intent.putExtra("NOMOR_KAMAR", model.getNomorKamar());
                        intent.putExtra("TANGGAL_MASUK", model.getTanggalMasuk());

                        context.startActivity(intent);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }

    // filtering
    public void filterResults(String text) {
        text = text == null ? null : text.toLowerCase().trim();
        if (text.equals("30/17c") || text.equals("31/17c") || text.equals("33/17c")){
            tipeRumah = text;
        }
        char[] chars = text.toCharArray();

        for (char c : chars){
            if (Character.isLetter(c)){
                namaPenghuni = text;
            }
        }

        if(text == null || "".equals(text)) {
            updateData(realm.where(Penghuni.class).findAll());
        } else {
            updateData(realm.where(Penghuni.class)
                    .equalTo("nomorKamar", text, Case.INSENSITIVE)
                    .or()
                    .equalTo("tipeKamar", tipeRumah, Case.INSENSITIVE)
                    .or()
                    .equalTo("nama", namaPenghuni, Case.INSENSITIVE)
                    .findAll());
        }
    }

    @Override
    public Filter getFilter() {
        return new FilterPenghuni(this);
    }

    private class FilterPenghuni
            extends Filter {
        private final ListPenghuniAdapter adapter;

        private FilterPenghuni(ListPenghuniAdapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filterResults(constraint.toString());
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipeRumah;
        TextView tvHargaKamar;
        TextView tvNama;
        TextView tvUmur;
        TextView tvPekerjaan;
        TextView tvNomorKamar;
        TextView tvTanggalMasuk;

        Button buttonHapusKamar;
        Button buttonUbahPenghuni;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTipeRumah = itemView.findViewById(R.id.item_tipe_rumah);
            tvHargaKamar = itemView.findViewById(R.id.item_harga_kamar);
            tvNama = itemView.findViewById(R.id.item_nama_penghuni);
            tvUmur = itemView.findViewById(R.id.item_umur_penghuni);
            tvPekerjaan = itemView.findViewById(R.id.item_pekerjaan_penghuni);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvTanggalMasuk = itemView.findViewById(R.id.item_tanngal_masuk);
            buttonHapusKamar = itemView.findViewById(R.id.button_hapus_penghuni);
            buttonUbahPenghuni = itemView.findViewById(R.id.button_ubah_penghuni);
        }
    }
}
