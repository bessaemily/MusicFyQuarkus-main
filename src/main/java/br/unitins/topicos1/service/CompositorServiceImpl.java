package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.CompositorDTO;
import br.unitins.topicos1.dto.CompositorResponseDTO;
import br.unitins.topicos1.model.Compositor;
import br.unitins.topicos1.repository.CompositorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;


@ApplicationScoped
public class CompositorServiceImpl implements CompositorService{

    @Inject
    CompositorRepository repository;

    @Inject
    Validator validator;

    private void validar(CompositorDTO compositorDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CompositorDTO>> violations = validator.validate(compositorDTO);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    @Override
    @Transactional
    public CompositorResponseDTO insert(@Valid CompositorDTO dto) {
        validar(dto);
        Compositor novoCompositor = new Compositor();
        novoCompositor.setNome(dto.nome());
        novoCompositor.setDescricao(dto.descricao());

        repository.persist(novoCompositor);

        return CompositorResponseDTO.valueOf(novoCompositor);
    }

    @Override
    @Transactional
    public CompositorResponseDTO update(@Valid CompositorDTO dto, Long id) {
        Compositor compositor = repository.findById(id);
        if (compositor != null){
            compositor.setNome(dto.nome());
            compositor.setDescricao(dto.descricao());
        } else
            throw new NotFoundException();

        return CompositorResponseDTO.valueOf(compositor);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        Compositor compositor = repository.findById(id);
        if (compositor != null)
            repository.delete(compositor);
        else
            throw new NotFoundException();
        }

    @Override
    public CompositorResponseDTO findById(Long id) {
        Compositor compositor = repository.findById(id);
        return compositor != null ? CompositorResponseDTO.valueOf(compositor) : null;
    }

    @Override
    public List<CompositorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(a -> CompositorResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<CompositorResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(a -> CompositorResponseDTO.valueOf(a)).toList();
    }

    @Override
    public List<CompositorResponseDTO> getAll(int page, int pageSize) {
        List<Compositor> list = repository
        .findAll()
        .page(page, pageSize)
        .list();

        return list.stream().map(e -> CompositorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return repository.count();
    } 
    
}
