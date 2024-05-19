package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.ClassificacaoEtaria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClassificacaoEtariaRepository implements PanacheRepository<ClassificacaoEtaria> {
        
        public ClassificacaoEtaria findById(Long id) {
            return find("id", id).firstResult();
        }
    
        public ClassificacaoEtaria findByFaixaEtaria(String faixaEtaria) {
            return find("faixaEtaria", faixaEtaria).firstResult();
        }

        public ClassificacaoEtaria findByDescricao(String descricao) {
            return find("descricao", descricao).firstResult();
        }
}
