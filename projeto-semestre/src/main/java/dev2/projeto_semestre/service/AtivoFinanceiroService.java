package dev2.projeto_semestre.service;

import dev2.projeto_semestre.model.AtivoFinanceiro;
import dev2.projeto_semestre.model.CotacaoHistorica;
import dev2.projeto_semestre.exceptions.ExternalServiceException;
import dev2.projeto_semestre.exceptions.NotFoundException;
import dev2.projeto_semestre.repository.AtivoFinanceiroRepository;
import dev2.projeto_semestre.repository.CotacaoHistoricaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
            cotacaoRepository.save(c1);
        } catch (Exception e) {
            throw new ExternalServiceException("Erro ao buscar cotação inicial", e);
        }

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