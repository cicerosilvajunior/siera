package br.edmtool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClassificationModel.class)
public abstract class ClassificationModel_ {

	public static volatile SingularAttribute<ClassificationModel, Date> date;
	public static volatile SingularAttribute<ClassificationModel, Integer> totalFalsePositive;
	public static volatile SingularAttribute<ClassificationModel, Integer> totalTrueNegative;
	public static volatile SingularAttribute<ClassificationModel, Double> correctlyClassified;
	public static volatile SingularAttribute<ClassificationModel, Double> incorrectlyClassified;
	public static volatile SingularAttribute<ClassificationModel, Double> rootRelativeSquaredError;
	public static volatile SingularAttribute<ClassificationModel, Double> meanAbsoluteError;
	public static volatile SingularAttribute<ClassificationModel, Integer> version;
	public static volatile SetAttribute<ClassificationModel, ClassificationInstance> instanciaList;
	public static volatile SingularAttribute<ClassificationModel, Double> kappaStatistic;
	public static volatile SingularAttribute<ClassificationModel, Double> rootMeanSquaredError;
	public static volatile SingularAttribute<ClassificationModel, Integer> totalTruePositive;
	public static volatile SingularAttribute<ClassificationModel, Integer> totalFalseNegative;
	public static volatile SingularAttribute<ClassificationModel, Double> relativeAbsoluteError;
	public static volatile SingularAttribute<ClassificationModel, Long> id;
	public static volatile SingularAttribute<ClassificationModel, String> algorithm;

}

