package br.edmtool.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class YamlConfigRunner {
	private static Logger logger = LogManager.getRootLogger();

	public static final String YAML_CONFIG_FILE = "edm-tool/config/edm-tool.yml";
	public static final String DATA_PATH = "edm-tool/";
	
	public static final String ROOT_DIR_WIN = "C:/";
	public static final String ROOT_DIR_LINUX = "/opt/";
	public static final String ROOT_DIR_MAC = "";
	public static final String ROOT_DIR_OTHER = "";
	
    private YamlConfigRunner singletonInstance;
    private Configuration config = null;
    

    public YamlConfigRunner() {
    }

    // Providing Global point of access
    public YamlConfigRunner getSingletonInstance() {
        if (null == singletonInstance) {
            singletonInstance = new YamlConfigRunner();
			logger.trace("Creating new instance");
            try {
				singletonInstance.setConfig(runYamlConfig(YamlConfigRunner.YAML_CONFIG_FILE)); 
			} catch (IOException e) {
				logger.error(e);
			}
        }
        return singletonInstance;
    }
    
    public YamlConfigRunner getSingletonInstance(String yamlConfigPath) {
        if (null == singletonInstance) {
            singletonInstance = new YamlConfigRunner();
            logger.trace("Creating new instance");
            try {
				singletonInstance.setConfig(runYamlConfig(yamlConfigPath)); 
			} catch (IOException e) {
				logger.error(e);
			}
        }
        return singletonInstance;
    }
    
    public void printSingleton(){
    	logger.trace("Inside print Singleton");
    }
    
    public static Configuration runYamlConfig(String yamlConfigPath) throws IOException {
		Yaml yaml = new Yaml();
		
		Configuration config = null;
		
		try (InputStream in = Files.newInputStream(Paths.get(yamlConfigPath))) {
			config = yaml.loadAs(in, Configuration.class);
			logger.trace(config.toString());
		}
		
		return config;
	}

	public Configuration getConfig() {
		return config;
	}
	public void setConfig(Configuration config) {
		this.config = config;
	}
}
