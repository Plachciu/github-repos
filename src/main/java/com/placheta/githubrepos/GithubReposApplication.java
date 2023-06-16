package com.placheta.githubrepos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.placheta.githubrepos.exceptions.TokenNotProvidedException;
import com.placheta.githubrepos.repos.Constants;

@SpringBootApplication
public class GithubReposApplication {

	public static void main(String[] args) {

		if (args.length < 1) {
            throw new TokenNotProvidedException();
        }
		Constants.setTOKEN(args[0]);

		SpringApplication.run(GithubReposApplication.class, args);
	}

}
