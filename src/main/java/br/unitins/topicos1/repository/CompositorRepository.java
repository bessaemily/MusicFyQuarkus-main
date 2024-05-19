package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Compositor;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CompositorRepository implements PanacheRepository<Compositor>{
    
    public PanacheQuery<Compositor> findByNome(String nome){
        if (nome == null)
            return null;
        return find("nome LIKE ?1", "%"+nome+ "%", "%" + nome.toUpperCase() + "%");

       // return find("nome LIKE ?1", "%"+nome+ "%").list();
    }
}
