package com.example.aditya123666.go_api.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aditya123666.go_api.R;
import com.example.aditya123666.go_api.adapter.MenuAdapter;
import com.example.aditya123666.go_api.api.BaseApiService;
import com.example.aditya123666.go_api.api.UtilsApi;
import com.example.aditya123666.go_api.model.Menu;
import com.example.aditya123666.go_api.model.ResponseMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListMenuFragment extends Fragment {

    private List<Menu> listMenu;
    private RecyclerView mRecyclerView;
    private MenuAdapter mAdapter;
    BaseApiService apiService;
    ProgressDialog loading;
    View v;


    public ListMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_list_menu, container, false);

        listMenu = new ArrayList<>();
        mAdapter = new MenuAdapter(getActivity(), listMenu);
        mRecyclerView = v.findViewById(R.id.rv_list_menu);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        apiService = UtilsApi.getAPIService();
        getAllMenu();
        return v;
    }

    private void getAllMenu(){
        loading = ProgressDialog.show(getActivity(), null, "Harap Tunggu...", true, false);
        Call<ResponseMenu> call = apiService.getAllMenu("123");
        call.enqueue(new Callback<ResponseMenu>() {
            @Override
            public void onResponse(Call<ResponseMenu> call, Response<ResponseMenu> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    listMenu = response.body().getData();

                    mRecyclerView.setAdapter(new MenuAdapter(getActivity(), listMenu));
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getActivity(), "Failed fletch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMenu> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
