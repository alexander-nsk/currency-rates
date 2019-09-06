package com.home.currency_rates.model;

public class CurrencyModel {
    private String currencyValue;
    private OnCurrencyUpdatedListener onCurrencyUpdatedListener;

    public void setOnCurrencyUpdatedListener(OnCurrencyUpdatedListener onCurrencyUpdatedListener) {
        this.onCurrencyUpdatedListener = onCurrencyUpdatedListener;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }


    public interface OnCurrencyUpdatedListener {
        void onCurrencyUpdated(String currency);
    }
}
