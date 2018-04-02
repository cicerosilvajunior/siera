package br.edmtool.bkt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.model.StudentPerformance;
import br.edmtool.utils.CSVUtil;
import br.edmtool.utils.Messages;
import br.edmtool.utils.YamlConfigRunner;

/**
 * 
 * @author Cicero Silva Jr
 * dados para testes dispon�veis em ..\mestrado_learning_analytics\la_tool\data\bkt\curso_oo\bkt-bf\Units_1-6\data\csv_bkt_bf_tratado
 */
public class BKTBruteForceWrapper extends computeKTparamsAll implements StudentKnowledgeEvaluator{
	private Logger logger = LogManager.getRootLogger();
	
	private String algorithmName;
	private String algorithmAuthors;
	private String algorithmReference; 
	
	public BKTBruteForceWrapper() {
		this.algorithmName = "BKT Brute Force";
		this.algorithmAuthors = "Baker, R. S. J. D., Corbett, A. T., & Aleven, V.";
		this.algorithmReference = "Improving contextual models of guessing and slipping with a truncated training set. Human-Computer Interaction Institute, p. 17, 2008.";
	}
	
	public List<String[]> computeBKT(String inFile) {
		List<String[]> listStudentBKT = super.computelzerot(inFile);
		return listStudentBKT;
	}
	
	/**
	 * @param rootPath Caminho do diretorio raiz da aplicação  
	 * @param configPath Caminho do arquivo de configuração
	 * @return
	 */
	public List<StudentPerformance> evaluateStudentPerformance(String bktDataFile) {
		List<String[]> listStudentBKT = this.computeBKT(bktDataFile);
		List<StudentPerformance> studentsBKTList = new ArrayList<StudentPerformance>(); 
		
		List<String[]> dadosCSV = new CSVUtil().retrieveDataFromCSV(bktDataFile);
		
		for (String[] strings : listStudentBKT) {
			StudentPerformance studentBKT = new StudentPerformance();
			studentBKT.setUserId(strings[0]);
			studentBKT.setLesson(strings[1]);
			studentBKT.setSkill(strings[2]);
			
			String[] sequenceAnswers = new String[dadosCSV.size()];
			int countSequenceAnswers = 0;
			for (int j = 0; j < dadosCSV.size(); j++) {
				String[] line = dadosCSV.get(j)[0].split(" ");
				line = line[0].split("	");//split by tab
				if(line[2].equals(strings[0])) {
					sequenceAnswers[countSequenceAnswers] = line[5];
					countSequenceAnswers++;
				} else {
					continue;
				}
					
			} 
			
			String[] studentAnswers = new String[countSequenceAnswers];
			for (int i = 0; i < countSequenceAnswers; i++) {
				studentAnswers[i] = sequenceAnswers[i];
			}
			
			studentBKT.setStudentAnswers(Arrays.toString(studentAnswers));
			
			double[] bktParams = new double[4];
			bktParams[0] = Double.parseDouble(strings[3]);
			bktParams[1] = Double.parseDouble(strings[4]);
			bktParams[2] = Double.parseDouble(strings[5]);
			bktParams[3] = Double.parseDouble(strings[6]);
			
			studentBKT.setEvaluationParams(bktParams);
			studentBKT.setBktProbString(Arrays.toString(bktParams));
			studentBKT.setBktProb(bktParams);
			
			studentBKT.setKnowledgeDiagnostic(this.calculateStudentKnowledge(bktParams, studentBKT));
			
			bktParams = null;
			studentAnswers = null;
			sequenceAnswers = null;
						
			studentsBKTList.add(studentBKT);
			
			logger.trace("BKT Student data "+studentBKT.toString());
		}
		return studentsBKTList;
	}
	
	
	protected String calculateStudentKnowledge(double[] bktParams, StudentPerformanceEvaluation studentBKT) {
		double L0 = bktParams[0];
		double G = bktParams[1];
		double S = bktParams[2];
		double T = bktParams[3];
		
		if(T > L0) { //aumentou conhecimento
			studentBKT.setItSeensItKnows(true);
			if(G > S) { //provavel chute 
				return Messages.ALTO_APROVEITAMENTO_GUESS;
			} else if (G < S) { //provavel falta de atencao
				return Messages.ALTO_APROVEITAMENTO_SLIP;
			} else { //cenario de incerteza (G == S)
				return Messages.ALTO_APROVEITAMENTO_UNKONW;
			}
		} else if (L0 > T) { //diminuiu conhecimento
			studentBKT.setItSeensItKnows(false);
			if(G > S) { //provavel chute 
				return Messages.PERDA_APROVEITAMENTO_GUESS;
			} else if (G < S) { //provavel falta de atencao
				return Messages.PERDA_APROVEITAMENTO_SLIP;
			} else { //cenario de incerteza (G == S)
				return Messages.PERDA_APROVEITAMENTO_UNKONW;
			}
		} else { //nao aumentou conhecimento
			studentBKT.setItSeensItKnows(false);
			if(G > S) { //provavel chute 
				return Messages.APROVEITAMENTO_INALTERADO_GUESS;
			} else if (G < S) { //provavel falta de atencao
				return Messages.APROVEITAMENTO_INALTERADO_SLIP; 
			} else { //cenario de incerteza (G == S)
				return Messages.APROVEITAMENTO_INALTERADO_UNKONW;
			}
		}
		
	}

	@Override
	public String getAlgorithmName() {
		return this.algorithmName;
	}

	@Override
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	@Override
	public String getAlgorithmAuthors() {
		return this.algorithmAuthors;
	}

	@Override
	public void setAlgorithmAuthors(String algorithmAuthors) {
		this.algorithmAuthors = algorithmAuthors;
	}

	@Override
	public String getAlgorithmReference() {
		return this.algorithmReference;
	}

	@Override
	public void setAlgorithmReference(String algorithmReference) {
		this.algorithmReference = algorithmReference;
	}
	

}
