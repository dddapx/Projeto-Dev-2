package dev2.projeto_semestre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev2.projeto_semestre.dto.InvestidorRequestDTO;
import dev2.projeto_semestre.dto.InvestidorResponseDTO;
import dev2.projeto_semestre.service.InvestidorService;

@RestController
@RequestMapping("/api/investidores")
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
}

