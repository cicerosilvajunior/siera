package br.edmtool.test;

import org.junit.Test;

import br.edmtool.utils.YamlConfigRunner;

public class YamlConfigRunnerTest {

	@Test
	public void testPrintSingleton() {
//		YamlConfigRunner.getSingletonInstance().printSingleton();
//		YamlConfigRunner.getSingletonInstance().printSingleton();
//		YamlConfigRunner.getSingletonInstance().printSingleton();
		
	}

	@Test
	public void testRunYamlConfig() {
		try{
			new YamlConfigRunner().getSingletonInstance().runYamlConfig(YamlConfigRunner.YAML_CONFIG_FILE);
			new YamlConfigRunner().getSingletonInstance().printSingleton();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
