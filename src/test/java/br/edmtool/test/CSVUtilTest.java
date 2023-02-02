package br.edmtool.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.edmtool.utils.CSVUtil;

public class CSVUtilTest {

	@Test
	public void testReadDataFromCSV() {
		CSVUtil csvUtil = new CSVUtil();
		List lista = csvUtil.retrieveDataFromCSV("data/dados_pessoais_test.csv");
		assertNotNull(lista);
	}

	@Test
	public void testReadFromCSV() {
		CSVUtil csvUtil = new CSVUtil();
		csvUtil.readFromCSV("data/dados_pessoais_test.csv", ',', '\'', 1);
	}
	
	
	@Test
	public void testLoadCSVtoBean() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetColumMapping() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHeaderMapping() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadCSVFile() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseCSVWithHeader() {
		fail("Not yet implemented");
	}

}
