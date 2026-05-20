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
    private final TransacaoRepository transacaoRepository;
    private final HgBrasilApiService hgBrasilApiService;

    public CarteiraService(CarteiraRepository carteiraRepository, InvestidorRepository investidorRepository,
                           TransacaoRepository transacaoRepository,HgBrasilApiService hgBrasilApiService) {
        this.carteiraRepository = carteiraRepository;
        this.investidorRepository = investidorRepository;
        this.transacaoRepository = transacaoRepository;
        this.hgBrasilApiService = hgBrasilApiService;
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

    public ResumoCarteiraDTO gerarResumo(Long id) {
        
        // busca a carteira no banco de dados para ver se ela existe
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));

        List<Transacao> todasTransacoes = transacaoRepository.findAll();

        double totalInvestido = 0.0;
        double totalAtual = 0.0;

        // faz um laço de repetição para olhar uma transação de cada vez
        for (Transacao t : todasTransacoes) {
            // verifica se essa transação pertence a carteira que estamos calculando
            if (t.getCarteira().getId().equals(id)) {
                
                String codigoDaAcao = t.getAtivoFinanceiro().getCodigo();
                int quantidade = t.getQuantidade();
                double precoDeQuandoComprou = t.getPrecoOperacao();
                
                double precoDeHoje = hgBrasilApiService.buscarPrecoAtivo(codigoDaAcao);
                
                // se foi uma compra, soma o valor na carteira
                if (t.getTipoOperacao().equalsIgnoreCase("COMPRA")) {
                    totalInvestido += (quantidade * precoDeQuandoComprou);
                    totalAtual += (quantidade * precoDeHoje);
                } 
                // se foi uma venda, diminui o valor dela
                else if (t.getTipoOperacao().equalsIgnoreCase("VENDA")) {
                    totalInvestido -= (quantidade * precoDeQuandoComprou);
                    totalAtual -= (quantidade * precoDeHoje);
                }
            }
        }

        // monta o dto para devolver pro usuário com as respostas
        return new ResumoCarteiraDTO(
                carteira.getNome(),
                carteira.getInvestidor().getNome(),
                totalInvestido,
                totalAtual
        );
    }

    public CarteiraResponseDTO buscarPorId(Long id) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        return new CarteiraResponseDTO(
                carteira.getId(),
                carteira.getNome(),
                carteira.getInvestidor().getNome()
        );
    }

    public CarteiraResponseDTO atualizarCarteira(Long id, CarteiraRequestDTO dto) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        
        Investidor investidor = investidorRepository.findById(dto.getInvestidorId())
                .orElseThrow(() -> new RuntimeException("Investidor não encontrado!"));
        
        carteira.setNome(dto.getNome());
        carteira.setInvestidor(investidor);
        Carteira atualizada = carteiraRepository.save(carteira);
        
        return new CarteiraResponseDTO(
                atualizada.getId(),
                atualizada.getNome(),
                atualizada.getInvestidor().getNome()
        );
    }

    public void deletarCarteira(Long id) {
        Carteira carteira = carteiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada!"));
        carteiraRepository.delete(carteira);
    }
}