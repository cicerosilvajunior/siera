package br.edmtool.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@ViewScoped
public class Classification implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private String scheme;

	@Column
	private String relation;

	@Column
	private String testDataSet;
	
	@Column
	private int instancesTotal;

	@Column
	private int attributesTotal;

	@Column (length = 3000)
	private String attributesList;

	@Transient
	private String attributesFormatted;

	@Transient
	private List<String> attributesArray;

	@Column
	private double accuracy;

	@Column
	private String testMode;

	@OneToOne
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Classification)) {
			return false;
		}
		Classification other = (Classification) obj;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public int getInstancesTotal() {
		return instancesTotal;
	}

	public void setInstancesTotal(int instancesTotal) {
		this.instancesTotal = instancesTotal;
	}

	public int getAttributesTotal() {
		return attributesTotal;
	}

	public void setAttributesTotal(int attributesTotal) {
		this.attributesTotal = attributesTotal;
	}

	public String getAttributesList() {
		return attributesList;
	}

	public void setAttributesList(String attributesList) {
		this.attributesList = attributesList;
	}

	public String getTestMode() {
		return testMode;
	}

	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}

	public ClassificationModel getModelo() {
		return modelo;
	}

	public void setModelo(ClassificationModel modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (scheme != null && !scheme.trim().isEmpty())
			result += "scheme: " + scheme;
		if (relation != null && !relation.trim().isEmpty())
			result += ", relation: " + relation;
		result += ", instancesTotal: " + instancesTotal;
		result += ", attributesTotal: " + attributesTotal;
		if (attributesList != null && !attributesList.trim().isEmpty())
			result += ", attributesList: " + attributesList;
		if (testMode != null && !testMode.trim().isEmpty())
			result += ", testMode: " + testMode;
		return result;
	}

	public List<String> getAttributesArray() {
		this.attributesArray = this.makeList(getAttributesList());
		return attributesArray;
	}

	public void setAttributesArray(List<String> attributesArray) {
		this.attributesArray = attributesArray;
	}

	private List<String> makeList(String attributesStr) {
		List<String> attributes = new ArrayList<String>();
		String[] attributesVector = attributesStr.split(";");
		for (String string : attributesVector) {
			attributes.add(string);
		}
		return attributes;
	}

	public double getAccuracy() {
//		this.accuracy = getModelo().getCorrectlyClassified() / getInstancesTotal();
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public String getAttributesFormatted() {
		return getAttributesList().trim().replaceAll(";", ", ");
	}

	public void setAttributesFormatted(String attributesFormatted) {
		this.attributesFormatted = attributesFormatted;
	}

	public String getTestDataSet() {
		return testDataSet;
	}

	public void setTestDataSet(String testDataSet) {
		this.testDataSet = testDataSet;
	}
}