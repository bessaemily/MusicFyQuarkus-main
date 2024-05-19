package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ClassificacaoEtariaDTO;
import br.unitins.topicos1.dto.ClassificacaoEtariaResponseDTO;
import br.unitins.topicos1.model.ClassificacaoEtaria;
import br.unitins.topicos1.repository.ClassificacaoEtariaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClassificacaoEtariaServiceImpl implements ClassificacaoEtariaService {

    @Inject
    private ClassificacaoEtariaRepository repository;

    @Inject
    Validator validator;

    private void validar(ClassificacaoEtariaDTO classificacaoetariaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClassificacaoEtariaDTO>> violations = validator.validate(classificacaoetariaDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public ClassificacaoEtariaResponseDTO insert(ClassificacaoEtariaDTO dto) {
        validar(dto);
        ClassificacaoEtaria novaClassificacaoEtaria = new ClassificacaoEtaria();
        novaClassificacaoEtaria.setFaixaEtaria(dto.faixaEtaria());
        novaClassificacaoEtaria.setDescricao(dto.descricao());

        repository.persist(novaClassificacaoEtaria);

        return ClassificacaoEtariaResponseDTO.valueOf(novaClassificacaoEtaria);
    }

    @Override
    @Transactional
    public ClassificacaoEtariaResponseDTO update(ClassificacaoEtariaDTO dto, Long id) {
        ClassificacaoEtaria classificacaoEtaria = repository.findById(id);
        if (classificacaoEtaria != null){
            classificacaoEtaria.setFaixaEtaria(dto.faixaEtaria());
            classificacaoEtaria.setDescricao(dto.descricao());
        } else
            throw new NotFoundException();

        return ClassificacaoEtariaResponseDTO.valueOf(classificacaoEtaria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        ClassificacaoEtaria classificacaoEtaria = repository.findById(id);
        if (classificacaoEtaria != null)
            repository.delete(classificacaoEtaria);
        else
            throw new NotFoundException();
    }

    @Override
    public ClassificacaoEtariaResponseDTO findById(Long id) {
        ClassificacaoEtaria classificacaoEtaria = repository.findById(id);
        return classificacaoEtaria != null ? ClassificacaoEtariaResponseDTO.valueOf(classificacaoEtaria) : null;
    }

    @Override
    public ClassificacaoEtariaResponseDTO findByFaixaEtaria(String faixaEtaria) {
        ClassificacaoEtaria classificacaoEtaria = repository.findByFaixaEtaria(faixaEtaria);
        if (classificacaoEtaria == null)
            throw new NotFoundException();

        return ClassificacaoEtariaResponseDTO.valueOf(classificacaoEtaria);
    }

    @Override
    public ClassificacaoEtariaResponseDTO findByDescricao(String descricao) {
        ClassificacaoEtaria classificacaoEtaria = repository.findByDescricao(descricao);
        if (classificacaoEtaria == null)
            throw new NotFoundException();

        return ClassificacaoEtariaResponseDTO.valueOf(classificacaoEtaria);
    }

    @Override
    public List<ClassificacaoEtariaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(ClassificacaoEtariaResponseDTO::valueOf).collect(Collectors.toList());
    }
    
}
