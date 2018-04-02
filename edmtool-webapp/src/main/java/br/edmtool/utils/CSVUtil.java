package br.edmtool.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUtil {
	private Logger logger = LogManager.getRootLogger();

	private char separator = ',';
	private char quoteChar = '\'';
	private int skipLine = 1;

	/**
	 * Default constructor
	 */
	public CSVUtil() {
	}

	/**
	 * Constructor for a custom CSV format
	 * 
	 * @param separator
	 *            char separator for a text (eg. ',' 'tab' ';')
	 * @param quoteChar
	 *            identifier for a quote char
	 * @param skipLine
	 *            indicates the line to read from. Skip line can be used for skip
	 *            reading header of a CSV file.
	 */
	public CSVUtil(char separator, char quoteChar, int skipLine) {
		this.separator = separator;
		this.quoteChar = quoteChar;
		this.skipLine = skipLine;
	}

	public void writeCSV(String fileName, List<String[]> data) {
		try {

			CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName, true));

			csvWriter.writeAll(data);
			csvWriter.close();

			logger.trace(csvWriter);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public List<String[]> retrieveDataFromCSV(String csvFileName) {

		CSVReader reader = null;
		List<String[]> myEntries = null;
		try {
			reader = new CSVReader(new FileReader(csvFileName), getSeparator(), getQuoteChar(), getSkipLine());
			myEntries = reader.readAll();
			reader.close();
		} catch (IOException e) {
			logger.error(e);
		}
		return myEntries;

	}

	public void readFromCSV(String csvFileName) {

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFileName));
			String[] line;
			while ((line = reader.readNext()) != null) {
				logger.trace("Student [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	public void readFromCSV(String csvFileName, char separator, char quoteChar, int lineNumber) {

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csvFileName), separator, quoteChar, lineNumber);
			String[] line;
			while ((line = reader.readNext()) != null) {
				logger.trace("Student [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
			}
		} catch (IOException e) {
			logger.error(e);
		}

	}

	public char getSeparator() {
		return separator;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	public char getQuoteChar() {
		return quoteChar;
	}

	public void setQuoteChar(char quoteChar) {
		this.quoteChar = quoteChar;
	}

	public int getSkipLine() {
		return skipLine;
	}

	public void setSkipLine(int skipLine) {
		this.skipLine = skipLine;
	}

}
