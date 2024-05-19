package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.PacienteDTO;
import br.unitins.topicos1.dto.PacienteResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Paciente;
import br.unitins.topicos1.model.Pessoa;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.CidadeRepository;
import br.unitins.topicos1.repository.PacienteRepository;
import br.unitins.topicos1.repository.PessoaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
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
public class PacienteServiceImpl implements PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Validator validator;

    @Override
    public List<PacienteResponseDTO> getAll(int page, int pageSize) {
        List<Paciente> list = pacienteRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> PacienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    

    @Override
    public PacienteResponseDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id);
        if (paciente == null)
            throw new NotFoundException("Paciente não encontrado.");
        return PacienteResponseDTO.valueOf(paciente);
    }

    @Override
    @Transactional
    public PacienteResponseDTO create(@Valid PacienteDTO pacienteDTO) throws ConstraintViolationException {
        validarDados(pacienteDTO);
       
        Usuario usuario = new Usuario();
        usuario.setUsername(pacienteDTO.username());
        usuario.setSenha(pacienteDTO.senha());
        usuarioRepository.persist(usuario);
        
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(pacienteDTO.cpf());
        pessoa.setDataNascimento(pacienteDTO.dataNascimento());
        pessoa.setNome(pacienteDTO.nome());
        pessoa.setUsuario(usuario);
        pessoaRepository.persist(pessoa);

        Paciente paciente = new Paciente();
        paciente.setNaturalidade(cidadeRepository.findById(pacienteDTO.idNaturalidade()));
        paciente.setPessoa(pessoa);
        pacienteRepository.persist(paciente);
        
        return PacienteResponseDTO.valueOf(paciente);
    }

    private void validarDados(PacienteDTO pacienteDTO) {
        if (usuarioRepository.findByUsername(pacienteDTO.username()).firstResult() != null) 
            throw new ValidationException("username", "Username indisponível.");
        
        if (pessoaRepository.findByCpf(pacienteDTO.cpf()).firstResult() != null) 
            throw new ValidationException("cpf", "Ja existe este cpf cadastrado.");

    }

    @Override
    @Transactional
    public PacienteResponseDTO update(Long id, PacienteDTO pacienteDTO) throws ConstraintViolationException{
        validar(pacienteDTO);
   
        Paciente entity = pacienteRepository.findById(id);

        return PacienteResponseDTO.valueOf(entity);
    }

    private void validar(PacienteDTO pacienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PacienteDTO>> violations = validator.validate(pacienteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public List<PacienteResponseDTO> findByNome(String nome) {
        List<Paciente> list = pacienteRepository.findByNome(nome).list();
        return list.stream().map(e -> PacienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return pacienteRepository.count();
    }

    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        Paciente paciente = pacienteRepository.findByUsernameAndSenha(username, senha).firstResult();

        if (paciente == null)
            throw new ValidationException(username, "Username ou senha inválido");
        return UsuarioResponseDTO.valueOf(paciente.getPessoa());
    }

}