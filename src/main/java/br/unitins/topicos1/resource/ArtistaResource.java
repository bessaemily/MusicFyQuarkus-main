package br.unitins.topicos1.resource;
import java.util.List;

import org.jboss.logging.Logger;
import br.unitins.topicos1.dto.ArtistaDTO;
import br.unitins.topicos1.dto.ArtistaResponseDTO;
import br.unitins.topicos1.service.ArtistaService;
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

    
@Path("/artistas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistaResource {

    @Inject
    ArtistaService service;
    
    private static final Logger LOG = Logger.getLogger(ArtistaResource.class);
    private Result result;

    @POST
    //@Transactional
    public Response insert(@Valid ArtistaDTO dto){
        LOG.infof("Inserindo um novo artista: %s", dto.nome());
        try {
            LOG.infof("Inserido o novo artista");
            return Response.status(Status.CREATED).entity(service.insert(dto)).build();

        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao inserir o artista.");
            LOG.debug(e.getMessage());
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    //@Transactional
    @Path("/{id}")   
    public Response update(ArtistaDTO dto, @PathParam("id") Long id){
        try{
            service.update(dto, id);
            LOG.infof("Atualizado o artista: %s", dto.nome());
            return Response.status(Status.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            LOG.infof("Erro ao atualizar o artista.");
            result = new Result(e.getConstraintViolations());
            return Response.status(Status.BAD_REQUEST).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), "404", false) ;
        }
         return Response.status(Status.NOT_FOUND).entity(result).build();
    }


    @DELETE
    //@Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) throws IllegalArgumentException{
        try{
            service.delete(id);
            LOG.infof("Deletado o artista: %d", id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            return Response.status(Status.NOT_FOUND).build();
        }
        
    }

    @GET
    //@RolesAllowed("Cliente")
    public Response findAll(
                @QueryParam("page") @DefaultValue("0") int page,
                @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        return Response.ok(service.getAll(page, pageSize)).build();
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) throws NotFoundException{
        LOG.infof("Buscando artista por id: %d", id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByName(@PathParam("nome") String nome) {
        List<ArtistaResponseDTO> artistas = service.findByNome(nome);
        if (artistas.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.status(Status.OK).entity(artistas).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }


}
