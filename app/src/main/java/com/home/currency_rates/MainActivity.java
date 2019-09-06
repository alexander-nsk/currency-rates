package com.home.currency_rates;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.home.currency_rates.network.RequestParams.PLN_CURRENCY;
import static com.home.currency_rates.network.RequestParams.USD_BASE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Controller controller = new Controller(new Controller.OnCurrencyUpdatedListener() {
            @Override
            public void onCurrencyUpdated(String currency) {
                updateCurrencyView(currency);
            }

            @Override
            public void onCurrencyUpdateFailed(Throwable t) {
                Toast.makeText(MainActivity.this, "Error during request!", Toast.LENGTH_SHORT).show();
            }
        });

        controller.makePeriodicRequest(USD_BASE, PLN_CURRENCY);

    }

    private void updateCurrencyView(String value) {
        ((TextView) findViewById(R.id.usdPlnCurrency)).setText(getString(R.string.main_usd_pln_currency, value));
    }
}
