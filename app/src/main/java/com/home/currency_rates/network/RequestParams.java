package com.home.currency_rates.network;

public final class RequestParams {
    static final String BASE_URL = "https://api.exchangeratesapi.io/";
    static final String GET_LATEST = "latest";

    public static final String PLN = "PLN";
    public static final String USD = "USD";

    static final long TIME_OUT = 20; //sec
    public static final int DELAY = 0;
    public static final int PERIOD = 60 * 1000;
}
