package br.edmtool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(KnowledgeAnalysis.class)
public abstract class KnowledgeAnalysis_ {

	public static volatile SingularAttribute<KnowledgeAnalysis, Date> date;
	public static volatile ListAttribute<KnowledgeAnalysis, StudentPerformance> studentPerformanceList;
	public static volatile SingularAttribute<KnowledgeAnalysis, Long> id;
	public static volatile SingularAttribute<KnowledgeAnalysis, Integer> version;
	public static volatile SingularAttribute<KnowledgeAnalysis, String> dataset;
	public static volatile SingularAttribute<KnowledgeAnalysis, String> algorithm;
	public static volatile SingularAttribute<KnowledgeAnalysis, Integer> instancesTotal;

}

