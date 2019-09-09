package com.home.currency_rates;

import android.os.Handler;

import com.home.currency_rates.network.RequestService;
import com.home.currency_rates.network.response.CurrencyData;
import com.home.currency_rates.network.response.Rates;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.home.currency_rates.network.RequestParams.DELAY;
import static com.home.currency_rates.network.RequestParams.PERIOD;
import static com.home.currency_rates.network.RequestParams.PLN;
import static com.home.currency_rates.network.RequestParams.USD;

public class Controller {
    private OnCurrencyUpdatedListener onCurrencyUpdatedListener;

    private Timer timer;
    private Handler handler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            onCurrencyUpdatedListener.onCurrencyUpdateStarted();

            RequestService.getService().getCurrencyRate(USD, PLN).enqueue(new Callback<CurrencyData>() {
                @Override
                public void onResponse(Call<CurrencyData> call, Response<CurrencyData> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }

                    CurrencyData currencyData = response.body();
                    if (currencyData == null) {
                        return;
                    }

                    Rates rates = currencyData.getRates();
                    if (rates == null) {
                        return;
                    }

                    String plnValue = rates.getPLN();
                    if (plnValue == null || plnValue.isEmpty()) {
                        return;
                    }

                    onCurrencyUpdatedListener.onCurrencyUpdatedFinished(plnValue);
                }

                @Override
                public void onFailure(Call<CurrencyData> call, Throwable t) {
                    onCurrencyUpdatedListener.onCurrencyUpdateFailed(t);
                }
            });
        }
    };

    public Controller(OnCurrencyUpdatedListener onCurrencyUpdatedListener) {
        this.onCurrencyUpdatedListener = onCurrencyUpdatedListener;
    }

    public void startFetchRates() {
        this.handler = new Handler();

        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        };

        timer = new Timer();
        timer.schedule(timertask, DELAY, PERIOD); // execute in every 1 min
    }

    public void pauseFetchRates() {
        this.handler.removeCallbacksAndMessages(runnable);
        this.handler = null;

        this.timer.cancel();
        this.timer = null;
    }


    public interface OnCurrencyUpdatedListener {
        void onCurrencyUpdateStarted();

        void onCurrencyUpdatedFinished(String currency);

        void onCurrencyUpdateFailed(Throwable t);
    }
}