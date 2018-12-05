package com.example.aditya123666.go_api;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aditya123666.go_api.api.BaseApiService;
import com.example.aditya123666.go_api.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_email)
    EditText etEmail;

    BaseApiService apiService;
    SessionManager session;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        apiService = UtilsApi.getAPIService();
        session  = new SessionManager(getApplicationContext());
        session.checkLoginPage();
    }


    @OnClick(R.id.btn_login)
    public void submit(){
        if (etEmail.getText().toString().equals("") || etPassword.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Harap isi terlebih dulu", Toast.LENGTH_SHORT).show();
        }
        else{
            Call<ResponseBody> call = apiService.login(etEmail.getText().toString(), etPassword.getText().toString(),"123");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code()==200){
                        Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Sukses Login", Toast.LENGTH_SHORT).show();
                        session.createData();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Username dan Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
