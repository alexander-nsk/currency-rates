package com.home.currency_rates.network;

import com.home.currency_rates.model.CurrencyResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    //symbols=USD,GBP
    @GET("latest")
    Call<CurrencyResponse> getCurrencyRate(@Query("base") String base, @Query("symbols") String symbols);
}
