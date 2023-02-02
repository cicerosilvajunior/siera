package br.edmtool.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name="classification_instance")
public class ClassificationInstance implements Serializable, Comparable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;
	
	@Column
    private String userId;
	@Column
    private Date date;
	@Column
	private String dropoutPrediction;
	@Column
	private String dropoutTruth;
	@Transient
	private String distributionProbability;
	
	@Column
	private double probabilityClassA;
	
	@Column
	private double probabilityClassB;
	
	@Transient
    private boolean predictionCorrect;
	
	@ManyToOne
	private ClassificationModel modelo;

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
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (!(obj instanceof ClassificacaoInstancia)) {
//			return false;
//		}
//		ClassificacaoInstancia other = (ClassificacaoInstancia) obj;
//		if (id != null) {
//			if (!id.equals(other.id)) {
//				return false;
//			}
//		}
//		return true;
//	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userIdmin) {
		this.userId = userIdmin;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDropoutPrediction() {
		return dropoutPrediction;
	}

	public void setDropoutPrediction(String dropoutPrediction) {
		this.dropoutPrediction = dropoutPrediction;
	}

	public String getDropoutTruth() {
		return dropoutTruth;
	}

	public void setDropoutTruth(String dropoutTruth) {
		this.dropoutTruth = dropoutTruth;
	}

	public String getDistributionProbability() {
		return distributionProbability;
	}

	public void setDistributionProbability(String distributionProbability) {
		this.distributionProbability = distributionProbability;
	}

	public boolean isPredictionCorrect() {
		return predictionCorrect;
	}

	public void setPredictionCorrect(boolean predictionCorrect) {
		this.predictionCorrect = predictionCorrect;
	}

	public ClassificationModel getModelo() {
		return modelo;
	}

	public void setModelo(ClassificationModel modelo) {
		this.modelo = modelo;
	}

	public double getProbabilityClassA() {
		return probabilityClassA;
	}

	public void setProbabilityClassA(double distributionProbabilityA) {
		this.probabilityClassA = distributionProbabilityA;
	}

	public double getProbabilityClassB() {
//		this.distributionProbabilityB = distributionProbability.split(",")[1].replace("]", "");
		return probabilityClassB;
	}

	public void setProbabilityClassB(double distributionProbabilityB) {
		this.probabilityClassB = distributionProbabilityB;
	}

	@Override
	public int compareTo(Object arg0) {
		if (this.id < ((ClassificationInstance)arg0).id) {
            return -1;
        }
        if (this.id > ((ClassificationInstance)arg0).id) {
            return 1;
        }
		
		return 0;
	}
}