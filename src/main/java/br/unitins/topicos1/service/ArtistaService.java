package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public interface ArtistaService {

    List<ArtistaResponseDTO> getAll(int page, int pageSize);

    public ArtistaResponseDTO insert(@Valid ArtistaDTO dto) throws ConstraintViolationException;

    public ArtistaResponseDTO update(ArtistaDTO dto, Long id);

    public void delete(Long id);

    public ArtistaResponseDTO findById(Long id);    

    public List<ArtistaResponseDTO> findByNome(String nome);  

    public List<ArtistaResponseDTO> findByAll();

    long count();


}