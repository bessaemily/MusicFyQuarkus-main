package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Gravadora;

public record GravadoraResponseDTO(
    Long id,
    String nome
) {
    public static GravadoraResponseDTO valueOf(Gravadora gravadora){
        return new GravadoraResponseDTO(gravadora.getId(), gravadora.getNome());
    }
}
