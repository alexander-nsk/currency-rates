package com.home.currency_rates;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingProgress;
    private TextView usdPlnCurrency;

    private Controller controller = new Controller(new Controller.OnCurrencyUpdatedListener() {
        @Override
        public void onCurrencyUpdateStarted() {
            showLoader();
        }

        @Override
        public void onCurrencyUpdatedFinished(String currency) {
            showCurrency();
            updateCurrencyView(getString(R.string.main_usd_pln_currency, currency));
        }

        @Override
        public void onCurrencyUpdateFailed(Throwable t) {
            showCurrency();
            updateCurrencyView(getString(R.string.main_usd_pln_currency_error));
            Toast.makeText(MainActivity.this, getString(R.string.main_usd_pln_currency_error), Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        controller.startFetchRates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.pauseFetchRates();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadingProgress = findViewById(R.id.loadingProgress);
        this.usdPlnCurrency = findViewById(R.id.usdPlnCurrency);
    }

    private void updateCurrencyView(String value) {
        this.usdPlnCurrency.setText(value);
    }

    private void showLoader() {
        this.usdPlnCurrency.setVisibility(View.INVISIBLE);
        this.loadingProgress.setVisibility(View.VISIBLE);
    }

    private void showCurrency() {
        this.usdPlnCurrency.setVisibility(View.VISIBLE);
        this.loadingProgress.setVisibility(View.INVISIBLE);
    }
}
