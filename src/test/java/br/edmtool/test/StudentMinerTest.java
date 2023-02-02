package br.edmtool.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import br.edmtool.controller.StudentMiner;
import br.edmtool.model.Classification;
import br.edmtool.utils.YamlConfigRunner;

public class StudentMinerTest {
	private Logger logger = LogManager.getRootLogger();
	
	@Test
	public void testPredictStudentDropout() {
		try {
			StudentMiner miner = new StudentMiner(new YamlConfigRunner().getSingletonInstance().getConfig().getTrainningDataFile());
			Classification student = miner.predictStudentsDropout(new YamlConfigRunner().getSingletonInstance().getConfig().getStudentInstanceFile(), 1);
			logger.trace("Student data "+student.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
	}
	

}
