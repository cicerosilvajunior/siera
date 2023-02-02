package br.edmtool.model;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name="classification_model")
public class ClassificationModel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;
	@Column
	private String algorithm;
	@Column
	private Date date;
	@Column
	private double correctlyClassified;
	@Column
	private double incorrectlyClassified;
	@Column
	private double kappaStatistic;
	@Column
	private double meanAbsoluteError;
	@Column
	private double rootMeanSquaredError;
	@Column
	private double relativeAbsoluteError;
	@Column
	private double rootRelativeSquaredError;
	
	@Column
	private int totalTruePositive;
	@Column
	private int totalFalsePositive;
	@Column
	private int totalFalseNegative;
	@Column
	private int totalTrueNegative;
	
	@OneToMany(cascade = ALL, fetch = FetchType.EAGER, mappedBy = "modelo")
	private Set<ClassificationInstance> instanciaList = new TreeSet<ClassificationInstance>();

	@Transient
	private Integer[] confusionMatrix;
	
	@Transient
	private List<ClassificationInstance> instanciaListOrdered = new ArrayList<ClassificationInstance>();
	
	private <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  Collections.sort(list);
	  return list;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClassificationModel)) {
			return false;
		}
		ClassificationModel other = (ClassificationModel) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCorrectlyClassified() {
		return correctlyClassified;
	}

	public void setCorrectlyClassified(double correctlyClassified) {
		this.correctlyClassified = correctlyClassified;
	}

	public double getIncorrectlyClassified() {
		return incorrectlyClassified;
	}

	public void setIncorrectlyClassified(double incorrectlyClassified) {
		this.incorrectlyClassified = incorrectlyClassified;
	}

	public double getKappaStatistic() {
		return kappaStatistic;
	}

	public void setKappaStatistic(double kappaStatistic) {
		this.kappaStatistic = kappaStatistic;
	}

	public double getMeanAbsoluteError() {
		return meanAbsoluteError;
	}

	public void setMeanAbsoluteError(double meanAbsoluteError) {
		this.meanAbsoluteError = meanAbsoluteError;
	}

	public double getRootMeanSquaredError() {
		return rootMeanSquaredError;
	}

	public void setRootMeanSquaredError(double rootMeanSquaredError) {
		this.rootMeanSquaredError = rootMeanSquaredError;
	}

	public double getRelativeAbsoluteError() {
		return relativeAbsoluteError;
	}

	public void setRelativeAbsoluteError(double relativeAbsoluteError) {
		this.relativeAbsoluteError = relativeAbsoluteError;
	}

	public double getRootRelativeSquaredError() {
		return rootRelativeSquaredError;
	}

	public void setRootRelativeSquaredError(double rootRelativeSquaredError) {
		this.rootRelativeSquaredError = rootRelativeSquaredError;
	}


	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (algorithm != null && !algorithm.trim().isEmpty())
			result += "algorithm: " + algorithm;
		result += ", correctlyClassified: " + correctlyClassified;
		result += ", incorrectlyClassified: " + incorrectlyClassified;
		result += ", kappaStatistic: " + kappaStatistic;
		result += ", meanAbsoluteError: " + meanAbsoluteError;
		result += ", rootMeanSquaredError: " + rootMeanSquaredError;
		result += ", relativeAbsoluteError: " + relativeAbsoluteError;
		result += ", rootRelativeSquaredError: " + rootRelativeSquaredError;
		return result;
	}

	public Set<ClassificationInstance> getInstanciaList() {
		return this.instanciaList;
	}

	public void setInstanciaList(final Set<ClassificationInstance> instanciaList) {
		this.instanciaList = instanciaList;
	}

	public Integer[] getConfusionMatrix() {
		int tp = 0; //true positive
		int fp = 0; //false positive
		int tn = 0; //true negative
		int fn = 0; //false negative
		this.confusionMatrix = new Integer[4];
		
		for (ClassificationInstance instancia : instanciaList) {
			if (instancia.isPredictionCorrect()) {
				if (instancia.getDropoutPrediction().equals(PredictionClass.SIM)) {
					tp++;
				} else {
					tn++;
				}
			} else {
				if (instancia.getDropoutPrediction().equals(PredictionClass.SIM)) {
					fp++;
				} else {
					fn++;
				}
			}
		}
		
		confusionMatrix[0] = tp;
		confusionMatrix[1] = fp;
		confusionMatrix[2] = tn;
		confusionMatrix[3] = fn;
		
		return confusionMatrix;
		
	}

	public void setConfusionMatrix(Integer[] confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	public int getTotalTruePositive() {
		return totalTruePositive;
	}

	public void setTotalTruePositive(int totalTruePositive) {
		this.totalTruePositive = totalTruePositive;
	}

	public int getTotalFalsePositive() {
		return totalFalsePositive;
	}

	public void setTotalFalsePositive(int totalFalsePositive) {
		this.totalFalsePositive = totalFalsePositive;
	}

	public int getTotalFalseNegative() {
		return totalFalseNegative;
	}

	public void setTotalFalseNegative(int totalFalseNegative) {
		this.totalFalseNegative = totalFalseNegative;
	}

	public int getTotalTrueNegative() {
		return totalTrueNegative;
	}

	public void setTotalTrueNegative(int totalTrueNegative) {
		this.totalTrueNegative = totalTrueNegative;
	}

	public List<ClassificationInstance> getInstanciaListOrdered() {
		if(this.instanciaListOrdered.isEmpty())
			this.instanciaListOrdered = this.asSortedList(this.instanciaList);
		return instanciaListOrdered;
	}

	public void setInstanciaListOrdered(List<ClassificationInstance> instanciaListOrdered) {
		this.instanciaListOrdered = instanciaListOrdered;
	}

}