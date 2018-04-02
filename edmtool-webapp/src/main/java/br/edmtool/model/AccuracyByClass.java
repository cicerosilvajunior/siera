package br.edmtool.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

public class AccuracyByClass implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;
	
	@Column
    private double tpRate;
	@Column
    private double fpRate;
	@Column
    private double precision;
	@Column
    private double recall;
	@Column
    private double fMeasure;
	@Column
    private double mcc;
	@Column
    private double rocArea;
	@Column
    private double prcArea;
	@Column
	private String predicationClass;
	
	@ManyToOne
	private ClassificationModel modeloAccuracy;
	

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

	public double getTpRate() {
		return tpRate;
	}

	public void setTpRate(double tpRate) {
		this.tpRate = tpRate;
	}

	public double getFpRate() {
		return fpRate;
	}

	public void setFpRate(double fpRate) {
		this.fpRate = fpRate;
	}

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}

	public double getRecall() {
		return recall;
	}

	public void setRecall(double recall) {
		this.recall = recall;
	}

	public double getfMeasure() {
		return fMeasure;
	}

	public void setfMeasure(double fMeasure) {
		this.fMeasure = fMeasure;
	}

	public double getMcc() {
		return mcc;
	}

	public void setMcc(double mcc) {
		this.mcc = mcc;
	}

	public double getRocArea() {
		return rocArea;
	}

	public void setRocArea(double rocArea) {
		this.rocArea = rocArea;
	}

	public double getPrcArea() {
		return prcArea;
	}

	public void setPrcArea(double prcArea) {
		this.prcArea = prcArea;
	}

	public String getPredicationClass() {
		return predicationClass;
	}

	public void setPredicationClass(String predicationClass) {
		this.predicationClass = predicationClass;
	}

	public ClassificationModel getModeloAccuracy() {
		return modeloAccuracy;
	}

	public void setModeloAccuracy(ClassificationModel modelo) {
		this.modeloAccuracy = modelo;
	}
}