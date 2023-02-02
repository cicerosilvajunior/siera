package br.edmtool.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.classification.NaiveBayesClassifier;
import br.edmtool.model.Classification;
import br.edmtool.model.ClassificationInstance;
import br.edmtool.service.ClassificationRegistration;
import br.edmtool.utils.Configuration;
import br.edmtool.utils.CrossValidationMethod;
import br.edmtool.utils.OsCheck;
import br.edmtool.utils.OsCheck.OSType;
import br.edmtool.utils.YamlConfigRunner;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Model
@ViewScoped
@Named
public class ClassificationController {

	private Logger logger = LogManager.getRootLogger();

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClassificationRegistration classificacaoRegistration;

	@Produces
	@Named
	private Classification newClassificacao;

	private CrossValidationMethod metodoSelecionado;

	private String valorSelecionado;

	public List<String> getValues() {
		return Arrays.asList("Year 2000", "Year 2010", "Year 2020");
	}

	public List<CrossValidationMethod> getMetodosValidacaoDesempenho() {
		List<CrossValidationMethod> listCrossValidMethod = new ArrayList<CrossValidationMethod>(2);
		listCrossValidMethod.add(new CrossValidationMethod("1", NaiveBayesClassifier.TEN_FOLD_CROSS_VALIDATION_DESC));
		listCrossValidMethod
				.add(new CrossValidationMethod("2", NaiveBayesClassifier.LEAVE_ONE_OUT_CROSS_VALIDATION_DESC));
		return listCrossValidMethod;
	}

	@PostConstruct
	public void initClassificacao() {
		newClassificacao = new Classification();
	}

	public void classifyStudents() {
		try {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			String crossValidSelected = ec.getRequestParameterMap().get("crossValidSelected");

			if (crossValidSelected == null || crossValidSelected.isEmpty()) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Antes de prosseguir, selecione o método de validação cruzada desejado.", null);
				facesContext.addMessage(null, m);

				return;
			}

			OSType operationalSystem = OsCheck.getOperatingSystemType();

			String dataDir = "";
			String configDir = "";

			logger.trace("Operational System Type :: " + OsCheck.getOperatingSystemType());

			switch (operationalSystem) {
			case Linux:
				dataDir = YamlConfigRunner.ROOT_DIR_LINUX;
				configDir = YamlConfigRunner.ROOT_DIR_LINUX + YamlConfigRunner.YAML_CONFIG_FILE;
				break;

			case Windows:
				dataDir = YamlConfigRunner.ROOT_DIR_WIN;
				configDir = YamlConfigRunner.ROOT_DIR_WIN + YamlConfigRunner.YAML_CONFIG_FILE;
				break;

			case MacOS:
				throw new Exception("MacOS distribution is not implemented yet");

			case Other:
				throw new Exception("Other's OS distribution is not implemented yet");

			default:
				throw new Exception("Operational system not detected");
			}

			Configuration config = new YamlConfigRunner().getSingletonInstance(configDir).getConfig();

			StudentMiner miner = new StudentMiner(
					dataDir.concat(config.getClassificationDataDir().concat(config.getTrainningDataFile())));

			Classification studentsClassification = miner.predictStudentsDropout(
					dataDir.concat(config.getClassificationDataDir()).concat(config.getTestDataFile()),
					Integer.valueOf(crossValidSelected));

			for (ClassificationInstance studentClassification : studentsClassification.getModelo().getInstanciaList()) {
				logger.trace("Estudante classificado no modelo:: " + studentClassification.getUserId());
			}

			classificacaoRegistration.registerAll(studentsClassification);

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Processo de classificação de alunos concluído com sucesso!", "Classificação realizada!");
			facesContext.addMessage(null, m);
		} catch (Exception e) {
			logger.error(e);
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro no processo de classificação dos alunos. Verifique o log do sistema.",
					errorMessage);
			facesContext.addMessage(null, m);
		}

	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e.getMessage() == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	public CrossValidationMethod getMetodoSelecionado() {
		return metodoSelecionado;
	}

	public void setMetodoSelecionado(CrossValidationMethod metodoSelecionado) {
		this.metodoSelecionado = metodoSelecionado;
	}

	public String getValorSelecionado() {
		return valorSelecionado;
	}

	public void setValorSelecionado(String valorSelecionado) {
		this.valorSelecionado = valorSelecionado;
	}

}
