package com.placheta.githubrepos.exceptions;

public class TokenNotProvidedException extends RuntimeException {

      public TokenNotProvidedException() {
            super("Provide token to get access to Github API, details in README.md file.");
        }
        
}
