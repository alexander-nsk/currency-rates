package com.home.currency_rates.model;

import com.home.currency_rates.Controller;

public class CurrencyModel {
    private String currencyValue;
    private Controller.OnCurrencyUpdatedListener onCurrencyUpdatedListener;

    public void setOnCurrencyUpdatedListener(Controller.OnCurrencyUpdatedListener onCurrencyUpdatedListener) {
        this.onCurrencyUpdatedListener = onCurrencyUpdatedListener;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }
}
