package com.example.aditya123666.go_api.api;

//import io.github.adamnain.gowarkop.model.Pesan;
//import io.github.adamnain.gowarkop.model.ResponseMenu;
import com.example.aditya123666.go_api.model.ResponseMenu;
import com.example.aditya123666.go_api.model.ResponsePesan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("pesanan/lihat/{status}")
    Call<ResponsePesan> getPesananStatus(@Path("status") String status, @Query("key")String key);

    @GET("pesan/lihat")
    Call<ResponsePesan> getAllPesanan(@Query("key")String key);

    @GET("allmenu/")
    Call<ResponseMenu> getAllMenu(@Query("key") String key);

    @FormUrlEncoded
    @PUT("status/pesan/{id}")
    Call<ResponseBody> updateStatus(@Path("id") String id, @Field("status") String status, @Query("key") String key);

    @FormUrlEncoded
    @POST("menu/tambah")
    Call<ResponseBody> postMenu(@Field("nama") String nama, @Field("harga") String harga,
                                @Field("kategori") String kategori, @Field("gambar") String gambar,
                                @Field("deskripsi") String deskripsi, @Query("key") String key);

    @DELETE("pesan/{id}")
    Call<ResponseBody> deletePesanan(@Path("id") String id, @Query("key") String key);

    @DELETE("makanan/hapus/{id}")
    Call<ResponseBody> deleteMenu(@Path("id") String id, @Query("key") String key);

    @FormUrlEncoded
    @POST("login/admin")
    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password, @Query("key") String key);

}
