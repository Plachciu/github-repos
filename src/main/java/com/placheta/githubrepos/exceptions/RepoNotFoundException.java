package com.placheta.githubrepos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Invalid owner or repository name")
public class RepoNotFoundException extends RuntimeException {
    
}
