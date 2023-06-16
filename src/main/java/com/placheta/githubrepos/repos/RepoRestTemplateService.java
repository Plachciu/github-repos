package com.placheta.githubrepos.repos;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.placheta.githubrepos.exceptions.RepoForbiddenException;
import com.placheta.githubrepos.exceptions.RepoMovedPermanentlyException;
import com.placheta.githubrepos.exceptions.RepoNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RepoRestTemplateService {

    private final RestTemplate restTemplate;
    
    RepoDTO fetchRepoInfo(String owner, String repositoryName) {
        try {
            String url = buildUri(owner, repositoryName);
            ResponseEntity<RepoDTO> response = restTemplate.exchange(url, HttpMethod.GET, Constants.getEntity(), RepoDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            switch(e.getStatusCode().value()){
                case 404:
                    throw new RepoNotFoundException();
                case 403:
                    throw new RepoForbiddenException();
                case 301:
                    throw new RepoMovedPermanentlyException();
                default:
                    throw e;
            }
        }
    }

    public static String buildUri (String owner, String repositoryName) {
        return String.format(Constants.getUriPrefix(), owner, repositoryName);
    }
    
}
