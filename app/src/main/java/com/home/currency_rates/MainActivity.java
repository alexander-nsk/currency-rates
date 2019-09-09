package com.home.currency_rates;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingProgress;
    private TextView usdPlnCurrency;    //view for showing USD-PLN currency

    //init controller and callback
    private Controller controller = new Controller(new Controller.OnCurrencyUpdatedListener() {
        @Override
        public void onCurrencyUpdateStarted() {
            //start loading
            showLoader();
        }

        @Override
        public void onCurrencyUpdatedFinished(String currency) {
            //request fetched successfully
            showCurrency();
            updateCurrencyView(getString(R.string.main_usd_pln_currency, currency));
        }

        @Override
        public void onCurrencyUpdateFailed(Throwable t) {
            //error during request
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

    /**
     * Set fetched string value to text view
     *
     * @param value - USD/PLN currency
     */
    private void updateCurrencyView(String value) {
        this.usdPlnCurrency.setText(value);
    }

    //hide text view and show progress
    private void showLoader() {
        this.usdPlnCurrency.setVisibility(View.INVISIBLE);
        this.loadingProgress.setVisibility(View.VISIBLE);
    }

    //show text view and hide progress
    private void showCurrency() {
        this.usdPlnCurrency.setVisibility(View.VISIBLE);
        this.loadingProgress.setVisibility(View.INVISIBLE);
    }
}
