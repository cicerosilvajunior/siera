package br.edmtool.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.model.Classification;
import br.edmtool.model.ClassificationInstance;

@Stateless
public class ClassificationRegistration {

	private Logger logger = LogManager.getRootLogger();

    @Inject
    private EntityManager em;

    @Inject
    private Event<Classification> classificacaoEventSrc;

    public void register(Classification classificacao) throws Exception {
        logger.info("Registering " + classificacao.getScheme());
        em.persist(classificacao);
        classificacaoEventSrc.fire(classificacao);
    }
    
    
    public void registerAll(Classification classificacao) throws Exception {
    	logger.info("Registering " + classificacao.getScheme());
        
        for (ClassificationInstance classificacaoInstancia : classificacao.getModelo().getInstanciaList()) {
        	em.persist(classificacaoInstancia);
		}
        
        em.persist(classificacao.getModelo());
        
        em.persist(classificacao);
        
        classificacaoEventSrc.fire(classificacao);
    }
}
