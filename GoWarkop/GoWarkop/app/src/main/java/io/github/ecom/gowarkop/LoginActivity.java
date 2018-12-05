package io.github.ecom.gowarkop;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ecom.gowarkop.api.BaseApiService;
import io.github.ecom.gowarkop.api.UtilsApi;
import io.github.ecom.gowarkop.model.Login;
import io.github.ecom.gowarkop.model.ResponseLogin;
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

        //Menghilangkan action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ButterKnife.bind(this);

        apiService = UtilsApi.getAPIService();
        session  = new SessionManager(getApplicationContext());
        session.checkLoginPage();


    }


    @OnClick(R.id.btn_login)
    public void submit(){

        Call<ResponseLogin> call = apiService.login(etEmail.getText().toString(),etPassword.getText().toString(),"123");
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()){
                    Login login = response.body().getLogin();
                    session.createData(login.getNama(), login.getEmail(), login.getNoHp());

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Sukses Login", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Username dan Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @OnClick(R.id.tv_register)
    public void register(){
        Intent i = new Intent (LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }


    private void setLogin(){

    }
}
