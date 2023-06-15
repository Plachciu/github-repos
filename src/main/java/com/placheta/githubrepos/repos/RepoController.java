package com.placheta.githubrepos.repos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/repositories")
@RequiredArgsConstructor
public class RepoController {

    private final RepoService repoService;

@GetMapping("/{owner}/{repositoryname}")
public RepoDTO getRepo(
    @PathVariable @NotBlank String owner, 
    @PathVariable("repositoryname") @NotBlank String repositoryName){
    return repoService.getRepo(owner, repositoryName);
}
    
}
