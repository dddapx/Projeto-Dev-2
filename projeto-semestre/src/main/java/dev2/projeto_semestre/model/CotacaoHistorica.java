package dev2.projeto_semestre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "tb_cotacao_historica")
public class CotacaoHistorica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "ativo_financeiro_id")
    private AtivoFinanceiro ativoFinanceiro;

    public AtivoFinanceiro getAtivoFinanceiro() { return ativoFinanceiro; }
    public void setAtivoFinanceiro(AtivoFinanceiro ativoFinanceiro) { this.ativoFinanceiro = ativoFinanceiro; }

    public CotacaoHistorica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}