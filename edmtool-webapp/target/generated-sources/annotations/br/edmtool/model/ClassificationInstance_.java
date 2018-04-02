package br.edmtool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClassificationInstance.class)
public abstract class ClassificationInstance_ {

	public static volatile SingularAttribute<ClassificationInstance, Date> date;
	public static volatile SingularAttribute<ClassificationInstance, Long> id;
	public static volatile SingularAttribute<ClassificationInstance, String> dropoutPrediction;
	public static volatile SingularAttribute<ClassificationInstance, String> dropoutTruth;
	public static volatile SingularAttribute<ClassificationInstance, Integer> version;
	public static volatile SingularAttribute<ClassificationInstance, String> userId;
	public static volatile SingularAttribute<ClassificationInstance, ClassificationModel> modelo;
	public static volatile SingularAttribute<ClassificationInstance, Double> probabilityClassA;
	public static volatile SingularAttribute<ClassificationInstance, Double> probabilityClassB;

}

