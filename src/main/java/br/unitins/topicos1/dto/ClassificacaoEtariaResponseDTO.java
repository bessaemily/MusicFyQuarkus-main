package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ClassificacaoEtaria;

public record ClassificacaoEtariaResponseDTO(
    Long id,
    String faixaEtaria,
    String descricao
) {
    public static ClassificacaoEtariaResponseDTO valueOf(ClassificacaoEtaria classificacaoEtaria){
        return new ClassificacaoEtariaResponseDTO (classificacaoEtaria.getId(), classificacaoEtaria.getFaixaEtaria(), classificacaoEtaria.getDescricao());
    }
}