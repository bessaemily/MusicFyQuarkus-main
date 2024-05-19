package br.unitins.topicos1.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos1.dto.CompositorDTO;
import br.unitins.topicos1.dto.CompositorResponseDTO;
import br.unitins.topicos1.service.CompositorService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import br.unitins.topicos1.application.Result;

@Path("/compositores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompositorResource {

    @Inject
    CompositorService service;

    private static final Logger LOG = Logger.getLogger(CompositorResource.class);
    

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        return Response.ok(service.getAll(page, pageSize)).build();
    }
    
    @GET
    @Path("/{id}")
    public CompositorResponseDTO findById(@PathParam("id") Long id) throws NotFoundException{
        LOG.infof("Buscando gravadora por id: %d", id);
        return service.findById(id);
    }

    @GET
    @Path("/search/{nome}")
    public List<CompositorResponseDTO> findByNome(@PathParam("nome") String nome){
        LOG.infof("Buscando compositor por nome: %s", nome);
        return service.findByNome(nome);
    }

    @POST
    //@RolesAllowed({"ADMIN"})
    //@Transactional
    public Response insert(@Valid CompositorDTO dto){
        LOG.infof("Inserindo um novo compositor: %s", dto.nome());
        Result result = null;
        try {
            LOG.infof("Inserido um novo compositor");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();

        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao inserir o compositor.");
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
    public Response update(CompositorDTO dto, @PathParam("id") Long id){
        Result result = null;
        try{
            service.update(dto, id);
            LOG.infof("Atualizando o compositor: %s", dto.nome());
            return Response.status(Status.OK).build();
        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao atualizar o compositor.");
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
    //@RolesAllowed({"ADMIN"})
    //@Transactional
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException{
        try{
            service.delete(id);
            LOG.infof("Deletando o compositor: %d", id);
            return Response.status(Status.OK).build();
        } catch (IllegalArgumentException e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }
    
}
