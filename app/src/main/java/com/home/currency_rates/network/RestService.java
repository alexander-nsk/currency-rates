package com.home.currency_rates.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

    //profile
    @GET("latest")
    Call<ResponseBody> getCurrencyRate();
}
