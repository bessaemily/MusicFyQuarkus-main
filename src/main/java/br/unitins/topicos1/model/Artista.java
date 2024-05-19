package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Artista extends DefaultEntity {

    @NotBlank(message = "O nome deve ser informada")
    @Column(length = 200)
    private String nome;

    @NotBlank(message = "A descrição deve ser informada")
    @Column(length = 200)
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
