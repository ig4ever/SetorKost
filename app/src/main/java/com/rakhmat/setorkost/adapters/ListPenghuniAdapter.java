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

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListPenghuniAdapter extends RecyclerView.Adapter<ListPenghuniAdapter.CategoryViewHolder>{
    private Context context;
    private List<Penghuni> penghuni;
    Realm realm;
    RealmHelper realmHelper;

    public ListPenghuniAdapter(Context context, List<Penghuni> penghuni ) {
        this.context = context;
        this.penghuni = penghuni;
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
        final Penghuni model = penghuni.get(position);

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

        holder.buttonUbahPenghuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UbahPenghuniActivity.class);
                intent.putExtra("ID_PENGHUNI", model.getId());
                intent.putExtra("NAMA", model.getNama());
                intent.putExtra("UMUR", model.getUmur());
                intent.putExtra("PEKERJAAN", model.getPekerjaan());
                intent.putExtra("TIPE_RUMAH", model.getTipeKamar());
                intent.putExtra("NOMOR_KAMAR", model.getNomorKamar());
                intent.putExtra("TANGGAL_MASUK", model.getTanggalMasuk());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return penghuni.size();
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
