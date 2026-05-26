package dev2.projeto_semestre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev2.projeto_semestre.exceptions.NotFoundException;
import dev2.projeto_semestre.model.AtivoFinanceiro;
import dev2.projeto_semestre.model.CotacaoHistorica;
import dev2.projeto_semestre.repository.AtivoFinanceiroRepository;
import dev2.projeto_semestre.repository.CotacaoHistoricaRepository;

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

    @Transactional
    public AtivoFinanceiro criarAtivo(String codigo) {
        
        double precoInicial = 0.0;
        
        try {
            // Pergunta para a API. Se ela souber, ela devolve o valor real.
            precoInicial = hgBrasilApiService.buscarPrecoAtivo(codigo);
            
        } catch (Exception e) {
            System.out.println("Aviso: A API não forneceu dados para '" + codigo + "'. Preço definido como 0.0");
            precoInicial = 0.0; 
        }

        // SALVA NO BANCO O QUE A API DECIDIU
        AtivoFinanceiro af1 = new AtivoFinanceiro();
        af1.setCodigo(codigo.toUpperCase());
        AtivoFinanceiro ativoSalvo = ativoRepository.save(af1);

        CotacaoHistorica c1 = new CotacaoHistorica();
        c1.setPreco(precoInicial);
        c1.setAtivoFinanceiro(ativoSalvo);
        cotacaoRepository.save(c1);

        return ativoSalvo;
    }

    public List<AtivoFinanceiro> listarTodos() {
        return ativoRepository.findAll();
    }

    public AtivoFinanceiro buscarAtivoPorId(Long id) {
        return ativoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ativo não encontrado: " + id));
    }

    public void deletarAtivo(Long id) {
        AtivoFinanceiro ativo = ativoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Ativo não encontrado!"));
        ativoRepository.delete(ativo);
    }
}