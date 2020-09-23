package ca.boc.quiz.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader {

	private String fileLocation;


	public DataFileReader() {
		super();
	}

	public DataFileReader(String fileLocation) {
		super();
		this.fileLocation = fileLocation;
	}
	
	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public List<String> fetchRawData() {
		
		String line;
		List<String> lines = new ArrayList<String>();
		
		BufferedReader reader = null; 
		
		try {
			
			reader = new BufferedReader( new FileReader(fileLocation) );
			
			while ( ( line = reader.readLine() ) != null ) {
			
				System.out.println(line);
				
				lines.add( line );
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lines;
	}
	
}
 