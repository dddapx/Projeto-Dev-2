package dev2.projeto_semestre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;


import dev2.projeto_semestre.dto.InvestidorRequestDTO;
import dev2.projeto_semestre.dto.InvestidorResponseDTO;
import dev2.projeto_semestre.model.Investidor;
import dev2.projeto_semestre.service.InvestidorService;

@RestController
@RequestMapping("/api/investidores")
@CrossOrigin(origins = "*")
public class InvestidorController {

    private final InvestidorService service;

    public InvestidorController(InvestidorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InvestidorResponseDTO> criar(@RequestBody InvestidorRequestDTO request) {
        InvestidorResponseDTO response = service.criarInvestidor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<InvestidorResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestidorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestidorResponseDTO> atualizar(@PathVariable Long id, @RequestBody InvestidorRequestDTO request) {
        return ResponseEntity.ok(service.atualizarInvestidor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarInvestidor(id);
        return ResponseEntity.noContent().build();
    }
}

