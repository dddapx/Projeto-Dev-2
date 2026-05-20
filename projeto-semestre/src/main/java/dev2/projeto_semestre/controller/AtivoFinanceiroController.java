package dev2.projeto_semestre.controller;

import dev2.projeto_semestre.model.AtivoFinanceiro;
import dev2.projeto_semestre.service.AtivoFinanceiroService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ativos")
public class AtivoFinanceiroController {

        private final AtivoFinanceiroService service;

        public AtivoFinanceiroController(AtivoFinanceiroService service) {
            this.service = service;
        }

        @PostMapping
        public ResponseEntity<AtivoFinanceiro> criar(@RequestBody AtivoFinanceiro ativo){
        AtivoFinanceiro resultado = service.criarAtivo(ativo.getCodigo());
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
        }

        @GetMapping
        public ResponseEntity<List<AtivoFinanceiro>> listar() {
            return ResponseEntity.ok(service.listarTodos());
        }

        @GetMapping("/{id}")
        public ResponseEntity<AtivoFinanceiro> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(service.buscarAtivoPorId(id));
        }
    }
