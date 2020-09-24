package ca.boc.quiz.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FilterDates {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date afterDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beforeDate;

	public Date getAfterDate() {
		return afterDate;
	}

	public void setAfterDate(Date afterDate) {
		this.afterDate = afterDate;
	}

	public Date getBeforeDate() {
		return beforeDate;
	}

	public void setBeforeDate(Date beforeDate) {
		this.beforeDate = beforeDate;
	}
	
	

}
