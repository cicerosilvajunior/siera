package br.edmtool.test;

import java.util.List;

import org.junit.Test;

import br.edmtool.bkt.BKTBruteForceWrapper;
import br.edmtool.utils.CSVUtil;
import br.edmtool.utils.YamlConfigRunner;

public class BKTBruteForceWrapperTest {
	@Test
	public void testComputeBKT() {
		BKTBruteForceWrapper bktWrapper = new BKTBruteForceWrapper();
		List<String[]> listStudentBKT =  bktWrapper.computeBKT("data/POO-Atividade-Avaliativa-1-results_bkt-bf_test2.csv");
		CSVUtil csvUtil = new CSVUtil();
		csvUtil.writeCSV(new YamlConfigRunner().getSingletonInstance().getConfig().getBktOutDataFile(), listStudentBKT);
		
	}

}
