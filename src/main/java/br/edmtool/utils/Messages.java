package br.edmtool.utils;

import java.util.HashMap;
import java.util.Map;

public class Messages {

	public static final String ALTO_APROVEITAMENTO_GUESS = "O aluno obteve bom aproveitamento na área de conhecimento, porém recomenda-se conversar com o Tutor caso o aluno tenha dificuldades relacionadas ao conteúdo.";
	public static final String ALTO_APROVEITAMENTO_SLIP = "O aluno obteve bom aproveitamento na área de conhecimento, porém recomenda-se atenção nas respostas para evitar erros.";
	public static final String ALTO_APROVEITAMENTO_UNKONW = "Aluno obteve bom aproveitamento na área de conhecimento.";

	public static final String APROVEITAMENTO_INALTERADO_GUESS = "Não foi possível observar melhoras do aproveitamento na área de conhecimento, recomenda-se conversar com o Tutor caso o aluno tenha dificuldades relacionadas ao conteúdo.";
	public static final String APROVEITAMENTO_INALTERADO_SLIP = "Não foi possível observar melhoras do aproveitamento na área de conhecimento, recomenda-se atenção nas respostas para evitar erros.";
	public static final String APROVEITAMENTO_INALTERADO_UNKONW = "Não foi possível observar melhoras no aproveitamento do aluno na área de conhecimento.";

	public static final String PERDA_APROVEITAMENTO_GUESS = "O aluno reduziu o aproveitamento na área de conhecimento, recomenda-se ao aluno conversar com o Tutor para avaliar eventuais dificuldades relacionadas ao conteúdo.";
	public static final String PERDA_APROVEITAMENTO_SLIP = "O aluno reduziu o aproveitamento na área de conhecimento, recomenda-se atenção nas respostas para evitar erros.";
	public static final String PERDA_APROVEITAMENTO_UNKONW = "O aluno reduziu o aproveitamento na área de conhecimento.";

	Map<String, String> performanceEvaluationMessages = new HashMap<String, String>();

}
