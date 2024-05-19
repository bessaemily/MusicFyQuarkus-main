package br.unitins.topicos1.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoConsulta {
    NORMAL(1, "Normal"), 
    EMERGENCIA(2, "Emergencia"), 
    PRIMEIRA_CONSULTA(3, "Primeira Consulta");

    private int id;
    private String nome;

    TipoConsulta(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static TipoConsulta valueOf(Integer id) throws IllegalArgumentException {
        for (TipoConsulta tipoConsulta : TipoConsulta.values()) {
            if (tipoConsulta.id == id)
                return tipoConsulta;
        }
        throw new IllegalArgumentException("id tipoConsulta inválido.");
    }

}
