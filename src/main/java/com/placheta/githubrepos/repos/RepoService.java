package com.placheta.githubrepos.repos;

public interface RepoService {

    public RepoDTO getRepo(String owner, String repositoryName);
    
}
