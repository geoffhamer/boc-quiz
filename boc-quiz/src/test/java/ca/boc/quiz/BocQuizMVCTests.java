package ca.boc.quiz;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.boc.quiz.model.ClimateData;
import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@WebMvcTest
class BocQuizMVCTests {

	@MockBean
	private ClimateDataParsingServiceImpl parsingService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHeartbeat() throws Exception {
		this.mockMvc.perform(get("/heartbeat")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testHeartbeatHello() throws Exception {
		this.mockMvc.perform( get("/heartbeat")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("hello")) );
	}

	@Test
	public void testSummary() throws Exception {
		this.mockMvc.perform( get("/summary")).andDo(print()).andExpect(status().isOk() );
	}
	
	@Test
	public void testSummaryContent() throws Exception {
		this.mockMvc.perform( get("/summary")).andDo(print()).andExpect(status().isOk() ).andExpect(content().string(containsString("City Climate Summary")) );
	}
	
	@Test
	public void testDetail() throws Exception {
		
		when( parsingService.getCityRows() ).thenReturn(getClimateData()); 
		
		this.mockMvc.perform( get("/detail?index=0")).andDo(print()).andExpect(status().isOk() ).andExpect(content().string(containsString("Ice Station Zebra")) );
	}
	
	@Test
	public void testDetailError() throws Exception {
		
		when( parsingService.getCityRows() ).thenReturn(getClimateData()); 
		
		this.mockMvc.perform( get("/detail?index=10")).andDo(print()).andExpect(status().isOk() ).andExpect(content().string(containsString("ERROR")) );
	}
	
	
	private List<ClimateData> getClimateData() {
		
		ClimateData climateData = new ClimateData();
		climateData.setIndex(0);
		climateData.setStation("Ice Station Zebra");
		climateData.setDate(new Date());
		climateData.setProvince("YK");
		climateData.setTempMax("10.5");
		climateData.setTempMin("-42");
		climateData.setTempMin("-5");
		
		ArrayList<ClimateData> climateDataList = new ArrayList<ClimateData>();
		climateDataList.add(climateData);

		return climateDataList;
	}
}
