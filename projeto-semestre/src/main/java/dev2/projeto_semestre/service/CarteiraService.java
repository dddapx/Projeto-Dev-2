package dev2.projeto_semestre.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.model.Carteira;
import dev2.projeto_semestre.model.Investidor;
import dev2.projeto_semestre.repository.CarteiraRepository;
import dev2.projeto_semestre.repository.InvestidorRepository;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;
    private final InvestidorRepository investidorRepository;

    public CarteiraService(CarteiraRepository carteiraRepository, InvestidorRepository investidorRepository) {
        this.carteiraRepository = carteiraRepository;
        this.investidorRepository = investidorRepository;
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
}