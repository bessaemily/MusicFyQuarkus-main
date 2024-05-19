package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Genero;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GeneroRepository implements PanacheRepository<Genero> {

    // public List<Genero> findByName(String nome) {
    //     return find("nome LIKE ?1", "%" + nome + "%").list();
    // }
    
    public PanacheQuery<Genero> findByNome(String nome){
        if (nome == null)
            return null;
        return find("nome LIKE ?1", "%"+nome+ "%", "%" + nome.toUpperCase() + "%");

       // return find("nome LIKE ?1", "%"+nome+ "%").list();
    }
    
}
