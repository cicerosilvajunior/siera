package br.edmtool.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name="knowledge_analysis")
public class KnowledgeAnalysis implements Serializable {

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
	private int instancesTotal;
	@Column
	private String dataset;
	
	@OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "analiseDesempenho")
	private List<StudentPerformance> studentPerformanceList = new ArrayList<StudentPerformance>();

	@Transient
	private Integer[] confusionMatrix;
	
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
		if (!(obj instanceof KnowledgeAnalysis)) {
			return false;
		}
		KnowledgeAnalysis other = (KnowledgeAnalysis) obj;
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

	
	public Integer[] getConfusionMatrix() {
		int tp = 0; //true positive
		int fp = 0; //false positive
		int tn = 0; //true negative
		int fn = 0; //false negative
		this.confusionMatrix = new Integer[4];
		
//		for (ClassificacaoInstancia instancia : instanciaList) {
//			if (instancia.isPredictionCorrect()) {
//				if (instancia.getDropoutPrediction().equals(PredictionClass.SIM)) {
//					tp++;
//				} else {
//					tn++;
//				}
//			} else {
//				if (instancia.getDropoutPrediction().equals(PredictionClass.SIM)) {
//					fp++;
//				} else {
//					fn++;
//				}
//			}
//		}
//		
		confusionMatrix[0] = tp;
		confusionMatrix[1] = fp;
		confusionMatrix[2] = tn;
		confusionMatrix[3] = fn;
		
		return confusionMatrix;
		
	}

	public void setConfusionMatrix(Integer[] confusionMatrix) {
		this.confusionMatrix = confusionMatrix;
	}

	public int getInstancesTotal() {
		return instancesTotal;
	}

	public void setInstancesTotal(int instancesTotal) {
		this.instancesTotal = instancesTotal;
	}

	public String getDataset() {
		return dataset;
	}

	public void setDataset(String dataset) {
		this.dataset = dataset;
	}

	public List<StudentPerformance> getStudentPerformanceList() {
		return studentPerformanceList;
	}

	public void setStudentPerformanceList(List<StudentPerformance> studentPerformanceList) {
		this.studentPerformanceList = studentPerformanceList;
	}

	@Override
	public String toString() {
		return "AnaliseDesempenho [id=" + id + ", version=" + version + ", algorithm=" + algorithm + ", date=" + date
				+ ", instancesTotal=" + instancesTotal + ", dataset=" + dataset + ", studentPerformanceList="
				+ studentPerformanceList + ", confusionMatrix=" + Arrays.toString(confusionMatrix) + "]";
	}


}