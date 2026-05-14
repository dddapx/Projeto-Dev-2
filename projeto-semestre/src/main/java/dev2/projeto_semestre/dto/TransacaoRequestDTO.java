package dev2.projeto_semestre.dto;

public class TransacaoRequestDTO {

    private Long carteiraId;
    private Long ativoFinanceiroId;
    private Integer quantidade;
    private Double precoOperacao;
    private String tipoOperacao; 

    public TransacaoRequestDTO() {
    }


    public Long getCarteiraId() {
        return carteiraId;
    }

    public void setCarteiraId(Long carteiraId) {
        this.carteiraId = carteiraId;
    }

    public Long getAtivoFinanceiroId() {
        return ativoFinanceiroId;
    }

    public void setAtivoFinanceiroId(Long ativoFinanceiroId) {
        this.ativoFinanceiroId = ativoFinanceiroId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoOperacao() {
        return precoOperacao;
    }

    public void setPrecoOperacao(Double precoOperacao) {
        this.precoOperacao = precoOperacao;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }
}