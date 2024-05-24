package br.unitins.topicos1.resource;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.CdDTO;
import br.unitins.topicos1.dto.CdResponseDTO;
import br.unitins.topicos1.form.CdImageForm;
import br.unitins.topicos1.service.CdService;
import br.unitins.topicos1.service.FileService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/cds")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CdResource {

    @Inject
    CdService service;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(CdResource.class);

    @POST
    public Response create(CdDTO dto) {
        CdResponseDTO retorno = service.create(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(CdDTO dto, @PathParam("id") Long id) {
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll(
                @QueryParam("page") @DefaultValue("0") int page,
                @QueryParam("pageSize") @DefaultValue("100") int pageSize) {

        return Response.ok(service.getAll(page, pageSize)).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }
    
    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return service.count();
    }

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm CdImageForm form) {
        LOG.info("nome imagem: "+form.getNomeImagem());
        System.out.println("nome imagem: "+form.getNomeImagem());
        
        fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        System.out.println(nomeImagem);
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

}