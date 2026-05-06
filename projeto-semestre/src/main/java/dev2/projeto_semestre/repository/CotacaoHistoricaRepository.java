package dev2.projeto_semestre.repository;

import dev2.projeto_semestre.model.CotacaoHistorica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotacaoHistoricaRepository extends JpaRepository<CotacaoHistorica, Long>{

}