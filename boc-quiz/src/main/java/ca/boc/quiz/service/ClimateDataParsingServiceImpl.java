package ca.boc.quiz.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.boc.quiz.model.ClimateData;
import ca.boc.quiz.util.DataFileReader;

@Service
public class ClimateDataParsingServiceImpl implements ClimateDataParsingService {
	
	private final static Logger LOG = LoggerFactory.getLogger(ClimateDataParsingServiceImpl.class) ;

	private String[] columnNames;
	
	private List<ClimateData> climateData;
	
	@Override
	public void initClimateData(String fileLocation) {
		
		LOG.info("Loading climate data from: " + fileLocation);
		
		DataFileReader fileReader = new DataFileReader(fileLocation);
		List<String> dataRows = fileReader.fetchRawData();
		
		int i = 0;
		for (String row : dataRows) {
		}
		
	}

	@Override
	public String[] getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClimateData> getCityRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClimateData> getCityRowsByDate(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
