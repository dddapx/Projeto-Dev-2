package dev2.projeto_semestre.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade; // Quantas ações 
    private Double precoOperacao; // Preço na hora exata da transação
    private String tipoOperacao; // Compra ou Venda
    private LocalDateTime dataTransacao; // Data e hora do registro

    @ManyToOne
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;

    @ManyToOne
    @JoinColumn(name = "ativo_financeiro_id")
    private AtivoFinanceiro ativoFinanceiro;

    public Transacao() {
        this.dataTransacao = LocalDateTime.now(); 
    }


    
    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
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

    public LocalDateTime getDataTransacao() { 
        return dataTransacao; 
    }

    public void setDataTransacao(LocalDateTime dataTransacao) { 
        this.dataTransacao = dataTransacao; 
    }

    public Carteira getCarteira() { 
        return carteira; 
    }
    public void setCarteira(Carteira carteira) { 
        this.carteira = carteira; 
    }

    public AtivoFinanceiro getAtivoFinanceiro() { 
        return ativoFinanceiro; 
    }
    public void setAtivoFinanceiro(AtivoFinanceiro ativoFinanceiro) { 
        this.ativoFinanceiro = ativoFinanceiro; 
    }
}