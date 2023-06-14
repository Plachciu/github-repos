package com.placheta.githubrepos.repos;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RepoServiceImpl implements RepoService{

    private final RepoRestTemplateService repoRestTemplateService;

    @Override
    public RepoDTO getRepo(String owner, String repositoryName) {
        return repoRestTemplateService.fetchRepoInfo(owner, repositoryName);
    }

    
    
}
