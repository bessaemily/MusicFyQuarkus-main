package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Paciente;


public record PacienteResponseDTO (
    Long id,
    String cpf,
    String nome,
    LocalDate dataNascimento,
    String username,
    String senha,
    CidadeResponseDTO naturalidade
) {

    public static PacienteResponseDTO valueOf(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getId(), 
            paciente.getPessoa().getCpf(),
            paciente.getPessoa().getNome(),
            paciente.getPessoa().getDataNascimento(),
            (paciente.getPessoa().getUsuario() == null ? null : paciente.getPessoa().getUsuario().getUsername()),
            (paciente.getPessoa().getUsuario() == null ? null : paciente.getPessoa().getUsuario().getSenha()),
            CidadeResponseDTO.valueOf(paciente.getNaturalidade())
        );
    }

}