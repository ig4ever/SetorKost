package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.model.Setoran;

import java.util.List;

public class ListSetoranAdapter extends RecyclerView.Adapter<ListSetoranAdapter.CategoryViewHolder> {
    private Context context;
    private List<Setoran> setoran;

    public ListSetoranAdapter(Context context, List<Setoran> setoran) {
        this.context = context;
        this.setoran = setoran;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_setoran, parent, false);
        return new ListSetoranAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Setoran model = setoran.get(position);
        holder.tvPeriode.setText(model.getPeriode());
        holder.tvNama.setText(model.getNama());
        holder.tvUmur.setText(model.getUmur());
        holder.tvPekerjaan.setText(model.getPekerjaan());
        holder.tvNomorKamar.setText(model.getNomorKamar());
        holder.tvTanggalMasuk.setText(model.getTanggalMasuk());
        holder.tvStatus.setText(model.getStatus());
    }

    @Override
    public int getItemCount() {
        return setoran.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvPeriode;
        TextView tvNama;
        TextView tvUmur;
        TextView tvPekerjaan;
        TextView tvNomorKamar;
        TextView tvTanggalMasuk;
        TextView tvStatus;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvPeriode = itemView.findViewById(R.id.item_tanggal);
            tvNama = itemView.findViewById(R.id.item_nama_penghuni);
            tvUmur = itemView.findViewById(R.id.item_umur_penghuni);
            tvPekerjaan = itemView.findViewById(R.id.item_pekerjaan_penghuni);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvTanggalMasuk = itemView.findViewById(R.id.item_tanngal_masuk);
            tvStatus = itemView.findViewById(R.id.item_status_pembayaran);
        }
    }
}
