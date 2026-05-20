package dev2.projeto_semestre.dto;

public class ResumoCarteiraDTO {

    private String nomeCarteira;
    private String nomeInvestidor;
    private Double valorTotalInvestido;
    private Double valorTotalAtual;

    public ResumoCarteiraDTO() {
    }

    public ResumoCarteiraDTO(String nomeCarteira, String nomeInvestidor, Double valorTotalInvestido, Double valorTotalAtual) {
        this.nomeCarteira = nomeCarteira;
        this.nomeInvestidor = nomeInvestidor;
        this.valorTotalInvestido = valorTotalInvestido;
        this.valorTotalAtual = valorTotalAtual;
    }

    public String getNomeCarteira() { return nomeCarteira; }
    public void setNomeCarteira(String nomeCarteira) { this.nomeCarteira = nomeCarteira; }

    public String getNomeInvestidor() { return nomeInvestidor; }
    public void setNomeInvestidor(String nomeInvestidor) { this.nomeInvestidor = nomeInvestidor; }

    public Double getValorTotalInvestido() { return valorTotalInvestido; }
    public void setValorTotalInvestido(Double valorTotalInvestido) { this.valorTotalInvestido = valorTotalInvestido; }

    public Double getValorTotalAtual() { return valorTotalAtual; }
    public void setValorTotalAtual(Double valorTotalAtual) { this.valorTotalAtual = valorTotalAtual; }
}