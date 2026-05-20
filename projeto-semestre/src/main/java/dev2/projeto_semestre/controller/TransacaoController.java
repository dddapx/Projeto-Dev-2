package dev2.projeto_semestre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev2.projeto_semestre.dto.TransacaoRequestDTO;
import dev2.projeto_semestre.dto.TransacaoResponseDTO;
import dev2.projeto_semestre.service.TransacaoService;

@RestController 
@RequestMapping("/api/transacoes") 
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping 
    public ResponseEntity<TransacaoResponseDTO> criar(@RequestBody TransacaoRequestDTO request) {
        
        TransacaoResponseDTO response = service.registrarTransacao(request);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody TransacaoRequestDTO request) {
        return ResponseEntity.ok(service.atualizarTransacao(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }
}