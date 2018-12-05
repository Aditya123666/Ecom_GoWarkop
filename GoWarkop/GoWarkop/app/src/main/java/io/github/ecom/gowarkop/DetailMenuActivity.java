package io.github.ecom.gowarkop;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailMenuActivity extends AppCompatActivity {
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    Toolbar toolbar;
    //lokasi
    private GpsTracker gpsTracker;

    int totalPesanan, totalHarga, hargaMenu;
    double latitude, longitude;

    boolean ExpandedActionBar = true;

    @BindView(R.id.img_menu_detail)
    ImageView imgMenu;
    @BindView(R.id.tv_deskripsi_detail)
    TextView tvDeskripsi;
    @BindView(R.id.tv_harga_detail)
    TextView tvHarga;
    @BindView(R.id.tv_nama_menu_detail)
    TextView tvNamaMenu;
    @BindView(R.id.tv_jumlah_pesanan_detail)
    TextView tvJumlahPesanan;
    @BindView(R.id.tv_total_harga_detail)
    TextView tvTotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        Appbar = (AppBarLayout) findViewById(R.id.appbar);
        CoolToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        totalHarga = 0;
        totalPesanan = Integer.parseInt(tvJumlahPesanan.getText().toString());
        hargaMenu = Integer.parseInt(getIntent().getStringExtra("harga"));


        Glide.with(getApplicationContext())
                .load(getIntent().getStringExtra("gambar"))
                .into(imgMenu);

        tvNamaMenu.setText(getIntent().getStringExtra("namamenu"));
        tvHarga.setText("Rp"+getIntent().getStringExtra("harga"));
        tvDeskripsi.setText(getIntent().getStringExtra("deskripsi"));
        tvTotalHarga.setText(""+totalHarga);

        Appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) > 200) {
                    ExpandedActionBar = false;
                    CoolToolbar.setTitle(getIntent().getStringExtra("namamenu"));
                    invalidateOptionsMenu();
                }
                else {
                    ExpandedActionBar = true;
                    CoolToolbar.setTitle(getIntent().getStringExtra("namamenu"));
                    invalidateOptionsMenu();
                }
            }
        });


    }

    @OnClick(R.id.btn_plus_detail)
    public void incPesanan(){
        totalPesanan = totalPesanan+1;
        tvJumlahPesanan.setText(""+totalPesanan);
        totalHarga = totalPesanan*hargaMenu;
        tvTotalHarga.setText("Rp."+totalHarga);
    }

    @OnClick(R.id.btn_min_pesanan)
    public void decPesanan(){
        if (totalPesanan > 0){
            totalPesanan = totalPesanan-1;
            tvJumlahPesanan.setText(""+totalPesanan);
            totalHarga = totalPesanan*hargaMenu;
            tvTotalHarga.setText("Rp."+totalHarga);
        }
        else {
            Toast.makeText(getApplicationContext(), "Pesanan Harus Lebih Dari Satu", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_pesan)
    public void pesan(){
        if (totalPesanan == 0){
            Toast.makeText(getApplicationContext(), "Harap Masukan Jumlah Pesanan", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(getApplicationContext(), KonfirmasiPesananActivity.class);
            i.putExtra("latitude", String.valueOf(latitude));
            i.putExtra("longitude", String.valueOf(longitude));
            i.putExtra("namamenu", getIntent().getStringExtra("namamenu"));
            i.putExtra("gambar", getIntent().getStringExtra("gambar"));
            i.putExtra("totalPesanan", String.valueOf(totalPesanan));
            i.putExtra("totalHarga", String.valueOf(totalHarga));
            startActivity(i);
        }
        //getLocation();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
