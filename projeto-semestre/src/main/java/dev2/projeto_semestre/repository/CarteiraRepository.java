package dev2.projeto_semestre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev2.projeto_semestre.model.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long>{
    List<Carteira> findByInvestidorId(Long investidorId);
}