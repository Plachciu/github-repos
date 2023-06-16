package com.placheta.githubrepos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "No proper authorization to access the requested content")
public class RepoForbiddenException extends RuntimeException {
    
}
