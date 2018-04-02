package br.edmtool.model;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import br.edmtool.bkt.StudentPerformanceEvaluation;

@Entity
@Table(name="student_performance")
public class StudentPerformance implements StudentPerformanceEvaluation {

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
	private String lesson;
	@Column
	private String skill;

	@Column(name = "EVALUATION_PARAMS", nullable = true)
	private String bktProbString; // an String containing the estimated probabilities for L0, G, S and T

	@Transient
	private double[] bktProb; // an array containing the estimated probabilities for L0, G, S and T

	@Transient
	private boolean itSeensItKnows; // indicates if it seen the student knows the skill

	@Column
	private String knowledgeDiagnostic;

	@Transient
	private String distributionProbability;

	@ManyToOne
	private KnowledgeAnalysis analiseDesempenho;

	@Column(name = "STUDENT_ANSWERS", nullable = true)
	private String studentAnswers; // a String containing the student answers sequence used to calculate BKT

	@Transient
	List<String> bktParamsMap; //a list of bkt params using "key: value" format

	@Transient
	private List<Integer> studentAnswersList; // a List String containing the student answers sequence used to calculate
												// BKT

	public String getLesson() {
		return lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public double[] getBktProb() {
		return bktProb;
	}

	public void setBktProb(double[] bktProb) {
		this.bktProb = bktProb;
	}

	public String getBktProbString() {
		this.bktProbString = "L0: " + '\13' + "G: " +(char)13 + "S: " +(char)13 +"T";
		
		return bktProbString;
	}

	public void setBktProbString(String bktProbString) {
		this.bktProbString = bktProbString;
	}

	public boolean isItSeensItKnows() {
		return itSeensItKnows;
	}

	public void setItSeensItKnows(boolean itSeensItKnows) {
		this.itSeensItKnows = itSeensItKnows;
	}

	public double[] getEvaluationParams() {
		return bktProb;
	}

	public void setEvaluationParams(double[] evaluationParams) {
		this.bktProb = evaluationParams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKnowledgeDiagnostic() {
		return knowledgeDiagnostic;
	}

	public void setKnowledgeDiagnostic(String knowledgeDiagnostic) {
		this.knowledgeDiagnostic = knowledgeDiagnostic;
	}

	public String getDistributionProbability() {
		return distributionProbability;
	}

	public void setDistributionProbability(String distributionProbability) {
		this.distributionProbability = distributionProbability;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(format("Id : %s\n", id)).append(format("Lesson : %s\n", lesson))
				.append(format("Skill : %s\n", skill))
				.append(format("It seens the student knows? : %s\n", isItSeensItKnows()))
				.append(format("Student knowledge : %s\n", knowledgeDiagnostic)).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lesson == null) ? 0 : lesson.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentPerformance other = (StudentPerformance) obj;
		if (lesson == null) {
			if (other.lesson != null)
				return false;
		} else if (!lesson.equals(other.lesson))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public KnowledgeAnalysis getAnaliseDesempenho() {
		return analiseDesempenho;
	}

	public void setAnaliseDesempenho(KnowledgeAnalysis analiseDesempenho) {
		this.analiseDesempenho = analiseDesempenho;
	}

	public String getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(String studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	public List<Integer> getStudentAnswersList() {
		if (this.studentAnswersList == null) {
			this.studentAnswersList = new ArrayList<>();
			studentAnswers = studentAnswers.replace('[', ' ').replace(']', ' ').trim();
			studentAnswers = studentAnswers.replaceAll("\\s+", "");
			String[] answersArray = studentAnswers.split(",");
			for (String answer : answersArray) {
				this.studentAnswersList.add(Integer.valueOf(answer));
			}
		}
		return this.studentAnswersList;
	}

	public void setStudentAnswersList(List<Integer> studentAnswersList) {
		this.studentAnswersList = studentAnswersList;
	}

	public List<String> getBktParamsMap() {
		if (this.bktParamsMap == null) {
			this.bktParamsMap = new ArrayList<String>();
			String paramsTemp = bktProbString.replace('[', ' ').replace(']', ' ').trim();
			paramsTemp = paramsTemp.replaceAll("\\s+", "");
			String[] paramsArray = paramsTemp.split(",");
			this.bktParamsMap.add("L0: " + paramsArray[0]);
			this.bktParamsMap.add("G: " + paramsArray[1]);
			this.bktParamsMap.add("S: " + paramsArray[2]);
			this.bktParamsMap.add("T: " + paramsArray[3]);
			paramsTemp = null;
			paramsArray = null;
		}

		return bktParamsMap;
	}

	public void setBktParamsMap(List<String> bktParamsMap) {
		this.bktParamsMap = bktParamsMap;
	}

}
