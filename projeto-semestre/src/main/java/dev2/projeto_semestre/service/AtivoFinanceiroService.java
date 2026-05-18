package dev2.projeto_semestre.service;



@Service
public class AtivoFinanceiroService {

    private final AtivoFinanceiroRepository ativoRepository;
    private final CotacaoHistoricaRepository cotacaoRepository;
    private final HgBrasilApiService hgBrasilApiService;

    public AtivoFinanceiroService(AtivoFinanceiroRepository ativoRepository,
                                  CotacaoHistoricaRepository cotacaoRepository,
                                  HgBrasilApiService hgBrasilApiService) {
        this.ativoRepository = ativoRepository;
        this.cotacaoRepository = cotacaoRepository;
        this.hgBrasilApiService = hgBrasilApiService;
    }

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
}