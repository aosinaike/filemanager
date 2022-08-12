package com.mytest.filemanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mytest.FileManagerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = FileManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileManagerApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void testUploadFiles()
			throws Exception {
		MockMultipartFile file
				= new MockMultipartFile(
				"files",
				"hello.txt",
				MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes()
		);

		MockMvc mockMvc
				= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart("/upload").file(file))
				.andExpect(status().isOk());
	}
}
