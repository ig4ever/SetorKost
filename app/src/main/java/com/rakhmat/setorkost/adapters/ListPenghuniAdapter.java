package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakhmat.setorkost.model.Penghuni;
import com.rakhmat.setorkost.R;

import java.util.ArrayList;

public class ListPenghuniAdapter extends RecyclerView.Adapter<ListPenghuniAdapter.CategoryViewHolder>{
    private Context context;
    private ArrayList<Penghuni> listPenghuni;

    public ListPenghuniAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Penghuni> getListPenghuni() {
        return listPenghuni;
    }

    public void setListPenghuni(ArrayList<Penghuni> listPenghuni) {
        this.listPenghuni = listPenghuni;
    }

    @NonNull
    @Override
    public ListPenghuniAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_penghuni, parent, false);
        return new ListPenghuniAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPenghuniAdapter.CategoryViewHolder holder, int position) {
        holder.tvNama.setText(getListPenghuni().get(position).getNama());
        holder.tvUmur.setText(getListPenghuni().get(position).getUmur());
        holder.tvPekerjaan.setText(getListPenghuni().get(position).getPekerjaan());
        holder.tvNomorKamar.setText(getListPenghuni().get(position).getNomorKamar());
        holder.tvTanggalMasuk.setText(getListPenghuni().get(position).getTanggalMasuk());
    }

    @Override
    public int getItemCount() {
        return getListPenghuni().size();
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
