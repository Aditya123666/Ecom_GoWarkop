package io.github.ecom.gowarkop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.github.ecom.gowarkop.DetailMenuActivity;
import io.github.ecom.gowarkop.R;
import io.github.ecom.gowarkop.model.Menu;

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

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
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
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            Menu element = listMenu.get(mPosition);

            //intent ke main activity dengan passing data
            Intent i = new Intent(context, DetailMenuActivity.class);
            i.putExtra("namamenu", element.getNama());
            i.putExtra("harga", element.getHarga());
            i.putExtra("gambar", element.getGambar());
            i.putExtra("deskripsi", element.getDeskripsi());
            context.startActivity(i);
            mAdapter.notifyDataSetChanged();

        }
    }
}
