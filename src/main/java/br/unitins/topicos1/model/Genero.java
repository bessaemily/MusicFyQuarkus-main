package br.unitins.topicos1.model;


import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Genero extends DefaultEntity {
    
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
