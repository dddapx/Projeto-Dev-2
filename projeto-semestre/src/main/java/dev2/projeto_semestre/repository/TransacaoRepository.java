package dev2.projeto_semestre.repository;

import dev2.projeto_semestre.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
    // O Spring cria o SELECT automático com esta linha:
    List<Transacao> findByCarteiraId(Long carteiraId);
}