package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ListKamarAdapter extends RecyclerView.Adapter<ListKamarAdapter.CategoryViewHolder> {
    private Context context;
    private List<Kamar> kamar;

    public ListKamarAdapter(Context context, List<Kamar> kamar) {
        this.context = context;
        this.kamar = kamar;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kamar, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Kamar model = kamar.get(position);
        holder.tvTipeRumah.setText(model.getTipeKamar());
        holder.tvNomorKamar.setText(model.getNomorKamar());
        holder.tvHargaKamar.setText(model.getHargaKamar());
    }

    @Override
    public int getItemCount() {
        return kamar.size();
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
