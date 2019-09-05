package com.home.currency_rates.network;

import com.home.currency_rates.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.protobuf.ProtoConverterFactory;

import static com.home.currency_rates.network.RestConfig.baseUrl;

public class RestFactory {
    private static RestService restRestService = null;

    public static RestService getService() {
        if (restRestService == null) {
            restRestService = new Retrofit.Builder()
                    .client(createClient())
                    .baseUrl(baseUrl)
                    .addConverterFactory(ProtoConverterFactory.create())
                    .build()
                    .create(RestService.class);
        }
        return restRestService;
    }

    private static OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        final OkHttpClient.Builder okHttpClientBuilder = builder.addInterceptor(chain -> {
            Request.Builder ongoing = chain.request().newBuilder();

            return chain.proceed(ongoing.build());
        });

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return okHttpClientBuilder
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }
}
