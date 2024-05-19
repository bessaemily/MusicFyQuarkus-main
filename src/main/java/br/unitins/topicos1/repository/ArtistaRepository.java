package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Artista;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;


@ApplicationScoped
public class ArtistaRepository implements PanacheRepository<Artista>{

    public PanacheQuery<Artista> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }
}
