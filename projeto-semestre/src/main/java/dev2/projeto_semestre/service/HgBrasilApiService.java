package dev2.projeto_semestre.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HgBrasilApiService {

    private final String API_KEY = "2aef7024"; 
    private final String BASE_URL = "https://api.hgbrasil.com/finance/stock_price";

    public Double buscarPrecoAtivo(String ticker) {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = BASE_URL + "?key=" + API_KEY + "&symbol=" + ticker;

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    }
            );

            Map<String, Object> body = response.getBody();
            if (body == null || !body.containsKey("results")) {
                throw new RuntimeException("Resposta invalida da HG Brasil");
            }

            Object resultsObj = body.get("results");
            if (!(resultsObj instanceof Map)) {
                throw new RuntimeException("Resposta invalida da HG Brasil");
            }

            Map<?, ?> results = (Map<?, ?>) resultsObj;
            Object tickerObj = results.get(ticker.toUpperCase());
            if (!(tickerObj instanceof Map)) {
                throw new RuntimeException("Ticker nao encontrado na resposta da HG Brasil");
            }

            Map<?, ?> tickerData = (Map<?, ?>) tickerObj;
            Object price = tickerData.get("price");
            if (price == null) {
                throw new RuntimeException("Preco nao encontrado na resposta da HG Brasil");
            }

            return Double.valueOf(price.toString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar preço da ação " + ticker + " na HG Brasil. Verifique o código da ação ou a sua chave.", e);
        }
    }
}