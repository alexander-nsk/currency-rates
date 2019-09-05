package com.home.currency_rates.network;

import com.home.currency_rates.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.home.currency_rates.network.RestConfig.baseUrl;

public class RestFactory {
    private static RestService restRestService = null;

    public static RestService getService() {
        if (restRestService == null) {
            restRestService = new Retrofit.Builder()
                    .client(createClient())
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RestService.class);
        }
        return restRestService;
    }

    private static OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return builder
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }
}
