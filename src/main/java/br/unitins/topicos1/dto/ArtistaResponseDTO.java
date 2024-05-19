package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Artista;

public record ArtistaResponseDTO(

    Long id,
    String nome,
    String descricao
) {
    public static ArtistaResponseDTO valueOf(Artista artista){
        return new ArtistaResponseDTO(artista.getId(), artista.getNome(), artista.getDescricao());
    }
}
