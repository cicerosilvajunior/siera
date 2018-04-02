package br.edmtool.utils;

import static java.lang.String.format;

import java.util.Date;

public final class Configuration { 
	//Version parameters
	private Date released;
    private String version;
    
    //Data Mining attributes
	private boolean useCSV;
	private String classificationDataDir;
	private String trainningDataFile;
	private String testDataFile;
	private String studentInstanceFile;
	
	//BKT attributes
	private String bktDataDir;
	private String bktDataFile;
	private String bktOutDataFile;
	
    public Date getReleased() {
        return released;
    }
 
    public String getVersion() {
        return version;
    }
 
    public void setReleased(Date released) {
        this.released = released;
    }
 
    public void setVersion(String version) {
        this.version = version;
    }
 
    public boolean isUseCSV() {
		return useCSV;
	}

	public void setUseCSV(boolean useCSV) {
		this.useCSV = useCSV;
	}

	public String getTrainningDataFile() {
		return trainningDataFile;
	}

	public void setTrainningDataFile(String trainningDataFile) {
		this.trainningDataFile = trainningDataFile;
	}

	public String getTestDataFile() {
		return testDataFile;
	}

	public void setTestDataFile(String testDataFile) {
		this.testDataFile = testDataFile;
	}

	public String getStudentInstanceFile() {
		return studentInstanceFile;
	}

	public void setStudentInstanceFile(String studentInstanceFile) {
		this.studentInstanceFile = studentInstanceFile;
	}

	public String getBktDataFile() {
		return bktDataFile;
	}

	public void setBktDataFile(String bktDataFile) {
		this.bktDataFile = bktDataFile;
	}

	public String getBktOutDataFile() {
		return bktOutDataFile;
	}

	public void setBktOutDataFile(String bktOutDataFile) {
		this.bktOutDataFile = bktOutDataFile;
	}

	public String getClassificationDataDir() {
		return classificationDataDir;
	}

	public void setClassificationDataDir(String classificationDataDir) {
		this.classificationDataDir = classificationDataDir;
	}
	
	public String getBktDataDir() {
		return bktDataDir;
	}

	public void setBktDataDir(String bktDataDir) {
		this.bktDataDir = bktDataDir;
	}
	
	@Override
    public String toString() {
        return new StringBuilder()
            .append( format( "Version: %s\n", version ) )
            .append( format( "Released: %s\n", released ) )
            .append( format( "UseCSV: %s\n", useCSV ) )
            .append( format( "Classification Data Directory: %s\n", classificationDataDir ) )
            .append( format( "Trainning Data File: %s\n", trainningDataFile ) )
            .append( format( "Test Data File: %s\n", testDataFile ) )
            .append( format( "Student Instance File: %s\n", studentInstanceFile ) )
            .append( format( "BKT Data Directory: %s\n", bktDataDir ) )
            .append( format( "BKT Data File: %s\n", bktDataFile ) )
            .append( format( "BKT Output Data File: %s\n", bktOutDataFile ) )
            .toString();
    }

	
}