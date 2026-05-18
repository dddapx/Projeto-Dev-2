package dev2.projeto_semestre.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class HgBrasilApiService {

    private final String API_KEY = "2aef7024"; 
    private final String BASE_URL = "https://api.hgbrasil.com/finance/stock_price";

    public Double buscarPrecoAtivo(String ticker) {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = BASE_URL + "?key=" + API_KEY + "&symbol=" + ticker;

        try {
            Map response = restTemplate.getForObject(url, Map.class);
            
            Map<String, Object> results = (Map<String, Object>) response.get("results");
            Map<String, Object> tickerData = (Map<String, Object>) results.get(ticker.toUpperCase());
            
            return Double.valueOf(tickerData.get("price").toString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar preço da ação " + ticker + " na HG Brasil. Verifique o código da ação ou a sua chave.", e);
        }
    }
}