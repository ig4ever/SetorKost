package com.rakhmat.setorkost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ListKamarAdapter extends RecyclerView.Adapter<ListKamarAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Kamar> listKamar;

    public ListKamarAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Kamar> getListKamar() {
        return listKamar;
    }

    public void setListKamar(ArrayList<Kamar> listKamar) {
        this.listKamar = listKamar;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kamar, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.tvTipeRumah.setText(getListKamar().get(position).getTipeKamar());
        holder.tvNomorKamar.setText(getListKamar().get(position).getNomorKamar());
        holder.tvHargaKamar.setText(getListKamar().get(position).getHargaKamar());

    }

    @Override
    public int getItemCount() {
        return getListKamar().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvTipeRumah;
        TextView tvNomorKamar;
        TextView tvHargaKamar;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTipeRumah = itemView.findViewById(R.id.item_tipe_rumah);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvHargaKamar = itemView.findViewById(R.id.item_harga_kamar);
        }
    }
}
