package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.CdDTO;
import br.unitins.topicos1.dto.CdResponseDTO;
import jakarta.validation.Valid;

public interface CdService {

        // recursos basicos
        List<CdResponseDTO> getAll(int page, int pageSize);

        CdResponseDTO findById(Long id);

        CdResponseDTO create(@Valid CdDTO dto);
    
        CdResponseDTO update(Long id, CdDTO dto);
    
        void delete(Long id);

        List<CdResponseDTO> findByNome(String nome);
    
        long count();

}
