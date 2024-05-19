package br.unitins.topicos1.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos1.dto.ClassificacaoEtariaDTO;
import br.unitins.topicos1.dto.ClassificacaoEtariaResponseDTO;
import br.unitins.topicos1.service.ClassificacaoEtariaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.topicos1.application.Result;


@Path("/classificacaoetaria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClassificacaoEtariaResource {
    
    @Inject
    ClassificacaoEtariaService service;

    private static final Logger LOG = Logger.getLogger(ClassificacaoEtariaResource.class);

    @GET
    public Response findByAll(){
        LOG.info("Listando todas as classificações etárias.");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public ClassificacaoEtariaResponseDTO findById(@PathParam("id") Long id) throws NotFoundException{
        LOG.infof("Buscando gravadora por id: %d", id);
        return service.findById(id);
    }

    @GET
    @Path("/search/{faixaetaria}")
    public ClassificacaoEtariaResponseDTO findByFaixaEtaria(@PathParam("faixaetaria") String faixaEtaria){
        LOG.infof("Listando a classificação etária da faixa etária %s", faixaEtaria);
        return service.findByFaixaEtaria(faixaEtaria);
    }

    @GET
    @Path("/search/{descricao}")
    public ClassificacaoEtariaResponseDTO findByDescricao(@PathParam("descricao") String descricao){
        LOG.infof("Listando a classificação etária da descrição %s", descricao);
        return service.findByDescricao(descricao);
    }

    @POST
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response insert(@Valid ClassificacaoEtariaDTO dto){
        LOG.infof("Inserindo uma nova classificação etária: %s", dto.faixaEtaria());
        Result result = null;
        try {
            LOG.infof("Inserido a nova classificação etária");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();
        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao inserir a classificação etária.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @RolesAllowed({"ADMIN"})
    @Transactional
    @Path("/{id}")
    public Response update(ClassificacaoEtariaDTO dto, @PathParam("id") Long id){
        Result result = null;
        try{
            service.update(dto, id);
            LOG.infof("Atualizando a classificação etária: %s", dto.faixaEtaria());
            return Response.status(Status.OK).build();
        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao atualizar a classificação etária.");
            result = new Result(e.getConstraintViolations());
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false);  
        }
         return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    @Transactional
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException{
        try{
            service.delete(id);
            LOG.infof("Deletado o classificação etária: %d", id);
            return Response.status(Status.OK).build();
        } catch (IllegalArgumentException e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
