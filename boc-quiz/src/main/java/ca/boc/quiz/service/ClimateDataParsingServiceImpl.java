package ca.boc.quiz.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

import ca.boc.quiz.model.ClimateData;

@Service
public class ClimateDataParsingServiceImpl implements ClimateDataParsingService {
	
	private final static Logger LOG = LoggerFactory.getLogger(ClimateDataParsingServiceImpl.class) ;

	private List<ClimateData> climateData;
	
	@Override
	
	public void initClimateData(String fileLocation) {
		
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

			this.climateData = data;

		} catch (FileNotFoundException e) {
			
			LOG.error("Can't find csv file in provided location: " + fileLocation);
			
			// TODO Throw proper error
			e.printStackTrace();
			
		} catch (Exception ex) {

			LOG.error("Error reading/parsing csv file in provided location: " + fileLocation);

			// TODO Throw proper error
			
			
		}
		
		
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClimateData> getCityRows() {
		
		return this.climateData;
	}

	@Override
	public List<ClimateData> getCityRowsByDate(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
