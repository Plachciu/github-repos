package com.placheta.githubrepos.repos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepoRestTemplateService {

    private static final String URI_PREFIX = "https://api.github.com/repos/%s/%s";
    private static final String GITHUB_MEDIA_TYPE = "application/vnd.github+json";
    private static final String TOKEN_PROPERTY_NAME = "git.token";
    private static final String TOKEN = loadToken();
    private static final HttpEntity<String> ENTITY = new HttpEntity<>("body", prepareHeaders());
    private static final String X_GITHUB_API_VERSION = "X-GitHub-Api-Version";
    private static final String API_VERSION = "2022-11-28";

    private final RestTemplate restTemplate;

    private static String loadToken() {
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            return properties.getProperty(TOKEN_PROPERTY_NAME);
        } catch (IOException io) {
            io.printStackTrace();
        }

        return null;
        
    }


    public RepoDTO fetchRepoInfo(String owner, String repositoryName) {
        try {
            String url = buildUri(owner, repositoryName);
            ResponseEntity<RepoDTO> response = restTemplate.exchange(url, HttpMethod.GET, ENTITY, RepoDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND){

            }
        }
        

        return null;
    }

    // Based on this documentation: https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28
    public static HttpHeaders prepareHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType(GITHUB_MEDIA_TYPE)));
        headers.setBearerAuth(TOKEN);
        headers.set(X_GITHUB_API_VERSION, API_VERSION);
        return headers;
    }

    public String buildUri (String owner, String repositoryName) {
        return String.format(URI_PREFIX, owner, repositoryName);
    }
    
}
