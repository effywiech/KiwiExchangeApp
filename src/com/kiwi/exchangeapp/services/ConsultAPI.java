package com.kiwi.exchangeapp.services;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultAPI {

    // Define the Currencies class according to the API response structure
    public static class Currencies {
        private String result;
        private String base_code;
        private String target_code;
        private double conversion_rate;
        private double conversion_result;

        // Add getters and setters as needed
        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getBaseCode() {
            return base_code;
        }

        public void setBaseCode(String base_code) {
            this.base_code = base_code;
        }

        public String getTargetCode() {
            return target_code;
        }

        public void setTargetCode(String target_code) {
            this.target_code = target_code;
        }

        public double getConversionRate() {
            return conversion_rate;
        }

        public void setConversionRate(double conversion_rate) {
            this.conversion_rate = conversion_rate;
        }

        public double getConversionResult() {
            return conversion_result;
        }

        public void setConversionResult(double conversion_result) {
            this.conversion_result = conversion_result;
        }
    }

    public static Currencies convertCurrency(String baseCurrency, String conversionCurrency, double amount) {
        // Replace with your API key
        String apiKey = "3200b093d50249309854b86d";
        URI address = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrency + "/" + conversionCurrency + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(address)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            return gson.fromJson(response.body(), Currencies.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}