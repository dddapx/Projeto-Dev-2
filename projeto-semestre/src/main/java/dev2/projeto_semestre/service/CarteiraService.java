package dev2.projeto_semestre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.dto.ResumoCarteiraDTO;
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

    // Construtor atualizado com os 3 repositórios
    public CarteiraService(CarteiraRepository carteiraRepository, 
                           InvestidorRepository investidorRepository,
                           TransacaoRepository transacaoRepository) { 
        this.carteiraRepository = carteiraRepository;
        this.investidorRepository = investidorRepository;
        this.transacaoRepository = transacaoRepository; 
    }

    public CarteiraResponseDTO criarCarteira(CarteiraRequestDTO dto) {
        
        Optional<Investidor> resultadoDoBanco = investidorRepository.findById(dto.getInvestidorId());

        Investidor donoDaCarteira;
        if (resultadoDoBanco.isPresent()) {
            donoDaCarteira = resultadoDoBanco.get(); 
        } else {
            throw new RuntimeException("Investidor não encontrado!");
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
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        return new CarteiraResponseDTO(
                carteira.getId(),
                carteira.getNome(),
                carteira.getInvestidor().getNome()
        );
    }

    public CarteiraResponseDTO atualizarCarteira(Long id, CarteiraRequestDTO dto) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        
        Investidor investidor = investidorRepository.findById(dto.getInvestidorId())
                .orElseThrow(() -> new RuntimeException("Investidor não encontrado!"));
        
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
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        carteiraRepository.delete(carteira);
    }

    public ResumoCarteiraDTO obterResumoCarteira(Long id) {
        // 1. Busca a carteira
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));

        // 2. Busca todas as transações ligadas a esta carteira
        List<Transacao> transacoes = transacaoRepository.findByCarteiraId(id);

        // 3. A Lógica Simples e Segura 
        double total = 0.0;
        for (Transacao t : transacoes) {
            if ("COMPRA".equalsIgnoreCase(t.getTipoOperacao())) {
                total += (t.getQuantidade() * t.getPrecoOperacao());
            } else if ("VENDA".equalsIgnoreCase(t.getTipoOperacao())) {
                total -= (t.getQuantidade() * t.getPrecoOperacao());
            }
        }

        // 4. Monta e devolve a resposta final para o utilizador
        ResumoCarteiraDTO resumo = new ResumoCarteiraDTO();
        resumo.setNomeCarteira(carteira.getNome());
        resumo.setNomeInvestidor(carteira.getInvestidor().getNome());
        resumo.setTotalInvestido(total);

        return resumo;
    }
}