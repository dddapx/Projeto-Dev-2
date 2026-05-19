package dev2.projeto_semestre.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev2.projeto_semestre.dto.InvestidorRequestDTO;
import dev2.projeto_semestre.dto.InvestidorResponseDTO;
import dev2.projeto_semestre.model.Investidor;
import dev2.projeto_semestre.repository.InvestidorRepository;

@Service
public class InvestidorService {
    private final InvestidorRepository repository;

    public InvestidorService(InvestidorRepository repository) {
        this.repository = repository;
    }

    public InvestidorResponseDTO criarInvestidor(InvestidorRequestDTO dto) {
        Investidor investidor = new Investidor();
        investidor.setNome(dto.nome());
        investidor.setEmail(dto.email());

        Investidor investidorSalvo = repository.save(investidor);

        return new InvestidorResponseDTO(
                investidorSalvo.getId(),
                investidorSalvo.getNome(),
                investidorSalvo.getEmail()
        );
    }

    public List<InvestidorResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(inv -> new InvestidorResponseDTO(inv.getId(), inv.getNome(), inv.getEmail()))
                .toList();
    }

    public InvestidorResponseDTO buscarPorId(Long id) {
        Investidor inv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investidor não encontrado!"));
        return new InvestidorResponseDTO(inv.getId(), inv.getNome(), inv.getEmail());
    }

    public InvestidorResponseDTO atualizarInvestidor(Long id, InvestidorRequestDTO dto) {
        Investidor inv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investidor não encontrado!"));
        inv.setNome(dto.nome());
        inv.setEmail(dto.email());
        Investidor atualizado = repository.save(inv);
        return new InvestidorResponseDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail());
    }

    public void deletarInvestidor(Long id) {
        Investidor inv = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investidor não encontrado!"));
        repository.delete(inv);
    }
}

