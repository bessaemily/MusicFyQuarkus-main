package br.unitins.topicos1.dto;

import java.time.LocalDate;

public record PacienteDTO(
    String cpf,
    String nome,
    LocalDate dataNascimento,
    String username,
    String senha,
    Long idNaturalidade
) { }
