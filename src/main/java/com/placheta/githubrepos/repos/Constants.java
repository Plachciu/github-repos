package com.placheta.githubrepos.repos;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Constants {

    private static final String URI_PREFIX = "https://api.github.com/repos/%s/%s";
    private static final String GITHUB_MEDIA_TYPE = "application/vnd.github+json";
    private static HttpEntity<String> ENTITY;
    private static final String X_GITHUB_API_VERSION = "X-GitHub-Api-Version";
    private static final String API_VERSION = "2022-11-28";
    private static String TOKEN;

    private Constants(){
    }

    public static HttpEntity<String> getEntity() {
        return ENTITY;
    }

    public static String getUriPrefix() {
        return URI_PREFIX;
    }

    public static void setTOKEN(String token) {
        Constants.TOKEN = token;
        ENTITY = new HttpEntity<>("body", prepareHeaders());
    }

        // Based on this documentation: https://docs.github.com/en/rest/repos/repos?apiVersion=2022-11-28
    public static HttpHeaders prepareHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.parseMediaType(GITHUB_MEDIA_TYPE)));
        headers.setBearerAuth(TOKEN);
        headers.set(X_GITHUB_API_VERSION, API_VERSION);
        return headers;
    }


}
