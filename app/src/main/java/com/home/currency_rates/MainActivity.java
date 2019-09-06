package com.home.currency_rates;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.home.currency_rates.model.response.CurrencyData;
import com.home.currency_rates.model.response.Rates;
import com.home.currency_rates.network.RequestFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String USD_PLN_CURRENCY_RATE = "PLN";
    private final String USD_BASE = "USD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestFactory.getService().getCurrencyRate(USD_BASE, USD_PLN_CURRENCY_RATE).enqueue(new Callback<CurrencyData>() {
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

                updateCurrencyView(plnValue);
            }

            @Override
            public void onFailure(Call<CurrencyData> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
            }
        });
    }

    private void updateCurrencyView(String value) {
        ((TextView) findViewById(R.id.usdPlnCurrency)).setText(getString(R.string.main_usd_pln_currency, value));

    }
}
