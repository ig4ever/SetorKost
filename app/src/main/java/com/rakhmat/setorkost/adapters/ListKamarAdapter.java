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
import android.widget.TextView;

import com.rakhmat.setorkost.activity.MainActivity;
import com.rakhmat.setorkost.activity.TambahKamarActivity;
import com.rakhmat.setorkost.activity.UbahKamarActivity;
import com.rakhmat.setorkost.fragment.KamarFragment;
import com.rakhmat.setorkost.model.Kamar;
import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListKamarAdapter extends RecyclerView.Adapter<ListKamarAdapter.CategoryViewHolder> {
    private Context context;
    private List<Kamar> kamar;
    Realm realm;
    RealmHelper realmHelper;

    public ListKamarAdapter(Context context, List<Kamar> kamar) {
        this.context = context;
        this.kamar = kamar;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_kamar, parent, false);

        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        final Kamar model = kamar.get(position);

        holder.tvTipeRumah.setText(model.getTipeKamar());
        holder.tvNomorKamar.setText(model.getNomorKamar());
        holder.tvHargaKamar.setText(model.getHargaKamar());

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

    @Override
    public int getItemCount() {
        return kamar.size();
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
}
