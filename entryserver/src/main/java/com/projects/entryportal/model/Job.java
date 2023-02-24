package com.projects.entryportal.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "postings")
public class Job {
    @Id
    private String id;
    // job posting name ex. new grad 23' position
    @NotBlank(message = "Posting name must not be blank")
    private String posting;

    @NotBlank(message = "Company name must not be blank")
    private String company;

    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotBlank(message = "Active must not be blank")
    private boolean active;

    public Job(String posting, String company, String description, boolean active) {
        this.posting = posting;
        this.company = company;
        this.description = description;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosting() {
        return posting;
    }

    public void setPosting(String posting) {
        this.posting = posting;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
