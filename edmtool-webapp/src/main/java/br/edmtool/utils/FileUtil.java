package br.edmtool.utils;

public class FileUtil {
	
	public static String cleanFilePath(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/")+1);
	}
	
	public void saveFile(String filePath, String fileName) {
		
	}

}
