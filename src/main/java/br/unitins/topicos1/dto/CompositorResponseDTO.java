package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Compositor;

public record CompositorResponseDTO (
    
    Long id,
    String nome,
    String descricao
) {
    public static CompositorResponseDTO valueOf(Compositor compositor){
        return new CompositorResponseDTO (compositor.getId(), compositor.getNome(), compositor.getDescricao());
    }
}
