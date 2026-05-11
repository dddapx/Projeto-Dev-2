package dev2.projeto_semestre.dto;

public class CarteiraResponseDTO {

    private Long id;
    private String nome;
    private String nomeInvestidor;

    public CarteiraResponseDTO() {
    }

    public CarteiraResponseDTO(Long id, String nome, String nomeInvestidor) {
        this.id = id;
        this.nome = nome;
        this.nomeInvestidor = nomeInvestidor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeInvestidor() {
        return nomeInvestidor;
    }

    public void setNomeInvestidor(String nomeInvestidor) {
        this.nomeInvestidor = nomeInvestidor;
    }
}