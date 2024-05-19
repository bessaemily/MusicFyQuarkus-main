package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pessoa;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String username
) { 
    public static UsuarioResponseDTO valueOf(Pessoa pessoa){
        if (pessoa == null || pessoa.getUsuario() == null)
            return null;

        return new UsuarioResponseDTO(
            pessoa.getId(), 
            pessoa.getNome(),
            pessoa.getUsuario().getUsername()
        );
    }
}
