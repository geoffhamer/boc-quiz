package ca.boc.quiz.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import ca.boc.quiz.exception.DataParsingException;
import ca.boc.quiz.model.ClimateData;

@Service
public class ClimateDataParsingServiceImpl implements ClimateDataParsingService {
	
	private final static Logger LOG = LoggerFactory.getLogger(ClimateDataParsingServiceImpl.class) ;

	private List<ClimateData> climateData;
	
	private Date earliestDate = new Date(Long.MIN_VALUE);
	private Date latestDate = new Date(Long.MAX_VALUE);
	
	@Override
	
	public void initClimateData(String fileLocation) throws DataParsingException {
		
		LOG.info("Loading climate data from: " + fileLocation);
		
		List<ClimateData> data; 
		
		FileReader reader;
		try {
			
			reader = new FileReader(fileLocation);
			data = new CsvToBeanBuilder<ClimateData>( reader )
				       .withType(ClimateData.class).withSkipLines(1).build().parse();

			LOG.info("Data Loaded: " + data.size() + " rows");
			
			// Give each row an identifier
			int i = 0;
			for (ClimateData climateData : data) {
				climateData.setIndex(i++);
			}

			if (data.size() <= 0) {
				LOG.warn("No data loaded while parsing file.");
			}
			
			this.climateData = data;

		} catch (FileNotFoundException e) {
			
			String errStr = "Can't find csv file in provided location: " + fileLocation;
			
			LOG.error(errStr);
			
			throw new DataParsingException(errStr);
			
		} catch (Exception ex) {

			String errStr = "An error occurred while trying to parse the following data file: " + fileLocation;
			
			LOG.error(errStr);

			throw new DataParsingException(errStr);
			
		}
		
	}

	@Override
	public List<ClimateData> getCityRows() {
		
		return this.climateData;
	}

	@Override
	public List<ClimateData> getCityRowsByDate(Date afterDate, Date beforeDate) {
		
    	// If a date isn't provided, set it to the limit
    	if ( afterDate == null) {
			afterDate = this.earliestDate;
		}
    	
    	if ( beforeDate == null) {
			beforeDate = this.latestDate;
		}
    	
    	List<ClimateData> filteredClimateData = new ArrayList<ClimateData>();
    	
    	for (ClimateData climateData : this.climateData ) {
			
    		if ( climateData.getDate().after( afterDate ) && 
    			 climateData.getDate().before( beforeDate ) ) {
				
    			filteredClimateData.add( climateData );
			}
    		
		}
		
		return filteredClimateData;
	}

	@Override
	public void addClimateData( ClimateData climateData ) {
		
		if ( this.climateData == null ) {
			
			this.climateData = new ArrayList<ClimateData>();
			
		}
		
		this.climateData.add( climateData ); 
	}
}
