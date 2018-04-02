package br.edmtool.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.model.KnowledgeAnalysis;
import br.edmtool.model.StudentPerformance;

@Stateless
public class KnowledgeAnalysisRegistration {

	private Logger logger = LogManager.getRootLogger();

    @Inject
    private EntityManager em;

    @Inject
    private Event<KnowledgeAnalysis> analiseDesempenhoEventSrc;

    public void register(KnowledgeAnalysis analiseDesempenho) throws Exception {
        logger.info("Registering " + analiseDesempenho.getAlgorithm());
        em.persist(analiseDesempenho);
        analiseDesempenhoEventSrc.fire(analiseDesempenho);
    }
    
    
    public void registerAll(KnowledgeAnalysis analiseDesempenho) throws Exception {
    	logger.info("Registering " + analiseDesempenho.getAlgorithm());
        
        em.persist(analiseDesempenho);
        
        for (StudentPerformance studentPerformanceInstance : analiseDesempenho.getStudentPerformanceList()) {
        	em.persist(studentPerformanceInstance);
		}
        
        analiseDesempenhoEventSrc.fire(analiseDesempenho);
    }
    
    public void persistPerformanceList(KnowledgeAnalysis analiseDesempenho) {
		try {
			for (int i = 0; i < analiseDesempenho.getStudentPerformanceList().size(); i++){
				StudentPerformance studentPerformanceInstance = analiseDesempenho.getStudentPerformanceList().get(i);
				studentPerformanceInstance = em.merge(studentPerformanceInstance);
				em.persist(studentPerformanceInstance);
		        
				analiseDesempenhoEventSrc.fire(analiseDesempenho);
				
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}
    
    public List<KnowledgeAnalysis> listStudentPerformance() {
		Query q = em.createQuery("select a from KnowledgeAnalysis a");

		return (List<KnowledgeAnalysis>) q.getResultList();
	}
}
