package io.github.ecom.gowarkop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ecom.gowarkop.api.BaseApiService;
import io.github.ecom.gowarkop.api.UtilsApi;
import io.github.ecom.gowarkop.model.Login;
import io.github.ecom.gowarkop.model.ResponseLogin;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Query;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_email_register)
    EditText etEmail;
    @BindView(R.id.et_nama_register)
    EditText etNama;
    @BindView(R.id.et_alamat_register)
    EditText etAlamat;
    @BindView(R.id.et_hp_register)
    EditText etHp;
    @BindView(R.id.et_pass_register)
    EditText etPassword;
    @BindView(R.id.et_repass_register)
    EditText etRePassword;

    BaseApiService apiService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        apiService = UtilsApi.getAPIService();
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_submit_register)
    public void submitRegister(){
        if (!isEmptyField(etEmail.getText().toString()) || !isEmptyField(etNama.getText().toString()) ||
                !isEmptyField(etAlamat.getText().toString()) || !isEmptyField(etHp.getText().toString()) ){
            Toast.makeText(getApplicationContext(), "Harap legkapi semua data",Toast.LENGTH_SHORT).show();
        }
        if (!isValidateEmail(etEmail.getText().toString())){
            Toast.makeText(getApplicationContext(), "Email Kosong atau Salah",Toast.LENGTH_SHORT).show();
        }
        if (!isMatch(etPassword.getText().toString(),etRePassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Password dan Re-Password tidak sama",Toast.LENGTH_SHORT).show();
        }
        else{
            register(etNama.getText().toString(), etAlamat.getText().toString(), etHp.getText().toString(),
                    etEmail.getText().toString(), etPassword.getText().toString(), "999", "1", "123");
        }
    }

    //form validation
    private boolean isValidateEmail(String email){
        return !TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean isEmptyField(String yourField){
        return !TextUtils.isEmpty(yourField);
    }
    private boolean isMatch(String password, String retypePassword){
        return password.equals(retypePassword);
    }
    private void register(String nama, String alamat, String hp, String email, String password, String api_key, String hit, String key){
        Call<ResponseBody> call = apiService.register(nama, alamat, hp, email, password, api_key, hit, key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Sukses Register", Toast.LENGTH_SHORT).show();
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