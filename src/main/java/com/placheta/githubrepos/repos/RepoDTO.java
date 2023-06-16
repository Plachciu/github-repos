package com.placheta.githubrepos.repos;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

public class RepoDTO {

    private String fullName;
    @Getter
    @Setter
    private String description;
    private String cloneUrl;
    private int stars;
    private String createdAt;

    @JsonProperty("fullName")
    public String getFullName() {
        return this.fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("cloneUrl")
    public String getCloneUrl() {
        return this.cloneUrl;
    }

    @JsonProperty("clone_url")
    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    @JsonProperty("stars")
    public int getStars() {
        return this.stars;
    }

    @JsonProperty("stargazers_count")
    public void setStars(int stars) {
        this.stars = stars;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return this.createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt= createdAt;
    }
    
}
