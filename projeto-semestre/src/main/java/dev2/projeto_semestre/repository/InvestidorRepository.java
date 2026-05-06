package dev2.projeto_semestre.repository;

import dev2.projeto_semestre.model.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestidorRepository extends JpaRepository<Investidor, Long>{

}