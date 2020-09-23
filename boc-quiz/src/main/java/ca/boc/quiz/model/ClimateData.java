package ca.boc.quiz.model;

import java.util.Date;

public class ClimateData {
	
	private String station;
	private String province;
	private Date date;
	private String tempMean;
	private String tempMin;
	private String tempMax;
	
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
