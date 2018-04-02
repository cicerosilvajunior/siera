package br.edmtool.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StudentPerformance.class)
public abstract class StudentPerformance_ {

	public static volatile SingularAttribute<StudentPerformance, Date> date;
	public static volatile SingularAttribute<StudentPerformance, String> bktProbString;
	public static volatile SingularAttribute<StudentPerformance, KnowledgeAnalysis> analiseDesempenho;
	public static volatile SingularAttribute<StudentPerformance, String> skill;
	public static volatile SingularAttribute<StudentPerformance, String> lesson;
	public static volatile SingularAttribute<StudentPerformance, Long> id;
	public static volatile SingularAttribute<StudentPerformance, String> knowledgeDiagnostic;
	public static volatile SingularAttribute<StudentPerformance, String> studentAnswers;
	public static volatile SingularAttribute<StudentPerformance, Integer> version;
	public static volatile SingularAttribute<StudentPerformance, String> userId;

}

