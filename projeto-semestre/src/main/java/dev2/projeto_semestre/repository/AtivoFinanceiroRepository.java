package dev2.projeto_semestre.repository;

import dev2.projeto_semestre.model.AtivoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoFinanceiroRepository extends JpaRepository<AtivoFinanceiro, Long>{

}