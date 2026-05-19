package dev2.projeto_semestre.controller;

import dev2.projeto_semestre.model.AtivoFinanceiro;

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
