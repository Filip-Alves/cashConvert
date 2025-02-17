package com.me.cashConvert.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyService {

    private final WebClient webClient;
    private final String API_URL_CURRENCIES = "https://api.frankfurter.dev/v1/currencies";

    public CurrencyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // Récupérer la liste des devises avec leur nom complet depuis /currencies
    public Map<String, String> getAvailableCurrencies() {
        try {
            return webClient.get()
                    .uri(API_URL_CURRENCIES)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des devises", e);
        }
    }

    // Récupérer le taux de change entre deux devises
    public BigDecimal getExchangeRate(String from, String to) {
        try {
            Map<String, Object> response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("api.frankfurter.dev")
                            .path("/v1/latest")
                            .queryParam("base", from)
                            .queryParam("symbols", to)
                            .build())
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            Map<String, Object> rates = (Map<String, Object>) response.get("rates");
            return new BigDecimal(rates.get(to).toString());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des taux", e);
        }
    }
}
