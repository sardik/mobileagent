package com.amtesting.mobileagentatex.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amtesting.mobileagentatex.R;

import java.util.ArrayList;

/**
 * Created by Mr-AM on 6/4/2017.
 */

public class RecycleView_Account_Customer extends RecyclerView.Adapter<RecycleView_Account_Customer.ViewHolder> {

    private ArrayList<String> rv_account_customer_Data;

    public RecycleView_Account_Customer(ArrayList<String> inputData) {
        rv_account_customer_Data = inputData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // di tutorial ini kita hanya menggunakan data String untuk tiap item
        public TextView tvTitle;
        public TextView tvSubtitle;
        public TextView tvHarga;

        public ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tanggal_transaksi);
            tvSubtitle = (TextView) view.findViewById(R.id.jam_transaksi);
            tvHarga = (TextView) view.findViewById(R.id.val_tanggal_transaksi);
        }
    }
    @Override
    public RecycleView_Account_Customer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // membuat view baru
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item_account_customer, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleView_Account_Customer.ViewHolder holder, int position) {

        // - mengambil elemen dari dataset (ArrayList) pada posisi tertentu
        // - mengeset isi view dengan elemen dari dataset tersebut
        final String name = rv_account_customer_Data.get(position);
        holder.tvTitle.setText(rv_account_customer_Data.get(position));
        holder.tvHarga.setText("ngits" + position);
        holder.tvSubtitle.setText("Jam " + position);

    }

    @Override
    public int getItemCount() {
        // menghitung ukuran dataset / jumlah data yang ditampilkan di RecyclerView
        return rv_account_customer_Data.size();
    }
}
