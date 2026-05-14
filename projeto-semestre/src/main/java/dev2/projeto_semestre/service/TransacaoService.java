package dev2.projeto_semestre.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev2.projeto_semestre.dto.TransacaoRequestDTO;
import dev2.projeto_semestre.dto.TransacaoResponseDTO;
import dev2.projeto_semestre.model.AtivoFinanceiro;
import dev2.projeto_semestre.model.Carteira;
import dev2.projeto_semestre.model.Transacao;
import dev2.projeto_semestre.repository.AtivoFinanceiroRepository;
import dev2.projeto_semestre.repository.CarteiraRepository;
import dev2.projeto_semestre.repository.TransacaoRepository;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CarteiraRepository carteiraRepository;
    private final AtivoFinanceiroRepository ativoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, 
                            CarteiraRepository carteiraRepository, 
                            AtivoFinanceiroRepository ativoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.ativoRepository = ativoRepository;
    }

    public TransacaoResponseDTO registrarTransacao(TransacaoRequestDTO dto) {
        
        Optional<Carteira> carteiraOpt = carteiraRepository.findById(dto.getCarteiraId());
        Carteira carteira;
        if (carteiraOpt.isPresent()) {
            carteira = carteiraOpt.get();
        } else {
            throw new RuntimeException("Carteira não encontrada!");
        }

        Optional<AtivoFinanceiro> ativoOpt = ativoRepository.findById(dto.getAtivoFinanceiroId());
        AtivoFinanceiro ativo;
        if (ativoOpt.isPresent()) {
            ativo = ativoOpt.get();
        } else {
            throw new RuntimeException("Ativo Financeiro não encontrado!");
        }

        Transacao novaTransacao = new Transacao();
        novaTransacao.setQuantidade(dto.getQuantidade());
        novaTransacao.setPrecoOperacao(dto.getPrecoOperacao());
        novaTransacao.setTipoOperacao(dto.getTipoOperacao());
        
        novaTransacao.setCarteira(carteira);
        novaTransacao.setAtivoFinanceiro(ativo);

        Transacao salva = transacaoRepository.save(novaTransacao);

        return new TransacaoResponseDTO(
                salva.getId(),
                salva.getCarteira().getNome(),
                salva.getAtivoFinanceiro().getCodigo(),
                salva.getQuantidade(),
                salva.getPrecoOperacao(),
                salva.getTipoOperacao()
        );
    }
}