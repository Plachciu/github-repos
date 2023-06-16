package com.placheta.githubrepos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.MOVED_PERMANENTLY, reason = "The resource has been moved permanently")
public class RepoMovedPermanentlyException extends RuntimeException {
    
}
