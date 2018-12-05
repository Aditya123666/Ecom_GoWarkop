package io.github.ecom.gowarkop;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.ecom.gowarkop.adapter.PesanAdapter;
import io.github.ecom.gowarkop.api.BaseApiService;
import io.github.ecom.gowarkop.api.UtilsApi;
import io.github.ecom.gowarkop.model.Pesan;
import io.github.ecom.gowarkop.model.ResponsePesan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusPesananFragment extends Fragment {

    private List<Pesan> listMenu;
    private RecyclerView mRecyclerView;
    private PesanAdapter mAdapter;
    BaseApiService apiService;
    ProgressDialog loading;
    SessionManager session;
    View v;


    public StatusPesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_status_pesanan, container, false);
        listMenu = new ArrayList<>();
        mAdapter = new PesanAdapter(getActivity(), listMenu);
        mRecyclerView = v.findViewById(R.id.rv_list_status_pesanan);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        apiService = UtilsApi.getAPIService();
        session  = new SessionManager(getActivity());

        getStatusPesanan();
        return v;
    }

    private void getStatusPesanan(){
        final String key = "123";

        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);
        Call<ResponsePesan> call = apiService.getMyPesanan(session.getEmailPref(), key);
        call.enqueue(new Callback<ResponsePesan>() {
            @Override
            public void onResponse(Call<ResponsePesan> call, Response<ResponsePesan> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    listMenu = response.body().getData();

                    mRecyclerView.setAdapter(new PesanAdapter(getContext(), listMenu));
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Failed fletch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePesan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
