package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class ClassificacaoEtaria extends DefaultEntity{
    
    @NotBlank(message = "A faixa etária deve ser informada")
    @Column(length = 2)
    private String faixaEtaria;

    @NotBlank(message = "A descrição deve ser informada")
    @Column(length = 200)
    private String descricao;

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
