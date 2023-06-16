package com.placheta.githubrepos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placheta.githubrepos.repos.Constants;
import com.placheta.githubrepos.repos.RepoRestTemplateService;


@SpringBootTest
@AutoConfigureMockMvc
class GithubReposApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestTemplate restTemplate;

	private ObjectMapper defaultMapper = new ObjectMapper();

	private static final String VALID_OWNER = "projectlombok";
	private static final String VALID_REPOSITORY = "lombok";
	private static final String INVALID_OWNER = "project_lombok";

	private static String buildMvcUri(String owner, String repository) { 
		return String.format("/repositories/%s/%s", owner, repository); 
	}
	

	private static void isResponseEqual(JsonNode githubJson, JsonNode apiJson) {

		Assertions.assertEquals(githubJson.path("full_name").asText(), apiJson.path("fullName").asText());
		Assertions.assertEquals(githubJson.path("clone_url").asText(), apiJson.path("cloneUrl").asText());
		Assertions.assertEquals(githubJson.path("stargazers_count").asText(), apiJson.path("stars").asText());
		Assertions.assertEquals(githubJson.path("created_at").asText(), apiJson.path("createdAt").asText());
		Assertions.assertEquals(githubJson.path("description").asText(), apiJson.path("description").asText());
	}

	@Test
	public void contextLoads() {
	}


	@Test
	public void validLoadedData() throws Exception {
	// 		GIVEN
		String githubUri = RepoRestTemplateService.buildUri(VALID_OWNER, VALID_REPOSITORY);
		String apiUri = buildMvcUri(VALID_OWNER, VALID_REPOSITORY);

	//		WHEN
		JsonNode githubJson = restTemplate.exchange(githubUri, HttpMethod.GET, Constants.getEntity(), JsonNode.class).getBody();
		
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.get(apiUri))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();
				
		JsonNode apiJson = defaultMapper.readTree(contentAsString);
		
	//		THEN
		Assert.notNull(githubJson, "Github Json is missing");
		Assert.notNull(apiJson, "Api Json is missing");

		isResponseEqual(githubJson, apiJson);
	}

	@Test
	public void invalidUrlReturnsNotFound() throws Exception {
	//		GIVEN
		String invalidUri = buildMvcUri(INVALID_OWNER, VALID_REPOSITORY);

	//		WHEN
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(invalidUri)).andReturn();

	//		THEN
		Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
	}

}
