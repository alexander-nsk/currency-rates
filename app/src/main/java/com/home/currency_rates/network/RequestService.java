package com.home.currency_rates.network;

import com.home.currency_rates.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.home.currency_rates.network.RequestParams.BASE_URL;
import static com.home.currency_rates.network.RequestParams.TIME_OUT;

public class RequestService {
    private static CurrencyService restCurrencyService = null;

    public static CurrencyService getService() {
        if (restCurrencyService == null) {
            restCurrencyService = new Retrofit.Builder()
                    .client(createClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CurrencyService.class);
        }
        return restCurrencyService;
    }

    private static OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return builder
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
}
