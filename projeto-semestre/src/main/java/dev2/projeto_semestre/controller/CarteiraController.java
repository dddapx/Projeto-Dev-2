package dev2.projeto_semestre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.service.CarteiraService;

@RestController
@RequestMapping("/api/carteiras") 
@CrossOrigin(origins = "http://localhost:3000")
public class CarteiraController {

    private final CarteiraService service;

    public CarteiraController(CarteiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CarteiraResponseDTO> criar(@RequestBody CarteiraRequestDTO request) {
        CarteiraResponseDTO response = service.criarCarteira(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarteiraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CarteiraResponseDTO>> listarPorInvestidor(
            @RequestParam(required = false) Long investidorId) {
        
        if (investidorId != null) {
            return ResponseEntity.ok(service.listarCarteirasPorInvestidor(investidorId));
        }
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarteiraResponseDTO> atualizar(@PathVariable Long id, @RequestBody CarteiraRequestDTO request) {
        return ResponseEntity.ok(service.atualizarCarteira(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarCarteira(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/resumo")
    public ResponseEntity<dev2.projeto_semestre.dto.ResumoCarteiraDTO> obterResumo(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterResumoCarteira(id));
    }
}