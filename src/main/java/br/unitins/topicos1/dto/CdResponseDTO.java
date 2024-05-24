package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Cd;

public record CdResponseDTO (
    Long id,
    String nome,
    Double preco,
    String nomeImagem
) {

    public static CdResponseDTO valueOf(Cd cd) {
        return new CdResponseDTO(
            cd.getId(), 
            cd.getNome(),
            cd.getPreco(),
            cd.getNomeImagem()
        );
    }

}
