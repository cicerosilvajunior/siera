package br.edmtool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Classification.class)
public abstract class Classification_ {

	public static volatile SingularAttribute<Classification, Date> date;
	public static volatile SingularAttribute<Classification, Integer> attributesTotal;
	public static volatile SingularAttribute<Classification, String> scheme;
	public static volatile SingularAttribute<Classification, String> testMode;
	public static volatile SingularAttribute<Classification, String> testDataSet;
	public static volatile SingularAttribute<Classification, Double> accuracy;
	public static volatile SingularAttribute<Classification, String> attributesList;
	public static volatile SingularAttribute<Classification, Long> id;
	public static volatile SingularAttribute<Classification, Integer> version;
	public static volatile SingularAttribute<Classification, ClassificationModel> modelo;
	public static volatile SingularAttribute<Classification, String> relation;
	public static volatile SingularAttribute<Classification, Integer> instancesTotal;

}

