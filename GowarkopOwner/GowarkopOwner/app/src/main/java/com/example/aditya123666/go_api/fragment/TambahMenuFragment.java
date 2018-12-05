package com.example.aditya123666.go_api.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditya123666.go_api.R;
import com.example.aditya123666.go_api.api.BaseApiService;
import com.example.aditya123666.go_api.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TambahMenuFragment extends Fragment {

    View v;

    @BindView(R.id.et_nama_menu_tambah)
    EditText etNama;
    @BindView(R.id.et_harga_menu_tambah)
    EditText etHarga;
    @BindView(R.id.et_kategori_menu_tambah)
    EditText etKategori;
    @BindView(R.id.et_deskripsi_menu_tambah)
    EditText etDeskrips;
    @BindView(R.id.et_link_menu_tambah)
    EditText etLink;

    BaseApiService apiService;


    public TambahMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_tambah_menu, container, false);
        ButterKnife.bind(this, v);

        apiService = UtilsApi.getAPIService();

        return v;
    }

    @OnClick(R.id.btn_submit_tambah)
    public void submitMenu(){
        if (etNama.getText().toString().equals("") || etHarga.getText().toString().equals("") || etKategori.getText().toString().equals("") || etLink.getText().toString().equals("") || etDeskrips.getText().toString().equals("") ){
            Toast.makeText(getActivity(), "Harap lengkapi data", Toast.LENGTH_SHORT).show();
        }
        else {

            Call<ResponseBody> call = apiService.postMenu(
                    etNama.getText().toString(), etHarga.getText().toString(), etKategori.getText().toString(),
                    etLink.getText().toString(), etDeskrips.getText().toString(),"123");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getActivity(), "Sukses Tambah Data", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

}
