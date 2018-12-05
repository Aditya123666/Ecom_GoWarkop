package com.example.aditya123666.go_api.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aditya123666.go_api.R;
import com.example.aditya123666.go_api.api.UtilsApi;
import com.example.aditya123666.go_api.model.Pesan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditya123666 on 24/05/2018.
 */

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.ViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final List<Pesan> listMenu;

    //konstruktor untuk menerima data adapter
    public PesanAdapter(Context context, List<Pesan> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public PesanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listmenu, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ViewHolder(mItemView,this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(PesanAdapter.ViewHolder holder, int position) {
        final Pesan mCurrent = listMenu.get(position);

        Glide.with(context)
                .load(mCurrent.getGambar())
                .into(holder.imgMenu);

        holder.tvNamaCustomer.setText(mCurrent.getNama());
        holder.tvNoHp.setText(mCurrent.getNoHp());
        holder.tvNamaMenu.setText(mCurrent.getNamaMenu());
        holder.tvJumlahPesanan.setText(mCurrent.getJumlah());
        holder.tvTotalHarga.setText("Rp."+mCurrent.getTotalHarga());

        holder.btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = UtilsApi.getAPIService().updateStatus(mCurrent.getId(), "1","123");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, "Sukses terima pesanan", Toast.LENGTH_SHORT).show();
                            String url = "http://maps.google.com/maps?daddr="+String.valueOf(mCurrent.getLatit())+","+String.valueOf(mCurrent.getLongit());
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse(url));
                            context.startActivity(intent);
                        }
                        else {
                            Toast.makeText(context, "gagal terima pesanan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        holder.btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseBody> call = UtilsApi.getAPIService().updateStatus(mCurrent.getId(), "2","123");
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(context, "Pesanan Berhasil ditolak", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "gagal menolak pesanan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView tvNamaMenu, tvTotalHarga,tvNamaCustomer,tvNoHp,tvJumlahPesanan;
        private ImageView imgMenu;
        private Button btnTerima, btnTolak;

        final PesanAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ViewHolder(View itemView, PesanAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            tvNamaCustomer = itemView.findViewById(R.id.tv_nama_konfirmasi);
            tvNoHp = itemView.findViewById(R.id.tv_nohp_konfirmasi);
            tvNamaMenu = itemView.findViewById(R.id.tv_nama_menu_konfirmasi);
            tvJumlahPesanan = itemView.findViewById(R.id.tv_jumlah_pesanan_konfirmasi);
            tvTotalHarga = itemView.findViewById(R.id.tv_total_harga_konfirmasi);
            imgMenu = itemView.findViewById(R.id.iv_pesanan);
            btnTerima = itemView.findViewById(R.id.btn_terimapesanan);
            btnTolak = itemView.findViewById(R.id.btn_cancelpesanan);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();

        }
    }
}
