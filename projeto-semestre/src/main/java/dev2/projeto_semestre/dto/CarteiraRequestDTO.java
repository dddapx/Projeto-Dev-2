package dev2.projeto_semestre.dto;

public class CarteiraRequestDTO {

    private String nome;
    private Long investidorId;

    public CarteiraRequestDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getInvestidorId() {
        return investidorId;
    }

    public void setInvestidorId(Long investidorId) {
        this.investidorId = investidorId;
    }
}