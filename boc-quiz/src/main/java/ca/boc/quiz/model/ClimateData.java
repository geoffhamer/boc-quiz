package ca.boc.quiz.model;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class ClimateData {
	
	private int index;
	
	@CsvBindByPosition(position = 0)
	private String station;
	
	@CsvBindByPosition(position = 1)
	private String province;

	@CsvBindByPosition(position = 2)
	@CsvDate("MM/dd/yyyy")
	private Date date;
	
	@CsvBindByPosition(position = 3)
    private String tempMean;

	@CsvBindByPosition(position = 4)
	private String tempMax;

	@CsvBindByPosition(position = 5)
	private String tempMin;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getStation() {
		return station;
	}
	
	public void setStation(String station) {
		this.station = station;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDateString() {
		
		String dateStr = "NULL";
		
		return dateStr;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTempMean() {
		return tempMean;
	}
	
	public void setTempMean(String tempMean) {
		this.tempMean = tempMean;
	}
	
	public String getTempMin() {
		return tempMin;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	
	public String getTempMax() {
		return tempMax;
	}
	
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	
	

}
