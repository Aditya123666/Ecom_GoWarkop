package com.example.aditya123666.go_api.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aditya123666.go_api.DashboardActivity;
import com.example.aditya123666.go_api.R;
import com.example.aditya123666.go_api.api.UtilsApi;
import com.example.aditya123666.go_api.model.Pesan;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    //deklarasi global variabel
    private Context context;
    private final List<Pesan> listMenu;

    //konstruktor untuk menerima data adapter
    public RiwayatAdapter(Context context, List<Pesan> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public RiwayatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_riwayat, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new ViewHolder(mItemView,this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(RiwayatAdapter.ViewHolder holder, int position) {
        final Pesan mCurrent = listMenu.get(position);

        Glide.with(context)
                .load(mCurrent.getGambar())
                .into(holder.imgMenu);

        holder.tvNamaCustomer.setText(mCurrent.getNama());
        holder.tvNoHp.setText(mCurrent.getNoHp());
        holder.tvNamaMenu.setText(mCurrent.getNamaMenu());
        holder.tvJumlahPesanan.setText(mCurrent.getJumlah());
        holder.tvTotalHarga.setText("Rp."+mCurrent.getTotalHarga());

        if (mCurrent.getStatus().equals("0")){
            holder.tvStatusPemesanan.setText("Belum Diproses");
        }
        else if (mCurrent.getStatus().equals("1")){
            holder.tvStatusPemesanan.setText("Pesanan Diantarkan");
        }
        else if (mCurrent.getStatus().equals("2")){
            holder.tvStatusPemesanan.setText("Pesanan Ditolak");
        }


    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener  {
        private TextView tvNamaMenu, tvTotalHarga,tvNamaCustomer,tvNoHp,tvJumlahPesanan, tvStatusPemesanan;
        private ImageView imgMenu;

        final RiwayatAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public ViewHolder(View itemView, RiwayatAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            tvNamaCustomer = itemView.findViewById(R.id.tv_nama_riwayat);
            tvNoHp = itemView.findViewById(R.id.tv_nohp_riwayat);
            tvNamaMenu = itemView.findViewById(R.id.tv_nama_menu_riwayat);
            tvJumlahPesanan = itemView.findViewById(R.id.tv_jumlah_pesanan_riwayat);
            tvTotalHarga = itemView.findViewById(R.id.tv_total_harga_riwayat);
            imgMenu = itemView.findViewById(R.id.iv_pesanan_riwayat);
            tvStatusPemesanan = itemView.findViewById(R.id.tv_status_pesanan);

            this.mAdapter = adapter;
            itemView.setOnLongClickListener(this);

        }


        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final Pesan element = listMenu.get(mPosition);

            //alert dialog
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Hapus data ini?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ya",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            hapusData(element.getId());
                        }
                    });

            builder1.setNegativeButton(
                    "Tidak",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return false;
        }

        private void hapusData(String id){
            Call<ResponseBody> call = UtilsApi.getAPIService().deletePesanan(id,"123");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Data Berhasil dihapus", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DashboardActivity.class);
                    context.startActivities(new Intent[]{i});
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Data Tidak berhasil diupdate", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
