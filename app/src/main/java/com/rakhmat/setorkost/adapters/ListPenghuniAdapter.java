package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.R;

import java.util.ArrayList;
import java.util.List;

public class ListPenghuniAdapter extends RecyclerView.Adapter<ListPenghuniAdapter.CategoryViewHolder>{
    private Context context;
    private List<Penghuni> penghuni;

    public ListPenghuniAdapter(Context context, List<Penghuni> penghuni ) {
        this.context = context;
        this.penghuni = penghuni;
    }

    @NonNull
    @Override
    public ListPenghuniAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_penghuni, parent, false);
        return new ListPenghuniAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPenghuniAdapter.CategoryViewHolder holder, int position) {
        final Penghuni model = penghuni.get(position);
        holder.tvNama.setText(model.getNama());
        holder.tvUmur.setText(model.getUmur());
        holder.tvPekerjaan.setText(model.getPekerjaan());
        holder.tvNomorKamar.setText(model.getNomorKamar());
        holder.tvTanggalMasuk.setText(model.getTanggalMasuk());
    }

    @Override
    public int getItemCount() {
        return penghuni.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        TextView tvUmur;
        TextView tvPekerjaan;
        TextView tvNomorKamar;
        TextView tvTanggalMasuk;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.item_nama_penghuni);
            tvUmur = itemView.findViewById(R.id.item_umur_penghuni);
            tvPekerjaan = itemView.findViewById(R.id.item_pekerjaan_penghuni);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvTanggalMasuk = itemView.findViewById(R.id.item_tanngal_masuk);
        }
    }
}