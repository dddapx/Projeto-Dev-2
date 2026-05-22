package dev2.projeto_semestre.service;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.dto.ResumoCarteiraDTO;
import dev2.projeto_semestre.exceptions.NotFoundException;
import dev2.projeto_semestre.model.Carteira;
import dev2.projeto_semestre.model.Investidor;
import dev2.projeto_semestre.model.Transacao;
import dev2.projeto_semestre.repository.CarteiraRepository;
import dev2.projeto_semestre.repository.InvestidorRepository;
import dev2.projeto_semestre.repository.TransacaoRepository;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final InvestidorRepository investidorRepository;
    private final TransacaoRepository transacaoRepository;
    private final HgBrasilApiService hgBrasilApiService;

    // Construtor atualizado com os 3 repositórios
    public CarteiraService(CarteiraRepository carteiraRepository, 
                           InvestidorRepository investidorRepository,
                           TransacaoRepository transacaoRepository,
                           HgBrasilApiService hgBrasilApiService) { 
        this.carteiraRepository = carteiraRepository;
        this.investidorRepository = investidorRepository;
        this.transacaoRepository = transacaoRepository;
        this.hgBrasilApiService = hgBrasilApiService;
    }

    public CarteiraResponseDTO criarCarteira(CarteiraRequestDTO dto) {
        
        Optional<Investidor> resultadoDoBanco = investidorRepository.findById(dto.getInvestidorId());

        Investidor donoDaCarteira;
        if (resultadoDoBanco.isPresent()) {
            donoDaCarteira = resultadoDoBanco.get(); 
        } else {
            throw new NotFoundException("Investidor não encontrado!");
        }

        Carteira novaCarteira = new Carteira();
        novaCarteira.setNome(dto.getNome());
        novaCarteira.setInvestidor(donoDaCarteira);

        Carteira carteiraSalva = carteiraRepository.save(novaCarteira);

        return new CarteiraResponseDTO(
                carteiraSalva.getId(),
                carteiraSalva.getNome(),
                carteiraSalva.getInvestidor().getNome()
        );
    }

    public CarteiraResponseDTO buscarPorId(Long id) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carteira não encontrada!"));
        return new CarteiraResponseDTO(
                carteira.getId(),
                carteira.getNome(),
                carteira.getInvestidor().getNome()
        );
    }

    public CarteiraResponseDTO atualizarCarteira(Long id, CarteiraRequestDTO dto) {
        Carteira carteira = carteiraRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Carteira não encontrada!"));
        
        Investidor investidor = investidorRepository.findById(dto.getInvestidorId())
            .orElseThrow(() -> new NotFoundException("Investidor não encontrado!"));
        
        carteira.setNome(dto.getNome());
        carteira.setInvestidor(investidor);
        Carteira atualizada = carteiraRepository.save(carteira);
        
        return new CarteiraResponseDTO(
                atualizada.getId(),
                atualizada.getNome(),
                atualizada.getInvestidor().getNome()
        );
    }

    public void deletarCarteira(Long id) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Carteira não encontrada!"));
        carteiraRepository.delete(carteira);
    }

    public ResumoCarteiraDTO obterResumoCarteira(Long id) {
        Carteira carteira = carteiraRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Carteira não encontrada!"));

        List<Transacao> transacoes = transacaoRepository.findByCarteiraId(id);

        double totalInvestido = 0.0;
        double totalAtual = 0.0;
        
        Map<String, Double> cacheDePrecos = new HashMap<>();

        for (Transacao t : transacoes) {
            String ticker = t.getAtivoFinanceiro().getCodigo();
            
            if (!cacheDePrecos.containsKey(ticker)) {
                cacheDePrecos.put(ticker, hgBrasilApiService.buscarPrecoAtivo(ticker));
            }
            
            double precoDeHoje = cacheDePrecos.get(ticker);
            
            if ("COMPRA".equalsIgnoreCase(t.getTipoOperacao())) {
                totalInvestido += (t.getQuantidade() * t.getPrecoOperacao()); 
                totalAtual += (t.getQuantidade() * precoDeHoje);              
            } else if ("VENDA".equalsIgnoreCase(t.getTipoOperacao())) {
                totalInvestido -= (t.getQuantidade() * t.getPrecoOperacao());
                totalAtual -= (t.getQuantidade() * precoDeHoje);
            }
        }

        ResumoCarteiraDTO resumo = new ResumoCarteiraDTO();
        resumo.setNomeCarteira(carteira.getNome());
        resumo.setNomeInvestidor(carteira.getInvestidor().getNome());
        resumo.setValorTotalInvestido(totalInvestido);
        resumo.setValorTotalAtual(totalAtual); 

        return resumo;
    }
}