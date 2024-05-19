package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.AuthUsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.HashServiceImpl;
import br.unitins.topicos1.service.JwtService;
import br.unitins.topicos1.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    PacienteService pacienteService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        UsuarioResponseDTO usuario = null;
        if (authDTO.perfil() == 1)
            usuario = pacienteService.findByUsernameAndSenha(authDTO.login(), hash);
        else if (authDTO.perfil() == 2) {
            // busca de usuario psicologo
        } else {
            return Response.status(Status.NOT_FOUND)
                .entity("Username ou senha inválido").build();
        }
        return Response.ok(usuario)
                .header("Authorization", jwtService.generateJwt(usuario))
                .build();

    }

    public static void main(String[] args) {
        HashService h = new HashServiceImpl();
        String hash = h.getHashSenha("111");
        System.out.println(hash);
    }

}
