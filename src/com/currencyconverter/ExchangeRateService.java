package com.currencyconverter;

import com.google.gson.Gson;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ExchangeRateService {
    private static final String API_URL =
            "https://v6.exchangerate-api.com/v6/deae59874bdcb2933616e22e/latest/USD";
    private final HttpClient httpClient;
    private final Gson gson;

    public ExchangeRateService(){
    this.httpClient = HttpClient.newHttpClient();
    this.gson = new Gson();
    }

    public CurrencyResponse getExchangeRates()throws IOException,  InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200){

            return gson.fromJson(response.body(), CurrencyResponse.class);
        }else{
            throw new RuntimeException("Error: No se pudo obtener los datos de la API. Código: " + response.statusCode());
        }
    }
}
