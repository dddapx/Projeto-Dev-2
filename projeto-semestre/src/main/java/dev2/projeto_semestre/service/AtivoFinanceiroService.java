package dev2.projeto_semestre.service;

import dev2.projeto_semestre.model.AtivoFinanceiro;

@Service
public class AtivoFinanceiroService {

    private final AtivoFinanceiroRepository ativoRepository;
    private final CotacaoHistoricaRepository cotacaoRepository;
    private final HgBrasilApiService hgBrasilApiService;

    public AtivoFinanceiroService(AtivoFinanceiroRepository ativoRepository,
                                  CotacaoHistoricaRepository cotacaoRepository,
                                  HgBrasilApiService hgBrasilApiService) {
        this.ativoRepository = ativoRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.hgBrasilApiService = hgBrasilApiService;
    }

    public AtivoFinanceiro criarAtivo (String codigo){
        
    }
}