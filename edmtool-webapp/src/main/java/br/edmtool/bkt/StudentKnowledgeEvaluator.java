package br.edmtool.bkt;

public interface StudentKnowledgeEvaluator {
	public abstract String getAlgorithmName();
	public abstract void setAlgorithmName(String algorithmName);
	
	public abstract String getAlgorithmAuthors();
	public abstract void setAlgorithmAuthors(String algorithmAuthors);
	
	public abstract String getAlgorithmReference();
	public abstract void setAlgorithmReference(String algorithmReference);
	
}
