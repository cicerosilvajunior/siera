package br.edmtool.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.model.StudentPerformance;

@Stateless
public class StudentPerformanceRegistration {

	private Logger logger = LogManager.getRootLogger();

    @Inject
    private EntityManager em;

    @Inject
    private Event<StudentPerformance> desempenhoEventSrc;

    public void register(StudentPerformance desempenhoAluno) throws Exception {
    	logger.info("Registering " + desempenhoAluno.getUserId());
        em.persist(desempenhoAluno);
        desempenhoEventSrc.fire(desempenhoAluno);
    }
    
    
    public void registerAll(StudentPerformance desempenhoAluno) throws Exception {
    	logger.info("Registering " + desempenhoAluno.getUserId());
        
        em.persist(desempenhoAluno);
        
        desempenhoEventSrc.fire(desempenhoAluno);
    }
    
    public void persistPerformanceList(List<StudentPerformance> studentPerformanceEvaluationList) {
		try {
			for (int i = 0; i < studentPerformanceEvaluationList.size(); i++){
				StudentPerformance studentBKT = studentPerformanceEvaluationList.get(i);
				studentBKT = em.merge(studentBKT);
				em.persist(studentBKT);
		        
		        desempenhoEventSrc.fire(studentBKT);

			}

		} catch (Exception e) {
			logger.error(e);
		}
	}
    
    public List<StudentPerformance> listStudentPerformance() {
		Query q = em.createQuery("select s from StudentPerformance s");

		return (List<StudentPerformance>) q.getResultList();
	}
}
