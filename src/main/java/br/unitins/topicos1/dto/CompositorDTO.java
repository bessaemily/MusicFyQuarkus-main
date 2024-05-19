package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record CompositorDTO(

    @NotBlank(message = "O nome deve ser informado")
    String nome,

    @NotBlank(message = "A descrição deve ser informado")
    String descricao
) {
    
}
