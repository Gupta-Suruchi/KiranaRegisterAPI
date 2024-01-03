package com.kirana_register_API.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Getter
@Setter
public class ExcahangeRateConversion_Service {
    private static final String API_URL = "https://api.fxratesapi.com/latest";
    public static BigDecimal RateConversion() {

        try {
            URI uri = URI.create(API_URL);

            // sending HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            // Sending the request and receiving the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeRateResponse exchangeRateResponse = objectMapper.readValue(response.body(), ExchangeRateResponse.class);
            
            // Get the exchange rate for INR
            BigDecimal inrRate = exchangeRateResponse.getRateForCurrency("INR");

            return inrRate;
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
}