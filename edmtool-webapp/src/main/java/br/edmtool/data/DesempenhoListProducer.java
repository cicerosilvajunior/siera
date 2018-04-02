package br.edmtool.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.edmtool.model.StudentPerformance;

@RequestScoped
public class DesempenhoListProducer {

    @Inject
    private DesempenhoRepository desempenhoRepository;

    private List<StudentPerformance> listaDesempenho;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<StudentPerformance> getListaDesempenho() {
        return listaDesempenho;
    }

    public void onDesempenhoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final StudentPerformance desempenhoAluno) {
    	retrieveListDesempenhoOrderedByDate();
    }

    @PostConstruct
    public void retrieveListDesempenhoOrderedByDate() {
        listaDesempenho = desempenhoRepository.findAllOrderedByDate();
    }
}
