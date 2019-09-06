package com.home.currency_rates;

import com.home.currency_rates.model.response.CurrencyData;
import com.home.currency_rates.model.response.Rates;
import com.home.currency_rates.network.RequestFactory;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.home.currency_rates.network.RequestParams.DELAY;
import static com.home.currency_rates.network.RequestParams.PERIOD;

public class Controller {
    private OnCurrencyUpdatedListener onCurrencyUpdatedListener;

    public Controller(OnCurrencyUpdatedListener onCurrencyUpdatedListener) {
        this.onCurrencyUpdatedListener = onCurrencyUpdatedListener;
    }

    public void makePeriodicRequest(String usdBase, String pln) {
        Timer mTimer = new Timer();
        mTimer.cancel();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                RequestFactory.getService().getCurrencyRate(usdBase, pln).enqueue(new Callback<CurrencyData>() {
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

                        onCurrencyUpdatedListener.onCurrencyUpdated(plnValue);
                    }

                    @Override
                    public void onFailure(Call<CurrencyData> call, Throwable t) {
                        onCurrencyUpdatedListener.onCurrencyUpdateFailed(t);
                    }
                });
            }
        }, DELAY, PERIOD);
    }


    public interface OnCurrencyUpdatedListener {
        void onCurrencyUpdated(String currency);

        void onCurrencyUpdateFailed(Throwable t);
    }
}
