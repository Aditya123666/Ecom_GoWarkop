package io.github.ecom.gowarkop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import io.github.ecom.gowarkop.R;
import io.github.ecom.gowarkop.model.Pesan;


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
        View mItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_status_pesanan, null, false);

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView tvNamaMenu, tvTotalHarga,tvNamaCustomer,tvNoHp,tvJumlahPesanan, tvStatusPemesanan;
        private ImageView imgMenu;

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
            tvStatusPemesanan = itemView.findViewById(R.id.tv_status_pemesanan);
            imgMenu = itemView.findViewById(R.id.iv_pesanan);

            this.mAdapter = adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();

        }
    }
}
