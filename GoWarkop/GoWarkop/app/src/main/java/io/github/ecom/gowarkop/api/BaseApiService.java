package io.github.ecom.gowarkop.api;

import io.github.ecom.gowarkop.model.Pesan;
import io.github.ecom.gowarkop.model.ResponseLogin;
import io.github.ecom.gowarkop.model.ResponseMenu;
import io.github.ecom.gowarkop.model.ResponsePesan;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("allmenu/")
    Call<ResponseMenu> getAllMenu(@Query("key") String key);

    @GET("menu/search")
    Call<ResponseMenu> getSearchMenu(@Query("keyword") String keyword, @Query("key") String key);

    @POST("pesan/")
    Call<ResponseBody> postPesan(@Body Pesan pesan, @Query("key") String key);

    @FormUrlEncoded
    @POST("login/")
    Call<ResponseLogin> login(@Field("email") String email, @Field("password") String password, @Query("key") String key);

    @FormUrlEncoded
    @POST("register/")
    Call<ResponseBody> register(@Field("nama") String nama, @Field("alamat") String alamat,
                                @Field("no_hp") String no_hp, @Field("email") String email,
                                @Field("password") String password, @Field("api_key") String api_key,
                                @Field("hit") String hit, @Query("key") String key);

    @GET("pesan/lihat/{email}")
    Call<ResponsePesan> getMyPesanan(@Path("email") String email, @Query("key")String key);



}
