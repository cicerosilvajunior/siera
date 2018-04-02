package br.edmtool.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.edmtool.classification.NaiveBayesClassifier;
import br.edmtool.model.Classification;
import br.edmtool.model.ClassificationInstance;
import br.edmtool.model.ClassificationModel;
import br.edmtool.utils.CSVUtil;
import br.edmtool.utils.FileUtil;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

public class StudentMiner {
	private Logger logger = LogManager.getRootLogger();
	
	private NaiveBayesClassifier classifier = null;
	private static final int STUDENT_ID_COLUMN = 0;
	

	public StudentMiner(String trainSetPath, String testSetPath) {
		classifier = new NaiveBayesClassifier(trainSetPath, testSetPath);
	}

	public StudentMiner(String trainSetPath) {
		classifier = new NaiveBayesClassifier(trainSetPath);
	}

	
	public Classification predictStudentsDropout(String studentDataFile, int crossValidStrategy) {
		logger.trace("Method predictAllStudentsDropout");
		Classification classificacao = new Classification();

		try {
			NaiveBayesClassifier customClassifier = getClassifier();
			
			Instances trainningInstances = null;
			if(customClassifier.getTrainSetPath().endsWith(".csv")) {
				trainningInstances = customClassifier.loadCSVDataSource(customClassifier.getTrainSetPath());
					
			} else if(customClassifier.getTrainSetPath().endsWith(".arff")) {
				trainningInstances = new Instances(new BufferedReader(new FileReader(customClassifier.getTrainSetPath())));
			}
			
			customClassifier.configureClassIndex(trainningInstances);

			Classifier nbClassifier = customClassifier.trainClassifier(trainningInstances);

			Evaluation eval = new Evaluation(trainningInstances); 

			if(crossValidStrategy == NaiveBayesClassifier.TEN_FOLD_CROSS_VALIDATION_METHOD) {
				eval.crossValidateModel(nbClassifier, trainningInstances, 10, new Random(1));	
				classificacao.setTestMode(NaiveBayesClassifier.TEN_FOLD_CROSS_VALIDATION_DESC);
			} else if(crossValidStrategy == NaiveBayesClassifier.LEAVE_ONE_OUT_CROSS_VALIDATION_METHOD) {
				eval.crossValidateModel(nbClassifier, trainningInstances, trainningInstances.numInstances(), new Random(1));
				classificacao.setTestMode(NaiveBayesClassifier.LEAVE_ONE_OUT_CROSS_VALIDATION_DESC);
			}
			
			logger.debug("Confusion matrix "+Arrays.toString(eval.confusionMatrix()[0]));
			logger.debug("Confusion matrix "+Arrays.toString(eval.confusionMatrix()[1]));
			
			Date dataClassificacao = new Date();
			
			ClassificationModel modelo = new ClassificationModel();
        	modelo.setAlgorithm(customClassifier.getAlgorithm());
        	modelo.setDate(dataClassificacao);
        	modelo.setCorrectlyClassified(eval.correct());
        	modelo.setIncorrectlyClassified(eval.incorrect());
        	modelo.setKappaStatistic(eval.kappa());
        	modelo.setMeanAbsoluteError(eval.meanAbsoluteError());
        	modelo.setRootMeanSquaredError(eval.rootMeanSquaredError());
        	modelo.setRelativeAbsoluteError(eval.relativeAbsoluteError());
        	modelo.setRootRelativeSquaredError(eval.rootRelativeSquaredError());
        	modelo.setTotalTruePositive((int)eval.confusionMatrix()[0][0]);
        	modelo.setTotalFalsePositive((int)eval.confusionMatrix()[0][1]);
        	modelo.setTotalFalseNegative((int)eval.confusionMatrix()[1][0]);
        	modelo.setTotalTrueNegative((int)eval.confusionMatrix()[1][1]);
        	
        	Instances testInstances = customClassifier.loadCSVDataSource(studentDataFile);
        	
			Set<ClassificationInstance> listStudentInstances = new LinkedHashSet<ClassificationInstance>(testInstances.numInstances());

        	customClassifier.configureClassIndex(testInstances);
        	
			CSVUtil csvUtil = new CSVUtil();

			List<String[]> listStudentData = csvUtil.retrieveDataFromCSV(studentDataFile);
			
			for (Iterator iterator = listStudentData.iterator(); iterator.hasNext();) {
				String[] strings = (String[]) iterator.next();
				Instance studentInstance = new Instance(strings.length - 1);
				
				studentInstance.setDataset(trainningInstances);
				
				for (int i = 1; i < strings.length; i++) {
					String string = strings[i];
					logger.trace("Setting value for attribute [" + trainningInstances.attribute(i - 1).name() + "] value [ " + string + "]");
					studentInstance.setValue(i - 1, string);
				}
				
				ClassificationInstance instanciaClassificacao = new ClassificationInstance();
				instanciaClassificacao.setUserId(strings[StudentMiner.STUDENT_ID_COLUMN]);
				
				int lastIndex = trainningInstances.numAttributes() - 1;
				int classIndex = (int) nbClassifier.classifyInstance(studentInstance);
				
				//classifica a instancia e popula a predicao de evasao
				instanciaClassificacao.setDropoutPrediction(trainningInstances.attribute(lastIndex).value((int)classIndex));
				instanciaClassificacao.setDropoutTruth(strings[strings.length-1]);
				
				instanciaClassificacao.setProbabilityClassA(nbClassifier.distributionForInstance(studentInstance)[0]);
				instanciaClassificacao.setProbabilityClassB(nbClassifier.distributionForInstance(studentInstance)[1]);
	        	instanciaClassificacao.setDate(dataClassificacao);
	        	instanciaClassificacao.setModelo(modelo);
	        	
				listStudentInstances.add(instanciaClassificacao);
			}

			modelo.setInstanciaList(listStudentInstances);
      
			classificacao.setAccuracy(eval.pctCorrect());
            classificacao.setModelo(modelo);
            classificacao.setInstancesTotal(trainningInstances.numInstances());
            
            classificacao.setRelation(FileUtil.cleanFilePath(customClassifier.getTrainSetPath()));
            classificacao.setTestDataSet(FileUtil.cleanFilePath(studentDataFile));
            
            classificacao.setAttributesTotal(trainningInstances.numAttributes());
            
            String[] attributesArray = new String[trainningInstances.numAttributes()];
            for (int i = 0; i < attributesArray.length; i++) {
				attributesArray[i] = trainningInstances.attribute(i).name();
			}
            String attributesList = Arrays.toString(attributesArray);
            classificacao.setAttributesList(attributesList.replace("[", "").replace("]", ""));
			
			logger.debug("Model evaluation "+eval.toSummaryString("\nResults\n======\n", false));
			logger.debug("Correct " + eval.correct());// - number of correctly classified instances (see also incorrect())
			logger.debug("Incorrect " + eval.incorrect());// - number of correctly classified instances (see also incorrect())
			logger.debug("Percent correct " + eval.pctCorrect());// - percentage of correctly classified instances (see also pctIncorrect())
			logger.debug("Kappa " + eval.kappa());// - Kappa statistics
			//numeric class

			//general
			logger.debug("Mean absolute error " + eval.meanAbsoluteError());// - the mean absolute error
			logger.debug("Root mean squared error " + eval.rootMeanSquaredError());// - the root mean squared error
			logger.debug("Relative absolute error " + eval.relativeAbsoluteError());// - number of unclassified instances
			logger.debug("Root relative squared error " + eval.rootRelativeSquaredError());// - percentage of unclassified instances

			logger.debug("Evaluation details "+eval.toClassDetailsString());
			
			logger.debug("Confusion matrix "+Arrays.toString(eval.confusionMatrix()[0]));
			logger.debug("Confusion matrix "+Arrays.toString(eval.confusionMatrix()[1]));
			
			logger.debug("Confusion matrix "+eval.toMatrixString());

		} catch (Exception e) {
			logger.error(e);
		}
		
		return classificacao;

	}


	public NaiveBayesClassifier getClassifier() {
		return classifier;
	}

	public void setClassifier(NaiveBayesClassifier classifier) {
		this.classifier = classifier;
	}

}