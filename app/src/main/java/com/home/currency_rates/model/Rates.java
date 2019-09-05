package com.home.currency_rates.model;

public class Rates {
    private String PLN;

    public String getPLN() {
        return PLN;
    }

    public void setPLN(String PLN) {
        this.PLN = PLN;
    }

    @Override
    public String toString() {
        return "Rates [PLN = " + PLN + "]";
    }
}
