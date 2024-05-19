package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Gravadora;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GravadoraRepository implements PanacheRepository<Gravadora> {

    public PanacheQuery<Gravadora> findByNome(String nome){
        if (nome == null)
            return null;
        return find("nome LIKE ?1", "%"+nome+ "%", "%" + nome.toUpperCase() + "%");

       // return find("nome LIKE ?1", "%"+nome+ "%").list();
    }
}
