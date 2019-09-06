package com.home.currency_rates.network;

import com.home.currency_rates.model.response.CurrencyData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Request {

    //symbols=USD,GBP
    @GET("latest")
    Call<CurrencyData> getCurrencyRate(@Query("base") String base, @Query("symbols") String symbols);
}
