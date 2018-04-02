package br.edmtool.bkt;

public interface StudentPerformanceEvaluation {
	
	public String getUserId();
	public void setUserId(String id);

	public String getLesson();
	public void setLesson(String lesson);
	
	public String getSkill();
	public void setSkill(String skill);
	
	public boolean isItSeensItKnows();
	public void setItSeensItKnows(boolean itSeensItKnows);
	
	public String getKnowledgeDiagnostic();
	public void setKnowledgeDiagnostic(String knowledgeDiagnostic);
	
	public double[] getEvaluationParams();
	public void setEvaluationParams(double[] evaluationParams);
	
	}
