package br.edmtool.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.edmtool.model.Classification;

@RequestScoped
public class ClassificacaoListProducer {

    @Inject
    private ClassificacaoRepository classificacaoRepository;

    private List<Classification> classificacoes;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Classification> getClassificacoes() {
        return classificacoes;
    }

    public void onClassificacaoListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Classification classificacao) {
        retrieveAllClassificacoesOrderedByDate();
    }

    @PostConstruct
    public void retrieveAllClassificacoesOrderedByDate() {
        classificacoes = classificacaoRepository.findAllOrderedByDate();
    }
}