/*package com.llacti.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}*/
package com.llacti.demo.controllers;

import com.llacti.demo.model.ViewLog;
import com.llacti.demo.repository.ViewLogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc; // MockMvc will allow us to simulate HTTP requests

	@MockBean
	private ViewLogRepository viewLogRepository; // Mocking the repository

	@Test
	public void testGetLogs() throws Exception {
		// Arrange
		ViewLog log1 = new ViewLog(1L, "Log Entry 1", "INFO");
		ViewLog log2 = new ViewLog(2L, "Log Entry 2", "ERROR");
		List<ViewLog> mockLogs = Arrays.asList(log1, log2);

		Mockito.when(viewLogRepository.findAll()).thenReturn(mockLogs);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.get("/logs")
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())  // prints the request and response
				.andExpect(status().isOk())  // expecting HTTP 200
				.andExpect(jsonPath("$.length()").value(2)) // expecting 2 items in the response
				.andExpect(jsonPath("$[0].message").value("Log Entry 1"))
				.andExpect(jsonPath("$[1].message").value("Log Entry 2"));
	}
}
