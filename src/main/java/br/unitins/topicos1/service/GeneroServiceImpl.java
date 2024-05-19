package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.GeneroDTO;
import br.unitins.topicos1.dto.GeneroResponseDTO;
import br.unitins.topicos1.model.Genero;
import br.unitins.topicos1.repository.GeneroRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class GeneroServiceImpl implements GeneroService{

    @Inject
    GeneroRepository repository;

    @Inject
    Validator validator;

    private void validar(GeneroDTO generoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<GeneroDTO>> violations = validator.validate(generoDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    //
    public GeneroResponseDTO insert(@Valid GeneroDTO dto) throws ConstraintViolationException {
        if (!repository.findByNome(dto.nome()).equals(dto)) {
            throw new ValidationException("nome", "Genero já existe.");
        }
        validar(dto);
        Genero novoGenero = new Genero();
        novoGenero.setNome(dto.nome());

        repository.persist(novoGenero);

        return GeneroResponseDTO.valueOf(novoGenero);
    }

    @Override
    @Transactional
    public GeneroResponseDTO update(GeneroDTO dto, Long id) {

        Genero genero = repository.findById(id);
        if (genero != null){
            genero.setNome(dto.nome());
        } else
            throw new NotFoundException();

        return GeneroResponseDTO.valueOf(genero);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       Genero genero = repository.findById(id);
        if (genero == null)
            throw new NotFoundException();

        repository.delete(genero);
    
    }

    @Override
    public GeneroResponseDTO findById(Long id) {
        Genero genero = repository.findById(id);
        return genero != null ? GeneroResponseDTO.valueOf(genero) : null;
    }

    @Override
    public List<GeneroResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> GeneroResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<GeneroResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(a -> GeneroResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<GeneroResponseDTO> getAll(int page, int pageSize) {
        List<Genero> list = repository
        .findAll()
        .page(page, pageSize)
        .list();

        return list.stream().map(e -> GeneroResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return repository.count();}

    
}
