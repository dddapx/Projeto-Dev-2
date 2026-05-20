package dev2.projeto_semestre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev2.projeto_semestre.dto.CarteiraRequestDTO;
import dev2.projeto_semestre.dto.CarteiraResponseDTO;
import dev2.projeto_semestre.service.CarteiraService;
import dev2.projeto_semestre.dto.ResumoCarteiraDTO;

@RestController
@RequestMapping("/api/carteiras") 
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

    @PutMapping("/{id}")
    public ResponseEntity<CarteiraResponseDTO> atualizar(@PathVariable Long id, @RequestBody CarteiraRequestDTO request) {
        return ResponseEntity.ok(service.atualizarCarteira(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarCarteira(id);
        return ResponseEntity.noContent().build();
    }
}