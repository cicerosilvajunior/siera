package br.edmtool.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edmtool.model.KnowledgeAnalysis;

@RequestScoped
public class AnaliseDesempenhoListProducer {

    @Inject
    private AnaliseDesempenhoRepository analiseDesempenhoRepository;

    private List<KnowledgeAnalysis> listaAnaliseDesempenho;
    
    private KnowledgeAnalysis analiseDesempenho;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public KnowledgeAnalysis getAnaliseDesempenhoById() {
    	if (this.analiseDesempenho == null) {
        	Long id = Long.parseLong((String)FacesContext
                    .getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("id") != null ? FacesContext
                            .getCurrentInstance().getExternalContext()
                            .getRequestParameterMap().get("id") : "0");
                 //by getting the id you can get more data
                 
                 System.out.println(id);
                 
                 analiseDesempenho = analiseDesempenhoRepository.findById(id);
    	}
             
        return analiseDesempenho;
    }
    
    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<KnowledgeAnalysis> getListaAnaliseDesempenho() {
        return listaAnaliseDesempenho;
    }

    public void onDesempenhoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final KnowledgeAnalysis analiseDesempenho) {
    	retrieveListDesempenhoOrderedByDate();
    }

    @PostConstruct
    public void retrieveListDesempenhoOrderedByDate() {
        listaAnaliseDesempenho = analiseDesempenhoRepository.findAllOrderedByDate();
    }
}