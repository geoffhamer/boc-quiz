package ca.boc.quiz;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ca.boc.quiz.service.ClimateDataParsingServiceImpl;

@WebMvcTest
class BocQuizMVCTests {

	@MockBean
	private ClimateDataParsingServiceImpl dataService;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHeartbeat() throws Exception {
		this.mockMvc.perform(get("/heartbeat")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void testHeartbeatHello() throws Exception {
		this.mockMvc.perform(get("/heartbeat")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("hello")));;
	}

}
