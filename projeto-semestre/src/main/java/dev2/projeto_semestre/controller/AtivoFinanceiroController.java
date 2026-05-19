package dev2.projeto_semestre.controller;

    @RestController
    @RequestMapping("/api/ativos")
    public class AtivoFinanceiroController {

        private final AtivoFinanceiroService service;

        public AtivoFinanceiroController(AtivoFinanceiroService service) {
            this.service = service;
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
