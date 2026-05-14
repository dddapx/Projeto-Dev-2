package dev2.projeto_semestre.dto;

public class TransacaoResponseDTO {

    private Long id;
    private String nomeCarteira;
    private String codigoAtivo;
    private Integer quantidade;
    private Double precoOperacao;
    private String tipoOperacao;

    public TransacaoResponseDTO() {
    }

    public TransacaoResponseDTO(Long id, String nomeCarteira, String codigoAtivo, 
                                Integer quantidade, Double precoOperacao, String tipoOperacao) {
        this.id = id;
        this.nomeCarteira = nomeCarteira;
        this.codigoAtivo = codigoAtivo;
        this.quantidade = quantidade;
        this.precoOperacao = precoOperacao;
        this.tipoOperacao = tipoOperacao;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCarteira() { return nomeCarteira; }
    public void setNomeCarteira(String nomeCarteira) { this.nomeCarteira = nomeCarteira; }

    public String getCodigoAtivo() { return codigoAtivo; }
    public void setCodigoAtivo(String codigoAtivo) { this.codigoAtivo = codigoAtivo; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoOperacao() { return precoOperacao; }
    public void setPrecoOperacao(Double precoOperacao) { this.precoOperacao = precoOperacao; }

    public String getTipoOperacao() { return tipoOperacao; }
    public void setTipoOperacao(String tipoOperacao) { this.tipoOperacao = tipoOperacao; }
}