package br.edmtool.test;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edmtool.classification.NaiveBayesClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import weka.core.Instances;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

public class NaiveBayesClassifierTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTrainClassifier() {
		System.out.println("testTrainClassifier");
		try {
			NaiveBayesClassifier classifier = new NaiveBayesClassifier("data/dados_pessoais_train.arff",
					"data/dados_pessoais_test.arff");
			DataSource trainDataSource = classifier.loadDataSource(classifier.getTrainSetPath());
			DataSource testDataSource = classifier.loadDataSource(classifier.getTestSetPath());

			Instances trainningInstances = classifier.getDataSet(trainDataSource);
			Instances testInstances = classifier.getDataSet(testDataSource);

			classifier.configureClassIndex(trainningInstances);
			classifier.configureClassIndex(testInstances);

			classifier.trainClassifier(trainningInstances, testInstances);
		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail trainning the custom classifier");
		}

	}

	@Test
	public void testClassifyInstances() {
		System.out.println("testClassifyInstances");
		try {
			NaiveBayesClassifier classifier = new NaiveBayesClassifier("data/dados_pessoais_train.arff",
					"data/dados_pessoais_test.arff");
			DataSource trainDataSource = classifier.loadDataSource(classifier.getTrainSetPath());
			DataSource testDataSource = classifier.loadDataSource(classifier.getTestSetPath());

			Instances trainningInstances = classifier.getDataSet(trainDataSource);
			Instances testInstances = classifier.getDataSet(testDataSource);

			classifier.configureClassIndex(trainningInstances);
			classifier.configureClassIndex(testInstances);

			Classifier nbClassifier = classifier.trainClassifier(trainningInstances);

			classifier.classifyInstances(testInstances, nbClassifier);

		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

	@Test
	public void testClassifierOutput() {
		System.out.println("testClassifyInstances");
		//0.9999999972720576, 2.7279425121080067E-9
		
		double a = 0.9999999972720576;
		double b = 2.7279425121080067E-9;
		
		if (a > b) {
			System.out.println("A");
		} else {
			System.out.println("B");
		}
		
		a = 8.571022794898629E-4;
		b = 0.9991428977205101;
		if (a > b) {
			System.out.println("A");
		} else {
			System.out.println("B");
		}

		a = 0.5312563517424946;
		b = 0.46874364825750536;
		if (a > b) {
			System.out.println("A");
		} else {
			System.out.println("B");
		}

		
		try {
			NaiveBayesClassifier classifier = new NaiveBayesClassifier("D:/workspace/test-repo/edm-web/src/main/java/data/dados_pessoais_train.arff",
					"D:/workspace/test-repo/edm-web/src/main/java/data/dados_pessoais_test.arff");
			DataSource trainDataSource = classifier.loadDataSource(classifier.getTrainSetPath());
			DataSource testDataSource = classifier.loadDataSource(classifier.getTestSetPath());

			Instances trainningInstances = classifier.getDataSet(trainDataSource);
			Instances testInstances = classifier.getDataSet(testDataSource);

			classifier.configureClassIndex(trainningInstances);
			classifier.configureClassIndex(testInstances);

			//... Instances train = ... // from somewhere Instances test = ... // from somewhere // 
			//train classifier Classifier cls = new J48(); cls.buildClassifier(train); // evaluate classifier and print some statistics 
			Classifier nbClassifier = classifier.trainClassifier(trainningInstances);

			Evaluation eval = new Evaluation(trainningInstances); 
			eval.evaluateModel(nbClassifier, testInstances); 
			System.out.println(eval.toSummaryString("\nResults\n======\n", false));
			
			System.out.println("Correct " + eval.correct());// - number of correctly classified instances (see also incorrect())
			System.out.println("Incorrect " + eval.incorrect());// - number of correctly classified instances (see also incorrect())
			System.out.println("Percent correct " + eval.pctCorrect());// - percentage of correctly classified instances (see also pctIncorrect())
			System.out.println("Kappa " + eval.kappa());// - Kappa statistics
			//numeric class
//			System.out.println("Correlation coefiecient " + eval.correlationCoefficient());// - correlation coefficient
			//general
			System.out.println("Mean absolute error " + eval.meanAbsoluteError());// - the mean absolute error
			System.out.println("Root mean squared error " + eval.rootMeanSquaredError());// - the root mean squared error
			System.out.println("Relative absolute error " + eval.relativeAbsoluteError());// - number of unclassified instances
			System.out.println("Root relative squared error " + eval.rootRelativeSquaredError());// - percentage of unclassified instances

			System.out.println(eval.toClassDetailsString());
			
			System.out.println(eval.confusionMatrix());
			
			System.out.println(eval.toMatrixString());
			
			classifier.classifyInstances(testInstances, nbClassifier);

			
			// load unlabeled data Instances unlabeled = new Instances( new BufferedReader( new FileReader("/some/where/unlabeled.arff")));   
			// set class attribute unlabeled.setClassIndex(unlabeled.numAttributes() - 1);   
			// create copy Instances labeled = new Instances(unlabeled);   
			// label instances 
//			for (int i = 0; i < unlabeled.numInstances(); i++) { double clsLabel = tree.classifyInstance(unlabeled.instance(i)); 
//			labeled.instance(i).setClassValue(clsLabel); } 
			// save labeled data BufferedWriter writer = new BufferedWriter( new FileWriter("/some/where/labeled.arff")); 
//			writer.write(labeled.toString()); writer.newLine(); writer.flush(); writer.close();


			
		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

	// @Test
	public void testClassifyOneInstanceArff() {
		System.out.println("testClassifyOneInstanceArff");
		try {
			NaiveBayesClassifier customClassifier = new NaiveBayesClassifier("data/dados_pessoais_train.arff",
					"data/dados_pessoais_test_name.arff");
			DataSource trainDataSource = customClassifier.loadDataSource(customClassifier.getTrainSetPath());
			DataSource testDataSource = customClassifier.loadDataSource(customClassifier.getTestSetPath());

			Instances trainningInstances = customClassifier.getDataSet(trainDataSource);
			Instances testInstances = customClassifier.getDataSet(testDataSource);

			customClassifier.configureClassIndex(trainningInstances);
			customClassifier.configureClassIndex(testInstances);

			Classifier nbClassifier = customClassifier.trainClassifier(trainningInstances);

			double sum = 0;
			double correct = 0;

			// Declare two numeric attributes
			Attribute Attribute1 = new Attribute("firstNumeric");
			Attribute Attribute2 = new Attribute("secondNumeric");

			// Declare a nominal attribute along with its values
			FastVector fvNominalVal = new FastVector(3);
			fvNominalVal.addElement("blue");
			fvNominalVal.addElement("gray");
			fvNominalVal.addElement("black");
			Attribute Attribute3 = new Attribute("aNominal", fvNominalVal);

			// Declare the class attribute along with its values
			FastVector fvClassVal = new FastVector(2);
			fvClassVal.addElement("positive");
			fvClassVal.addElement("negative");
			Attribute ClassAttribute = new Attribute("theClass", fvClassVal);

			// Declare the feature vector
			FastVector fvWekaAttributes = new FastVector(4);
			fvWekaAttributes.addElement(Attribute1);
			fvWekaAttributes.addElement(Attribute2);
			fvWekaAttributes.addElement(Attribute3);
			fvWekaAttributes.addElement(ClassAttribute);

			// Create an empty training set
			Instances isTrainingSet = new Instances("Rel", fvWekaAttributes, 10);
			// Set class index
			isTrainingSet.setClassIndex(3);

			// Create the instance
			Instance iExample = new Instance(4);
			iExample.setValue((Attribute) fvWekaAttributes.elementAt(0), 1.0);
			iExample.setValue((Attribute) fvWekaAttributes.elementAt(1), 0.5);
			iExample.setValue((Attribute) fvWekaAttributes.elementAt(2), "gray");
			iExample.setValue((Attribute) fvWekaAttributes.elementAt(3), "positive");

			// add the instance
			isTrainingSet.add(iExample);

			// Create a na�ve bayes classifier
			Classifier cModel = (Classifier) new NaiveBayes();
			cModel.buildClassifier(isTrainingSet);

			// // Test the model
			// Evaluation eTest = new Evaluation(isTrainingSet);
			// eTest.evaluateModel(cModel, isTestingSet);

			// Print the result � la Weka explorer:
			// String strSummary = eTest.toSummaryString();
			// System.out.println(strSummary);
			//
			// // Get the confusion matrix
			// double[][] cmMatrix = eTest.confusionMatrix();
			//
			// Specify that the instance belong to the training set
			// in order to inherit from the set description

			FastVector atrib1 = new FastVector(4);
			atrib1.addElement("NAO_ENCONTRADO");
			atrib1.addElement("QUASE_TOTAL");
			atrib1.addElement("POUCO");
			atrib1.addElement("PARCIAL");
			Attribute conhecOO = new Attribute("CONHEC_O_O", atrib1);

			FastVector atrib2 = new FastVector(4);
			atrib2.addElement("NAO_ENCONTRADO");
			atrib2.addElement("GRADUACAO_INCOMPLETA");
			Attribute formacao = new Attribute("FORMACAO", atrib2);

			FastVector atrib3 = new FastVector(4);
			atrib3.addElement("NAO_ENCONTRADO");
			atrib3.addElement("PRIVADA");
			atrib3.addElement("MUNICIPAL");
			Attribute instEns = new Attribute("INST_ENS_ORIG", atrib3);

			FastVector atrib4 = new FastVector(4);
			atrib4.addElement("NAO_ENCONTRADO");
			atrib4.addElement("CIENC_COMP");
			atrib4.addElement("SIST_INFO");
			Attribute cursoGrad = new Attribute("CURSO_GRAD", atrib4);

			FastVector atrib5 = new FastVector(4);
			atrib5.addElement("NAO_ENCONTRADO");
			atrib5.addElement("SIM");
			atrib5.addElement("NAO");
			Attribute gradOO = new Attribute("O_O_GRAD", atrib5);

			FastVector atrib6 = new FastVector(4);
			atrib6.addElement("NAO_ENCONTRADO");
			atrib6.addElement("NAO");
			atrib6.addElement("SIM");
			Attribute trabProgr = new Attribute("TRAB_PROGRAM", atrib6);

			FastVector atrib7 = new FastVector(4);
			atrib7.addElement("NAO_ENCONTRADO");
			atrib7.addElement("NAO_TRABALHA_PROG");
			atrib7.addElement("1~2_ANOS");
			atrib7.addElement("<1_ANO");
			atrib7.addElement("3~5_ANOS");
			Attribute expProgr = new Attribute("EXP_PROGRAM", atrib7);

			FastVector atrib8 = new FastVector(4);
			atrib8.addElement("NAO_ENCONTRADO");
			atrib8.addElement("NAO");
			atrib8.addElement("SIM");
			Attribute trabProgrOO = new Attribute("TRAB_PROGR_O_O", atrib8);

			FastVector atrib9 = new FastVector(4);
			atrib9.addElement("NAO_ENCONTRADO");
			atrib9.addElement("NAO_TRABALHA_PROG");
			atrib9.addElement("<1_ANO");
			atrib9.addElement("1~2_ANOS");
			atrib9.addElement("3~5_ANOS");
			Attribute expOO = new Attribute("EXP_O_O", atrib9);

			FastVector atrib10 = new FastVector(4);
			atrib10.addElement("SIM");
			atrib10.addElement("NAO");
			Attribute dropout = new Attribute("DROPOUT", atrib10);

			FastVector attributes = new FastVector(10);
			attributes.addElement(conhecOO);
			attributes.addElement(formacao);
			attributes.addElement(instEns);
			attributes.addElement(cursoGrad);
			attributes.addElement(gradOO);
			attributes.addElement(trabProgr);
			attributes.addElement(expProgr);
			attributes.addElement(trabProgrOO);
			attributes.addElement(expOO);
			attributes.addElement(dropout);

			Instances isTestSet = new Instances("Rel", attributes, 1);
			// Set class index
			isTestSet.setClassIndex(9);

			Instance iUse = new Instance(10);
			iUse.setValue((Attribute) attributes.elementAt(0), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(1), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(2), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(3), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(4), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(5), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(6), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(7), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(8), "NAO_ENCONTRADO");
			iUse.setValue((Attribute) attributes.elementAt(9), "SIM");

			iUse.setDataset(isTestSet);

			// Get the likelihood of each classes
			// fDistribution[0] is the probability of being �positive�
			// fDistribution[1] is the probability of being �negative�
			double[] fDistribution = nbClassifier.distributionForInstance(iUse);

			double classification = nbClassifier.classifyInstance(iUse);

			// Create empty instance with three attribute values
			Instance inst = new Instance(10);

			inst.setDataset(isTestSet);

			// Set instance's values for the attributes "length", "weight", and "position"
			inst.setValue(0, "NAO_ENCONTRADO");
			inst.setValue(1, "NAO_ENCONTRADO");
			inst.setValue(2, "NAO_ENCONTRADO");
			inst.setValue(3, "NAO_ENCONTRADO");
			inst.setValue(4, "NAO_ENCONTRADO");
			inst.setValue(5, "NAO_ENCONTRADO");
			inst.setValue(6, "NAO_ENCONTRADO");
			inst.setValue(7, "NAO_ENCONTRADO");
			inst.setValue(8, "NAO_ENCONTRADO");
			inst.setValue(9, "SIM");

			int predicao;
			predicao = (int) nbClassifier.classifyInstance(inst);

			System.out.println("                        \tpred\ttruth\tdist");

			for (int i = 0; i < testInstances.numInstances(); i++) {
				Instance instance = testInstances.instance(i);
				int truth = (int) instance.classValue();

				int prediction;
				prediction = (int) nbClassifier.classifyInstance(instance);
				System.out.println("Prediction for instance " + i + "\t" + prediction + "\t" + truth + "\t"
						+ Arrays.toString(nbClassifier.distributionForInstance(instance)));

				sum++;
				if (truth == prediction)
					correct++;
			}
			// customClassifier.classifyInstances(testInstances, nbClassifier);

			System.out.println("\nAccuracy: " + (correct / sum));

		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

	@Test
	public void testClassifyOneInstanceAutomaticCSV() {
		System.out.println("testClassifyOneInstanceAutomaticCSV");
		try {
			NaiveBayesClassifier customClassifier = new NaiveBayesClassifier("data/dados_pessoais_train.csv",
					"data/dados_pessoais_test.csv");
			// NaiveBayesClassifier customClassifier = new
			// NaiveBayesClassifier("data/dados_pessoais_train.csv",
			// "data/dados_pessoais_identified_test.csv");
			Instances trainningInstances = customClassifier.loadCSVDataSource(customClassifier.getTrainSetPath());
			Instances testInstances = customClassifier.loadCSVDataSource(customClassifier.getTestSetPath());

			customClassifier.configureClassIndex(trainningInstances);
			customClassifier.configureClassIndex(testInstances);

			Classifier nbClassifier = customClassifier.trainClassifier(trainningInstances);

			double sum = 0;
			double correct = 0;

			System.out.println("Predictions 1/2                        \tpred\ttruth\tdist");

			for (int i = 0; i < testInstances.numInstances(); i++) {
				Instance instance = testInstances.instance(i);
				int truth = (int) instance.classValue();

				int prediction;
				prediction = (int) nbClassifier.classifyInstance(instance);
				System.out.println("Prediction for instance " + i + "\t" + prediction + "\t" + truth + "\t"
						+ Arrays.toString(nbClassifier.distributionForInstance(instance)));

				sum++;
				if (truth == prediction)
					correct++;
			}
			System.out.println("\nAccuracy: " + (correct / sum));

		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

	@Test
	public void testClassifyOneInstanceManualCSV() {
		System.out.println("testClassifyOneInstanceManualCSV");
		try {
			NaiveBayesClassifier customClassifier = new NaiveBayesClassifier("data/dados_pessoais_train.csv");
			// NaiveBayesClassifier customClassifier = new
			// NaiveBayesClassifier("data/dados_pessoais_train.csv",
			// "data/dados_pessoais_identified_test.csv");
			Instances trainningInstances = customClassifier.loadCSVDataSource(customClassifier.getTrainSetPath());

			customClassifier.configureClassIndex(trainningInstances);

			Classifier nbClassifier = customClassifier.trainClassifier(trainningInstances);

			double sum = 0;
			double correct = 0;

			FastVector atrib1 = new FastVector(4);
			atrib1.addElement("NAO_ENCONTRADO");
			atrib1.addElement("QUASE_TOTAL");
			atrib1.addElement("POUCO");
			atrib1.addElement("PARCIAL");
			Attribute conhecOO = new Attribute("CONHEC_O_O", atrib1);

			FastVector atrib2 = new FastVector(2);
			atrib2.addElement("NAO_ENCONTRADO");
			atrib2.addElement("GRADUACAO_INCOMPLETA");
			Attribute formacao = new Attribute("FORMACAO", atrib2);

			FastVector atrib3 = new FastVector(3);
			atrib3.addElement("NAO_ENCONTRADO");
			atrib3.addElement("PRIVADA");
			atrib3.addElement("MUNICIPAL");
			Attribute instEns = new Attribute("INST_ENS_ORIG", atrib3);

			FastVector atrib4 = new FastVector(3);
			atrib4.addElement("NAO_ENCONTRADO");
			atrib4.addElement("CIENC_COMP");
			atrib4.addElement("SIST_INFO");
			Attribute cursoGrad = new Attribute("CURSO_GRAD", atrib4);

			FastVector atrib5 = new FastVector(3);
			atrib5.addElement("NAO_ENCONTRADO");
			atrib5.addElement("SIM");
			atrib5.addElement("NAO");
			Attribute gradOO = new Attribute("O_O_GRAD", atrib5);

			FastVector atrib6 = new FastVector(3);
			atrib6.addElement("NAO_ENCONTRADO");
			atrib6.addElement("NAO");
			atrib6.addElement("SIM");
			Attribute trabProgr = new Attribute("TRAB_PROGRAM", atrib6);

			FastVector atrib7 = new FastVector(5);
			atrib7.addElement("NAO_ENCONTRADO");
			atrib7.addElement("NAO_TRABALHA_PROG");
			atrib7.addElement("1~2_ANOS");
			atrib7.addElement("<1_ANO");
			atrib7.addElement("3~5_ANOS");
			Attribute expProgr = new Attribute("EXP_PROGRAM", atrib7);

			FastVector atrib8 = new FastVector(3);
			atrib8.addElement("NAO_ENCONTRADO");
			atrib8.addElement("NAO");
			atrib8.addElement("SIM");
			Attribute trabProgrOO = new Attribute("TRAB_PROGR_O_O", atrib8);

			FastVector atrib9 = new FastVector(5);
			atrib9.addElement("NAO_ENCONTRADO");
			atrib9.addElement("NAO_TRABALHA_PROG");
			atrib9.addElement("<1_ANO");
			atrib9.addElement("1~2_ANOS");
			atrib9.addElement("3~5_ANOS");
			Attribute expOO = new Attribute("EXP_O_O", atrib9);

			FastVector atrib10 = new FastVector(2);
			atrib10.addElement("SIM");
			atrib10.addElement("NAO");
			Attribute dropout = new Attribute("DROPOUT", atrib10);

			FastVector attributes = new FastVector(10);
			attributes.addElement(conhecOO);
			attributes.addElement(formacao);
			attributes.addElement(instEns);
			attributes.addElement(cursoGrad);
			attributes.addElement(gradOO);
			attributes.addElement(trabProgr);
			attributes.addElement(expProgr);
			attributes.addElement(trabProgrOO);
			attributes.addElement(expOO);
			attributes.addElement(dropout);

			Instances isTestSet = new Instances("Rel", attributes, 3);
			// Set class index
			isTestSet.setClassIndex(9);

			customClassifier.configureClassIndex(isTestSet);

			// Create empty instance with ten attribute values
			Instance student1 = new Instance(10);

			student1.setDataset(trainningInstances);

			// Set instance's values for the attributes
			student1.setValue(0, "NAO_ENCONTRADO");
			student1.setValue(1, "NAO_ENCONTRADO");
			student1.setValue(2, "NAO_ENCONTRADO");
			student1.setValue(3, "NAO_ENCONTRADO");
			student1.setValue(4, "NAO_ENCONTRADO");
			student1.setValue(5, "NAO_ENCONTRADO");
			student1.setValue(6, "NAO_ENCONTRADO");
			student1.setValue(7, "NAO_ENCONTRADO");
			student1.setValue(8, "NAO_ENCONTRADO");
			student1.setValue(9, "SIM");

			Instance student2 = new Instance(10);
			student2.setDataset(trainningInstances);

			// Set instance's values for the attributes
			// POUCO,GRADUACAO_INCOMPLETA,MUNICIPAL,CIENC_COMP,SIM,NAO,NAO_TRABALHA_PROG,NAO,NAO_TRABALHA_PROG,SIM
			student2.setValue(0, "POUCO");
			student2.setValue(1, "GRADUACAO_INCOMPLETA");
			student2.setValue(2, "MUNICIPAL");
			student2.setValue(3, "CIENC_COMP");
			student2.setValue(4, "SIM");
			student2.setValue(5, "NAO");
			student2.setValue(6, "NAO_TRABALHA_PROG");
			student2.setValue(7, "NAO");
			student2.setValue(8, "NAO_TRABALHA_PROG");
			student2.setValue(9, "SIM");

			Instance student3 = new Instance(10);
			student3.setDataset(trainningInstances);

			// Set instance's values for the attributes
			// PARCIAL,GRADUACAO_INCOMPLETA,PRIVADA,CIENC_COMP,SIM,NAO,NAO_TRABALHA_PROG,NAO,NAO_TRABALHA_PROG,NAO
			student3.setValue(0, "PARCIAL");
			student3.setValue(1, "GRADUACAO_INCOMPLETA");
			student3.setValue(2, "PRIVADA");
			student3.setValue(3, "CIENC_COMP");
			student3.setValue(4, "SIM");
			student3.setValue(5, "NAO");
			student3.setValue(6, "NAO_TRABALHA_PROG");
			student3.setValue(7, "NAO");
			student3.setValue(8, "NAO_TRABALHA_PROG");
			student3.setValue(9, "NAO");

			trainningInstances.add(student1);
			trainningInstances.add(student2);
			trainningInstances.add(student3);

			System.out.println("Predictions 2/2                        \tpred\ttruth\tdist");

			int truthStudent1 = (int) student1.classValue();
			int predStudent1 = (int) nbClassifier.classifyInstance(student1);
			System.out.println("Prediction for instance 1\t" + predStudent1 + "\t" + truthStudent1 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student1)));
			sum++;
			if (truthStudent1 == predStudent1)
				correct++;

			int truthStudent2 = (int) student2.classValue();
			int predStudent2 = (int) nbClassifier.classifyInstance(student2);
			System.out.println("Prediction for instance 1\t" + predStudent2 + "\t" + truthStudent2 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student2)));
			sum++;
			if (truthStudent2 == predStudent2)
				correct++;

			int truthStudent3 = (int) student3.classValue();
			int predStudent3 = (int) nbClassifier.classifyInstance(student3);
			System.out.println("Prediction for instance 1\t" + predStudent3 + "\t" + truthStudent3 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student3)));
			sum++;
			if (truthStudent3 == predStudent3)
				correct++;

			System.out.println("\nAccuracy: " + (correct / sum));

		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

	@Test
	public void testClassifyUsingTrainningDataSetOneInstanceManualCSV() {
		System.out.println("testClassifyUsingTrainningDataSetOneInstanceManualCSV");
		try {
			NaiveBayesClassifier customClassifier = new NaiveBayesClassifier("data/dados_pessoais_train.csv",
					"data/dados_pessoais_test.csv");
			// NaiveBayesClassifier customClassifier = new
			// NaiveBayesClassifier("data/dados_pessoais_train.csv",
			// "data/dados_pessoais_identified_test.csv");
			Instances trainningInstances = customClassifier.loadCSVDataSource(customClassifier.getTrainSetPath());
			Instances testInstances = customClassifier.loadCSVDataSource(customClassifier.getTestSetPath());

			customClassifier.configureClassIndex(trainningInstances);
			customClassifier.configureClassIndex(testInstances);

			Classifier nbClassifier = customClassifier.trainClassifier(trainningInstances);

			double sum = 0;
			double correct = 0;

			// Create empty instance with ten attribute values
			Instance student1 = new Instance(10);

			student1.setDataset(testInstances);

			// Set instance's values for the attributes
			student1.setValue(0, "NAO_ENCONTRADO");
			student1.setValue(1, "NAO_ENCONTRADO");
			student1.setValue(2, "NAO_ENCONTRADO");
			student1.setValue(3, "NAO_ENCONTRADO");
			student1.setValue(4, "NAO_ENCONTRADO");
			student1.setValue(5, "NAO_ENCONTRADO");
			student1.setValue(6, "NAO_ENCONTRADO");
			student1.setValue(7, "NAO_ENCONTRADO");
			student1.setValue(8, "NAO_ENCONTRADO");
			student1.setValue(9, "SIM");

			Instance student2 = new Instance(10);
			student2.setDataset(testInstances);

			// Set instance's values for the attributes
			// POUCO,GRADUACAO_INCOMPLETA,MUNICIPAL,CIENC_COMP,SIM,NAO,NAO_TRABALHA_PROG,NAO,NAO_TRABALHA_PROG,SIM
			student2.setValue(0, "POUCO");
			student2.setValue(1, "GRADUACAO_INCOMPLETA");
			student2.setValue(2, "MUNICIPAL");
			student2.setValue(3, "CIENC_COMP");
			student2.setValue(4, "SIM");
			student2.setValue(5, "NAO");
			student2.setValue(6, "NAO_TRABALHA_PROG");
			student2.setValue(7, "NAO");
			student2.setValue(8, "NAO_TRABALHA_PROG");
			student2.setValue(9, "SIM");

			Instance student3 = new Instance(10);
			student3.setDataset(testInstances);

			// Set instance's values for the attributes
			// PARCIAL,GRADUACAO_INCOMPLETA,PRIVADA,CIENC_COMP,SIM,NAO,NAO_TRABALHA_PROG,NAO,NAO_TRABALHA_PROG,NAO
			student3.setValue(0, "PARCIAL");
			student3.setValue(1, "GRADUACAO_INCOMPLETA");
			student3.setValue(2, "PRIVADA");
			student3.setValue(3, "CIENC_COMP");
			student3.setValue(4, "SIM");
			student3.setValue(5, "NAO");
			student3.setValue(6, "NAO_TRABALHA_PROG");
			student3.setValue(7, "NAO");
			student3.setValue(8, "NAO_TRABALHA_PROG");
			student3.setValue(9, "NAO");

			System.out.println("Predictions 2/2                        \tpred\ttruth\tdist");

			int truthStudent1 = (int) student1.classValue();
			int predStudent1 = (int) nbClassifier.classifyInstance(student1);
			System.out.println("Prediction for instance 1\t" + predStudent1 + "\t" + truthStudent1 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student1)));
			sum++;
			if (truthStudent1 == predStudent1)
				correct++;

			int truthStudent2 = (int) student2.classValue();
			int predStudent2 = (int) nbClassifier.classifyInstance(student2);
			System.out.println("Prediction for instance 1\t" + predStudent2 + "\t" + truthStudent2 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student2)));
			sum++;
			if (truthStudent2 == predStudent2)
				correct++;

			int truthStudent3 = (int) student3.classValue();
			int predStudent3 = (int) nbClassifier.classifyInstance(student3);
			System.out.println("Prediction for instance 1\t" + predStudent3 + "\t" + truthStudent3 + "\t"
					+ Arrays.toString(nbClassifier.distributionForInstance(student3)));
			sum++;
			if (truthStudent3 == predStudent3)
				correct++;

			System.out.println("\nAccuracy: " + (correct / sum));

		} catch (Exception e) {
			e.printStackTrace();

			fail("Fail classifing instances using custom classifier");
		}

	}

}
