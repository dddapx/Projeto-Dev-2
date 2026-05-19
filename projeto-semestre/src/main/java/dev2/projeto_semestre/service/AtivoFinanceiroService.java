package dev2.projeto_semestre.service;

import dev2.projeto_semestre.model.AtivoFinanceiro;
import dev2.projeto_semestre.model.CotacaoHistorica;

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
        AtivoFinanceiro af1 = new AtivoFinanceiro();
        af1.setCodigo(codigo);
        AtivoFinanceiro ativoSalvo = ativoRepository.save(af1);

        try { 
            double preco = hgBrasilApiService.buscarPrecoAtivo(codigo);
            CotacaoHistorica c1 = new CotacaoHistorica();
            c1.setPreco(preco);
            c1.setAtivoFinanceiro(ativoSalvo);
            cotacaoRepository.save();
        } catch (Exception e) { 
            throw new RuntimeException("Erro ao buscar cotação inicial", e);
          }
        
          return ativoSalvo;
    }
}