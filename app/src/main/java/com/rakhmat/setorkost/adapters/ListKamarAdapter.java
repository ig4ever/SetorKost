package com.rakhmat.setorkost.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rakhmat.setorkost.activity.MainActivity;
import com.rakhmat.setorkost.activity.TambahKamarActivity;
import com.rakhmat.setorkost.activity.UbahKamarActivity;
import com.rakhmat.setorkost.fragment.KamarFragment;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmRecyclerViewAdapter;

public class ListKamarAdapter extends RealmRecyclerViewAdapter<Kamar, ListKamarAdapter.CategoryViewHolder> implements Filterable {
    private Context context;
    String tipeRumah;
    Realm realm;
    RealmHelper realmHelper;

    public ListKamarAdapter(Context context, Realm realm, OrderedRealmCollection<Kamar> data) {
        super(data, true);
        this.context = context;
        this.realm = realm;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kamar, parent, false);

        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Kamar model = getData().get(position);

        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = Double.parseDouble(model.getHargaKamar());
        String hargaKamar = formatter.format(myNumber);

        holder.tvTipeRumah.setText("Rumah " +model.getTipeKamar());
        holder.tvNomorKamar.setText("Kamar "+model.getNomorKamar());
        holder.tvHargaKamar.setText("Rp. "+hargaKamar);

        holder.buttonHapusKamar.setOnTouchListener(new View.OnTouchListener(){
            long then = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    then = (Long) System.currentTimeMillis();
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(((Long) System.currentTimeMillis() - then) > 2100){
                        realmHelper.deleteKamar(model.getId());
                        notifyDataSetChanged();
                        return true;
                    }
                }
                return false;
            }
        });

        holder.buttonUbahKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UbahKamarActivity.class);
                intent.putExtra("ID_KAMAR", model.getId());
                intent.putExtra("TIPE_RUMAH", model.getTipeKamar());
                intent.putExtra("NOMOR_KAMAR", model.getNomorKamar());
                intent.putExtra("HARGA_KAMAR", model.getHargaKamar());
                context.startActivity(intent);
            }
        });
    }

    // filtering
    public void filterResults(String text) {
        text = text == null ? null : text.toLowerCase().trim();
        if (text.equals("30/17c") || text.equals("31/17c") || text.equals("33/17c")){
            tipeRumah = text;
        }
        if(text == null || "".equals(text)) {
            updateData(realm.where(Kamar.class).findAll());
        } else {
            updateData(realm.where(Kamar.class)
                    .equalTo("nomorKamar", text, Case.INSENSITIVE)
                    .or()
                    .equalTo("tipeKamar", tipeRumah, Case.INSENSITIVE)
                    .findAll());
        }
    }

    public Filter getFilter() {
        return new FilterKamar(this);
    }

    private class FilterKamar
            extends Filter {
        private final ListKamarAdapter adapter;

        private FilterKamar(ListKamarAdapter adapter) {
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
        TextView tvNomorKamar;
        TextView tvHargaKamar;

        Button buttonHapusKamar;
        Button buttonUbahKamar;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTipeRumah = itemView.findViewById(R.id.item_tipe_rumah);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvHargaKamar = itemView.findViewById(R.id.item_harga_kamar);
            buttonHapusKamar = itemView.findViewById(R.id.button_hapus_kamar);
            buttonUbahKamar = itemView.findViewById(R.id.button_ubah_kamar);
        }
    }

    @Override
    public int getItemCount() {
        return getData().size();
    }
}
