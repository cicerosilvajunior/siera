package br.edmtool.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.model.Classification;

@RequestScoped
public class ModeloListProducer  {
	private Logger logger = LogManager.getRootLogger();
	
    @Inject
    private ClassificacaoRepository classificacaoRepository;

    private Classification classificacaoModelo;
    
    private Long id;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public Classification getModeloClassificacao() {
    	if(this.classificacaoModelo == null) {
    		Long id = Long.parseLong((String)FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("id") != null ? FacesContext
                            .getCurrentInstance().getExternalContext()
                            .getRequestParameterMap().get("id") : "0");
                 //by getting the id you can get more data
                 
                 logger.trace("Id Modelo Classificacao "+id);
                 
                 classificacaoModelo = classificacaoRepository.findById(id);	
    	}
    	
        return classificacaoModelo;
    }

    public void onClassificacaoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Classification classificacao) {
    	logger.trace("On Classificacao List changed");
    }

    @PostConstruct
    public void retrieveAllClassificacoesOrderedByDate() {
    	logger.trace("Post Construction method");
    }

}
