package dev2.projeto_semestre.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev2.projeto_semestre.exceptions.ExternalServiceException;

@Service
public class HgBrasilApiService {

    private final String API_KEY = "2aef7024"; 
    private final String BASE_URL = "https://api.hgbrasil.com/finance";

    public Double buscarPrecoAtivo(String ticker) {
        String url = BASE_URL + "?key=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();

        try {
            Map resposta = restTemplate.getForObject(url, Map.class);
            Map results = (Map) resposta.get("results");
            Map stocks = (Map) results.get("stocks");
            
            Map dadosDoAtivo = (Map) stocks.get(ticker.toUpperCase());

            if (dadosDoAtivo == null) {
                throw new ExternalServiceException("O ativo " + ticker + " não foi encontrado na bolsa.");
            }

            Object valorObj = dadosDoAtivo.get("price");
            
            if (valorObj == null) {
                valorObj = dadosDoAtivo.get("points"); 
            }

            if (valorObj == null) {
                throw new ExternalServiceException("Preço/Pontuação não disponível para o ativo " + ticker);
            }

            return Double.valueOf(valorObj.toString());

        } catch (Exception e) {
            throw new ExternalServiceException("Erro interno ao processar cotação da HG Brasil.", e);
        }
    }
}