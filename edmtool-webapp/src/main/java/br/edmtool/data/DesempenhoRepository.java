package br.edmtool.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edmtool.model.StudentPerformance;

@ApplicationScoped
public class DesempenhoRepository {

    @Inject
    private EntityManager em;

    public StudentPerformance findById(Long id) {
        return em.find(StudentPerformance.class, id);
    }

    public StudentPerformance findByEmail(String email) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentPerformance> criteria = cb.createQuery(StudentPerformance.class);
        Root<StudentPerformance> desempenhoAluno = criteria.from(StudentPerformance.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(desempenhoAluno).where(cb.equal(desempenhoAluno.get("userId"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<StudentPerformance> findAllOrderedByDate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StudentPerformance> criteria = cb.createQuery(StudentPerformance.class);
        Root<StudentPerformance> desempenhoAluno = criteria.from(StudentPerformance.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(desempenhoAluno).orderBy(cb.asc(desempenhoAluno.get("date")));
        return em.createQuery(criteria).getResultList();
    }
}
