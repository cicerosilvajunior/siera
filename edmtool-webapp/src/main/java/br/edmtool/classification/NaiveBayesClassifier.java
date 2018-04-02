package br.edmtool.classification;

import java.io.File;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;

public class NaiveBayesClassifier extends CustomClassifier {
	private static final long serialVersionUID = 1L;

	private Logger logger = LogManager.getRootLogger();
	
	private final String algorithm = "Naïve Bayes";
	public static final String TEN_FOLD_CROSS_VALIDATION_DESC = "10-Fold Cross-validation";
	public static final String LEAVE_ONE_OUT_CROSS_VALIDATION_DESC = "Leave-One-Out Cross-validation";
	public static final int TEN_FOLD_CROSS_VALIDATION_METHOD = 1;
	public static final int LEAVE_ONE_OUT_CROSS_VALIDATION_METHOD = 2; 

	
	private String trainSetPath = null;
	private String testSetPath = null;

	private DataSource trainDataSource = null;
	private DataSource testDataSource = null;


	public NaiveBayesClassifier() {
		//empty constructor
	}
	
	public NaiveBayesClassifier(String trainSetPath) {
		this.trainSetPath = trainSetPath;
	}
	
	public NaiveBayesClassifier(String trainSetPath, String testSetPath) {
		this.trainSetPath = trainSetPath;
		this.testSetPath = testSetPath;
	}
	
	public static void ConfigureClassIndex(Instances data) {
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
	}
	
	public Instances configureClassIndex(Instances data) {
		if (data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
		
		return data;
	}

	public void loadData() {
		try {
			setTrainDataSource(new DataSource(this.getTrainSetPath()));
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de Treinamento", e);
			e.printStackTrace();
		}
		
		try {
			setTestDataSource(new DataSource(this.getTestSetPath()));
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de Teste", e);
			e.printStackTrace();
		}

	}
	
	public Instances loadCSVDataSource(String datasetPath) {
		CSVLoader loader = new CSVLoader();
		
		try {
			loader.setSource(new File(datasetPath));
		
		return loader.getDataSet();
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de dados", e);
			e.printStackTrace();
		}
		return null; 
	}
	
	public DataSource loadDataSource(String datasetPath) {
		DataSource dataSource = null;
		
		try {
			dataSource = new DataSource(datasetPath);
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de dados", e);
			e.printStackTrace();
		} 
		
		return dataSource;
	}
	
	public Instances getDataSet(DataSource dataSource) throws Exception {
		return dataSource.getDataSet();	
	}
	
	public void configClassifier() {
		
	}
	
	public Classifier trainClassifier(Instances trainDataSet) {

		Classifier nbClassifier = new NaiveBayes();
		
		try {
			nbClassifier.buildClassifier(trainDataSet);
		} catch (Exception e) {
			logger.error("Erro ao carregar arquivo de treinamento", e);
			e.printStackTrace();
		}
		
		logger.trace("Naive Bayes Classifier has been learned.");
		
		return nbClassifier;

	}
	
	public void classifyInstances(Instances testDataSet, Classifier classifier) {
		double sum = 0;
		double correct = 0;
		
		logger.debug("                        \tpred\ttruth\tdist");
		
		for (int i = 0; i < testDataSet.numInstances(); i++) {
			Instance instance = testDataSet.instance(i);
			int truth = (int) instance.classValue();
			
			try {
				int prediction;
				prediction = (int) classifier.classifyInstance(instance);
				logger.debug("Prediction for instance " + i + "\t" + prediction + "\t" + truth + "\t"
						+ Arrays.toString(classifier.distributionForInstance(instance)));
				
				sum++;
				if (truth == prediction)
					correct++;
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
			
		}
		logger.debug("\nAccuracy: " + (correct / sum));
	}
	
	public void trainClassifier(Instances trainDataSet, Instances testDataSet) {

		NaiveBayes nbClassifier = new NaiveBayes();
		
		try {
			nbClassifier.buildClassifier(trainDataSet);
		} catch (Exception e) {
			logger.error(e);
		}
		
		logger.trace("Classifier has been learned.");
		
		logger.debug("                        \tpred\ttruth\tdist");

		double sum = 0;
		double correct = 0;
		for (int i = 0; i < testDataSet.numInstances(); i++) {
			Instance instance = testDataSet.instance(i);
			int truth = (int) instance.classValue();
			
			try {
				int prediction;
				prediction = (int) nbClassifier.classifyInstance(instance);
				logger.debug("Prediction for instance " + i + "\t" + prediction + "\t" + truth + "\t"
						+ Arrays.toString(nbClassifier.distributionForInstance(instance)));
				
				sum++;
				if (truth == prediction)
					correct++;
			} catch (Exception e) {
				logger.error(e);
			}
			
		}
		logger.debug("\nAccuracy: " + (correct / sum));
	}
	
	public void trainClassifier( ) {
		loadData();
		
		Instances trainDataSet = null;
		Instances testDataSet = null;
		
		try {
			trainDataSet = configureClassIndex(getTrainDataSource().getDataSet());
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			testDataSet = configureClassIndex(getTestDataSource().getDataSet());
		} catch (Exception e) {
			logger.error(e);
		}
		
		NaiveBayes nbClassifier = new NaiveBayes();
		try {
			nbClassifier.buildClassifier(trainDataSet);
		} catch (Exception e) {
			logger.error(e);
		}
		
		logger.trace("Classifier has been learned.");
		
		logger.debug("                        \tpred\ttruth\tdist");

		double sum = 0;
		double correct = 0;
		for (int i = 0; i < testDataSet.numInstances(); i++) {
			Instance instance = testDataSet.instance(i);
			int truth = (int) instance.classValue();
			
			try {
				int prediction;
				prediction = (int) nbClassifier.classifyInstance(instance);
				logger.debug("Prediction for instance " + i + "\t" + prediction + "\t" + truth + "\t"
						+ Arrays.toString(nbClassifier.distributionForInstance(instance)));
				
				sum++;
				if (truth == prediction)
					correct++;
			} catch (Exception e) {
				logger.error(e);
			}
			
		}
		logger.debug("\nAccuracy: " + (correct / sum));
	}

	public String getTrainSetPath() {
		return trainSetPath;
	}

	public void setTrainSetPath(String trainSetPath) {
		this.trainSetPath = trainSetPath;
	}

	public String getTestSetPath() {
		return testSetPath;
	}

	public void setTestSetPath(String testSetPath) {
		this.testSetPath = testSetPath;
	}

	public DataSource getTrainDataSource() {
		return trainDataSource;
	}

	public void setTrainDataSource(DataSource trainDataSource) {
		this.trainDataSource = trainDataSource;
	}

	public DataSource getTestDataSource() {
		return testDataSource;
	}

	public void setTestDataSource(DataSource testDataSource) {
		this.testDataSource = testDataSource;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	@Override
	public void buildClassifier(Instances arg0) throws Exception {
		// TODO implementar treinamento do modelo
		
	}

}
