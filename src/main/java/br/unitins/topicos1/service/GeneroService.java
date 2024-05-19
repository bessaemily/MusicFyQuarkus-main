package br.unitins.topicos1.service;


import java.util.List;

import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GeneroResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public interface GeneroService {
    public GeneroResponseDTO insert(@Valid GeneroDTO dto) throws ConstraintViolationException;

    public GeneroResponseDTO update(GeneroDTO dto, Long id);

    public void delete(Long id);

    public GeneroResponseDTO findById(Long id);    

    public List<GeneroResponseDTO> findByNome(String nome);    

    public List<GeneroResponseDTO> findByAll();

    List<GeneroResponseDTO> getAll(int page, int pageSize);

    long count();
}
