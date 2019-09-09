package com.home.currency_rates.network.response;

//Class which represents server request
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
