package br.edmtool.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edmtool.model.KnowledgeAnalysis;

@ApplicationScoped
public class AnaliseDesempenhoRepository {

    @Inject
    private EntityManager em;

    public KnowledgeAnalysis findById(Long id) {
        return em.find(KnowledgeAnalysis.class, id);
    }

    public KnowledgeAnalysis findByAlgorithm(String algorithm) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<KnowledgeAnalysis> criteria = cb.createQuery(KnowledgeAnalysis.class);
        Root<KnowledgeAnalysis> analiseDesempenho = criteria.from(KnowledgeAnalysis.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(analiseDesempenho).where(cb.equal(analiseDesempenho.get("algorithm"), algorithm));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<KnowledgeAnalysis> findAllOrderedByDate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<KnowledgeAnalysis> criteria = cb.createQuery(KnowledgeAnalysis.class);
        Root<KnowledgeAnalysis> analiseDesempenho = criteria.from(KnowledgeAnalysis.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(analiseDesempenho).orderBy(cb.asc(analiseDesempenho.get("date")));
        return em.createQuery(criteria).getResultList();
    }
}