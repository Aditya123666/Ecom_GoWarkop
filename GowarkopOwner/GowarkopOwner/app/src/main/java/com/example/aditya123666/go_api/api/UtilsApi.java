package com.example.aditya123666.go_api.api;

public class UtilsApi {

    public static final String BASE_URL_API = "https://gowarkop.herokuapp.com/public/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
