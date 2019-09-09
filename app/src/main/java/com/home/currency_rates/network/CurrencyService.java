package com.home.currency_rates.network;

import com.home.currency_rates.network.response.CurrencyData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.home.currency_rates.network.RequestParams.GET_LATEST;

public interface CurrencyService {
    @GET(GET_LATEST)
    Call<CurrencyData> getCurrencyRate(@Query("base") String base, @Query("symbols") String symbols);
}
