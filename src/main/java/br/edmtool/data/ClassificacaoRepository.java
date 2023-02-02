package br.edmtool.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edmtool.model.Classification;

@ApplicationScoped
public class ClassificacaoRepository {

    @Inject
    private EntityManager em;

    public Classification findById(Long id) {
        return em.find(Classification.class, id);
    }

    public Classification findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Classification> criteria = cb.createQuery(Classification.class);
        Root<Classification> classificacao = criteria.from(Classification.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(classificacao).where(cb.equal(classificacao.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Classification> findAllOrderedByDate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Classification> criteria = cb.createQuery(Classification.class);
        Root<Classification> classificacao = criteria.from(Classification.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(classificacao).orderBy(cb.asc(classificacao.get("date")));
        return em.createQuery(criteria).getResultList();
    }
}
