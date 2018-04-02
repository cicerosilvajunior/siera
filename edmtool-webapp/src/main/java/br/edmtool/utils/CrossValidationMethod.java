package br.edmtool.utils;

import java.io.Serializable;

public class CrossValidationMethod implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CrossValidationMethod() {
		super();
	}
	
	public CrossValidationMethod(String id, String description) {
		this.idCrossValidationMethod = id;
		this.crossValidationMethodDescription = description;
	}
	
	private String idCrossValidationMethod;
	private String crossValidationMethodDescription;
	
	public String getIdCrossValidationMethod() {
		return idCrossValidationMethod;
	}
	public void setIdCrossValidationMethod(String idCrossValidationMethod) {
		this.idCrossValidationMethod = idCrossValidationMethod;
	}
	public String getCrossValidationMethodDescription() {
		return crossValidationMethodDescription;
	}
	public void setCrossValidationMethodDescription(String crossValidationMethodDescription) {
		this.crossValidationMethodDescription = crossValidationMethodDescription;
	}
	@Override
	public String toString() {
		return crossValidationMethodDescription;
	}
	
	
}
