package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record ClassificacaoEtariaDTO(

    @NotBlank(message = "A faixa etária deve ser informada")
    String faixaEtaria,

    @NotBlank(message = "A descrição deve ser informada")
    String descricao
) {
}