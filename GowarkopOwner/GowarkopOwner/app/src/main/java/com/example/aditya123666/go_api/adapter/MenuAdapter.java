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
import com.example.aditya123666.go_api.model.Menu;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{
    //deklarasi global variabel
    private Context context;
    private final List<Menu> listMenu;

    //konstruktor untuk menerima data adapter
    public MenuAdapter(Context context, List<Menu> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    //view holder berfungsi untuk setting list item yang digunakan
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_menu, null, false);

        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(layoutParams);

        return new MenuViewHolder(mItemView,this);
    }

    //bind view holder berfungsi untuk set data ke view yang ditampilkan pada list item
    @Override
    public void onBindViewHolder(MenuAdapter.MenuViewHolder holder, int position) {
        final Menu mCurrent = listMenu.get(position);

        Glide.with(context)
                .load(mCurrent.getGambar())
                .into(holder.imgMenu);

        holder.tvNamaMenu.setText(mCurrent.getNama());
        holder.tvHarga.setText("Rp."+mCurrent.getHarga());

    }

    //untuk menghitung jumlah data yang ada pada list
    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener  {
        private TextView tvNamaMenu, tvHarga;
        private ImageView imgMenu;

        final MenuAdapter mAdapter;

        //untuk casting view yang digunakan pada list item
        public MenuViewHolder(View itemView, MenuAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            tvHarga = itemView.findViewById(R.id.tv_harga_menu);
            tvNamaMenu = itemView.findViewById(R.id.tv_nama_menu);
            imgMenu = itemView.findViewById(R.id.img_menu);

            this.mAdapter = adapter;
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View view) {
            int mPosition = getLayoutPosition();
            final Menu element = listMenu.get(mPosition);

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
            Call<ResponseBody> call = UtilsApi.getAPIService().deleteMenu(id,"123");
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
