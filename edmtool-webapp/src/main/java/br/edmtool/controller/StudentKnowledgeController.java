package br.edmtool.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.bkt.BKTBruteForceWrapper;
import br.edmtool.model.KnowledgeAnalysis;
import br.edmtool.model.StudentPerformance;
import br.edmtool.service.KnowledgeAnalysisRegistration;
import br.edmtool.utils.Configuration;
import br.edmtool.utils.OsCheck;
import br.edmtool.utils.OsCheck.OSType;
import br.edmtool.utils.YamlConfigRunner;

@Model
@ViewScoped
@Named
public class StudentKnowledgeController {
	
	private Logger logger = LogManager.getRootLogger();
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private KnowledgeAnalysisRegistration analiseDesempenhoRegistration;

	@Produces
	@Named
	private KnowledgeAnalysis analiseDesempenho;

	@PostConstruct
	public void initAnaliseDesempenho() {
		analiseDesempenho = new KnowledgeAnalysis();
	}


	public void evaluateStudentPerformance() {
		try {
			OSType operationalSystem = OsCheck.getOperatingSystemType();
			
			String dataDir = "";
			String configDir = "";
			
			System.out.println(OsCheck.getOperatingSystemType());
			
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
			
			BKTBruteForceWrapper bktWrapper = new BKTBruteForceWrapper();

			List<StudentPerformance> studentBKTList = bktWrapper.evaluateStudentPerformance(dataDir.concat(config.getBktDataDir()).concat(config.getBktDataFile()));
			
			Date dataAnalise = new Date();
			
			analiseDesempenho.setAlgorithm(bktWrapper.getAlgorithmName());
			analiseDesempenho.setDataset(config.getBktDataFile());
			analiseDesempenho.setInstancesTotal(studentBKTList.size());
			analiseDesempenho.setDate(dataAnalise);
			
			for (StudentPerformance studentBKT : studentBKTList) {
				logger.debug("Student data "+ studentBKT);
				studentBKT.setAnaliseDesempenho(analiseDesempenho);
				studentBKT.setDate(dataAnalise);
			}

			analiseDesempenho.setStudentPerformanceList(studentBKTList);

			analiseDesempenhoRegistration.registerAll(analiseDesempenho);

			logger.trace(studentBKTList.size() + " BKT students saved");

			List<KnowledgeAnalysis> studentsList = analiseDesempenhoRegistration.listStudentPerformance();

			logger.trace(studentsList.size() + " students saved");

			for (KnowledgeAnalysis studentBKT : studentsList) {
				logger.trace("BKT Student data "+ studentBKT);
			}

			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Processo de análise de desempenho de alunos concluído com sucesso!", "Análise de desempenho realizada.");
			logger.info("Processo de análise de desempenho de alunos concluído com sucesso! Análise de desempenho realizada.");
			
			facesContext.addMessage(null, m);
			analiseDesempenho = null;
		} catch (Exception e) {
			String errorMessage = getRootErrorMessage(e);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage,
					"Falha no processo de análise de desempenho de alunos");
			facesContext.addMessage(null, m);
			logger.error(e);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Registration failed. See server log for more information";
		if (e == null) {
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

}
