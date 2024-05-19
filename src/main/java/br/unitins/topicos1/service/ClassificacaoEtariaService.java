package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ClassificacaoEtariaDTO;
import br.unitins.topicos1.dto.ClassificacaoEtariaResponseDTO;
import jakarta.validation.Valid;

public interface ClassificacaoEtariaService {
    
    public ClassificacaoEtariaResponseDTO insert(@Valid ClassificacaoEtariaDTO dto);
        
    public ClassificacaoEtariaResponseDTO update(ClassificacaoEtariaDTO dto, Long id);
        
    public void delete(Long id);
        
    public ClassificacaoEtariaResponseDTO findById(Long id);
        
    public ClassificacaoEtariaResponseDTO findByFaixaEtaria(String faixaEtaria);

    public ClassificacaoEtariaResponseDTO findByDescricao(String descricao);
        
    public List<ClassificacaoEtariaResponseDTO> findByAll();  
}
