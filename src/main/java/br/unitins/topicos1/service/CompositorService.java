package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CompositorDTO;
import br.unitins.topicos1.dto.CompositorResponseDTO;
import jakarta.validation.Valid;

public interface CompositorService {
        
        public CompositorResponseDTO insert(@Valid CompositorDTO dto);
    
        public CompositorResponseDTO update(@Valid CompositorDTO dto, Long id);
    
        public void delete(Long id);
    
        public CompositorResponseDTO findById(Long id);
    
        public List<CompositorResponseDTO> findByNome(String nome);
    
        public List<CompositorResponseDTO> findByAll();

        List<CompositorResponseDTO> getAll(int page, int pageSize);

        long count();
    
}