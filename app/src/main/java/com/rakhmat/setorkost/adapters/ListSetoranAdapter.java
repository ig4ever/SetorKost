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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.rakhmat.setorkost.R;
import com.rakhmat.setorkost.activity.UbahSetoranActivity;
import com.rakhmat.setorkost.model.Setoran;
import com.rakhmat.setorkost.realm.RealmHelper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmRecyclerViewAdapter;

public class ListSetoranAdapter extends RealmRecyclerViewAdapter<Setoran, ListSetoranAdapter.CategoryViewHolder> implements Filterable {
    private Context context;
    Realm realm;
    RealmHelper realmHelper;
    String tipeRumah;
    String namaPenghuni;
    String periode;

    public ListSetoranAdapter(Context context, Realm realm, OrderedRealmCollection<Setoran> data) {
        super(data, true);
        this.context = context;
        this.realm = realm;
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
        final Setoran model = getData().get(position);
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

        holder.buttonResetSetoran.setOnTouchListener(new View.OnTouchListener(){
            long then = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    then = (Long) System.currentTimeMillis();
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(((Long) System.currentTimeMillis() - then) > 2100){
                        realmHelper.updateSetoran(model.getId(), "Belum Setor", "-");
                        notifyDataSetChanged();
                        return true;
                    }
                }
                return false;
            }
        });

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
        return getData().size();
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
        Button buttonResetSetoran;

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
            buttonResetSetoran = itemView.findViewById(R.id.button_reset_setoran);
        }
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

        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String tempDate = format.format(c.getTime());
        String [] dateParts = tempDate.split("/");

        if (text.equals("januari " + dateParts[2]) || text.equals("februari " + dateParts[2]) || text.equals("maret " + dateParts[2])
                || text.equals("april " + dateParts[2]) || text.equals("mei " + dateParts[2]) || text.equals("juni " + dateParts[2])
                || text.equals("juli " + dateParts[2]) || text.equals("agustus " + dateParts[2]) || text.equals("september " + dateParts[2])
                || text.equals("oktober " + dateParts[2]) || text.equals("november " + dateParts[2]) || text.equals("desember " + dateParts[2])){

            periode = text;
        }

        System.out.println(periode);

        if(text == null || "".equals(text)) {
            updateData(realm.where(Setoran.class).findAll());
        } else {
            updateData(realm.where(Setoran.class)
                    .equalTo("nomorKamar", text, Case.INSENSITIVE)
                    .or()
                    .equalTo("tipeKamar", tipeRumah, Case.INSENSITIVE)
                    .or()
                    .equalTo("nama", namaPenghuni, Case.INSENSITIVE)
                    .or()
                    .equalTo("periode", periode, Case.INSENSITIVE)
                    .findAll());
        }
    }

    @Override
    public Filter getFilter() {
        return new FilterSetoran(this);
    }

    private class FilterSetoran
            extends Filter {
        private final ListSetoranAdapter adapter;

        private FilterSetoran(ListSetoranAdapter adapter) {
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
}
