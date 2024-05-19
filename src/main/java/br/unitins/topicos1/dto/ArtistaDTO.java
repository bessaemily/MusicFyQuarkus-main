package br.unitins.topicos1.dto;


import jakarta.validation.constraints.NotBlank;

public record ArtistaDTO(

    @NotBlank(message = "O nome deve ser informado")
    String nome,

    @NotBlank(message = "A descricao ser informado")
    String descricao
) {
    
}

