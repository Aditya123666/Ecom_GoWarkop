package com.example.aditya123666.go_api.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aditya123666.go_api.R;
import com.example.aditya123666.go_api.adapter.PesanAdapter;
import com.example.aditya123666.go_api.api.BaseApiService;
import com.example.aditya123666.go_api.api.UtilsApi;
import com.example.aditya123666.go_api.model.Pesan;
import com.example.aditya123666.go_api.model.ResponsePesan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    View v;
    private List<Pesan> listMenu;
    private RecyclerView mRecyclerView;
    private PesanAdapter mAdapter;
    BaseApiService apiService;
    ProgressDialog loading;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main, container, false);
        listMenu = new ArrayList<>();
        mAdapter = new PesanAdapter(getActivity(), listMenu);
        mRecyclerView = v.findViewById(R.id.rv_list_request);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        apiService = UtilsApi.getAPIService();

        final String key = "123";

        loading = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);
        Call<ResponsePesan> call = apiService.getPesananStatus("0",key);
        call.enqueue(new Callback<ResponsePesan>() {
            @Override
            public void onResponse(Call<ResponsePesan> call, Response<ResponsePesan> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    listMenu = response.body().getData();

                    mRecyclerView.setAdapter(new PesanAdapter(getActivity(), listMenu));
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Failed fletch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
