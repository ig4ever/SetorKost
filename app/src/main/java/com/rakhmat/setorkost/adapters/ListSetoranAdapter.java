package com.rakhmat.setorkost.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.activity.UbahSetoranActivity;
import com.rakhmat.setorkost.model.Setoran;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListSetoranAdapter extends RecyclerView.Adapter<ListSetoranAdapter.CategoryViewHolder> {
    private Context context;
    private List<Setoran> setoran;
    Realm realm;
    RealmHelper realmHelper;

    public ListSetoranAdapter(Context context, List<Setoran> setoran) {
        this.context = context;
        this.setoran = setoran;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_setoran, parent, false);
        //Realm Setup
        Realm.init(context);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);

        return new ListSetoranAdapter.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Setoran model = setoran.get(position);
        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = Double.parseDouble(model.getHargaKamar());
        String hargaKamar = formatter.format(myNumber);
        SpannableStringBuilder builder = new SpannableStringBuilder();

        holder.tvPeriode.setText(model.getPeriode());
        holder.tvNama.setText("Nama : " +model.getNama());
        holder.tvUmur.setText("Umur : " + model.getUmur());
        holder.tvPekerjaan.setText("Pekerjaan : " + model.getPekerjaan());
        holder.tvNomorKamar.setText("Kamar "+model.getNomorKamar());
        holder.tvTanggalMasuk.setText("Tanggal Tagihan : " + model.getTanggalMasuk());
        holder.tvTanggalBayar.setText("Tanggal Bayar : " + model.getTanggalBayar());
        if (model.getStatus().equals("Belum Setor")){
            SpannableString whiteSpannable= new SpannableString("Status : ");
            builder.append(whiteSpannable);
            SpannableString redSpannable= new SpannableString(model.getStatus());
            redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, model.getStatus().length(), 0);
            builder.append(redSpannable);
        }else {
            SpannableString whiteSpannable= new SpannableString("Status : ");
            builder.append(whiteSpannable);
            SpannableString redSpannable= new SpannableString(model.getStatus());
            redSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, model.getStatus().length(), 0);
            builder.append(redSpannable);
        }
        holder.tvStatus.setText(builder, TextView.BufferType.SPANNABLE);
        holder.tvTipeRumah.setText("Rumah "+model.getTipeKamar());
        holder.tvHargaKamar.setText("Rp. " + hargaKamar);

        holder.buttonUpdateSetoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UbahSetoranActivity.class);
                intent.putExtra("ID_SETORAN", model.getId());
                intent.putExtra("PERIODE", model.getPeriode());
                intent.putExtra("NAMA", model.getNama());
                intent.putExtra("UMUR", model.getUmur());
                intent.putExtra("PEKERJAAN", model.getPekerjaan());
                intent.putExtra("TIPE_RUMAH", model.getTipeKamar());
                intent.putExtra("STATUS_SETORAN", model.getStatus());
                intent.putExtra("NOMOR_KAMAR", model.getNomorKamar());
                intent.putExtra("HARGA_KAMAR", model.getHargaKamar());
                intent.putExtra("TANGGAL_TAGIHAN", model.getTanggalMasuk());
                intent.putExtra("TANGGAL_BAYAR", model.getTanggalBayar());

                context.startActivity(intent);
            }
        });
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
        TextView tvTipeRumah;
        TextView tvHargaKamar;
        TextView tvTanggalMasuk;
        TextView tvTanggalBayar;
        TextView tvStatus;

        Button buttonUpdateSetoran;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvPeriode = itemView.findViewById(R.id.item_tanggal);
            tvNama = itemView.findViewById(R.id.item_nama_penghuni);
            tvUmur = itemView.findViewById(R.id.item_umur_penghuni);
            tvPekerjaan = itemView.findViewById(R.id.item_pekerjaan_penghuni);
            tvNomorKamar = itemView.findViewById(R.id.item_nomor_kamar);
            tvTanggalMasuk = itemView.findViewById(R.id.item_tanggal_tagihan);
            tvStatus = itemView.findViewById(R.id.item_status_pembayaran);
            tvTipeRumah = itemView.findViewById(R.id.item_tipe_rumah);
            tvHargaKamar = itemView.findViewById(R.id.item_harga_kamar);
            tvTanggalBayar = itemView.findViewById(R.id.item_tanggal_bayar);
            buttonUpdateSetoran = itemView.findViewById(R.id.button_update_setoran);
        }
    }
}
