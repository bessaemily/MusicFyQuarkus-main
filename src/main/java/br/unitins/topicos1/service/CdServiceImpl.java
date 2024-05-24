package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CdDTO;
import br.unitins.topicos1.dto.CdResponseDTO;
import br.unitins.topicos1.model.Cd;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CdServiceImpl implements CdService {

    @Inject
    br.unitins.topicos1.repository.CdRepository cdRepository;

    @Inject
    Validator validator;

    @Override
    public List<CdResponseDTO> getAll(int page, int pageSize) {
        List<Cd> list = cdRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> CdResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    

    @Override
    public CdResponseDTO findById(Long id) {
        Cd cd = cdRepository.findById(id);
        if (cd == null)
            throw new NotFoundException("Cd não encontrado.");
        return CdResponseDTO.valueOf(cd);
    }

    @Override
    @Transactional
    public CdResponseDTO create(@Valid CdDTO cdDTO) throws ConstraintViolationException {
        validar(cdDTO);

        Cd entity = new Cd();
        entity.setNome(cdDTO.nome());
        entity.setPreco(cdDTO.preco());

        cdRepository.persist(entity);

        return CdResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CdResponseDTO update(Long id, CdDTO cdDTO) throws ConstraintViolationException{
        validar(cdDTO);
   
        Cd entity = cdRepository.findById(id);

        entity.setNome(cdDTO.nome());
        entity.setPreco(cdDTO.preco());

        return CdResponseDTO.valueOf(entity);
    }

    private void validar(CdDTO cdDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CdDTO>> violations = validator.validate(cdDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cdRepository.deleteById(id);
    }

    @Override
    public List<CdResponseDTO> findByNome(String nome) {
        List<Cd> list = cdRepository.findByNome(nome).list();
        return list.stream().map(e -> CdResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cdRepository.count();
    }

}