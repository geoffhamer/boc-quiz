package ca.boc.quiz.service;

import java.util.Date;
import java.util.List;

import ca.boc.quiz.model.ClimateData;

public interface ClimateDataParsingService {
	
	public void initClimateData( String fileLocation );
	
	public String[] getColumnNames();
	
	public List<ClimateData> getCityRows();
	
	public List<ClimateData> getCityRowsByDate( Date startDate, Date endDate );
	
	public void addClimateData( ClimateData climateData );

}
