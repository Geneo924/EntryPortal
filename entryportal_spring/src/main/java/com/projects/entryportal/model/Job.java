package com.projects.entryportal.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "postings")
public class Job {
    @Id
    private String id;
    // job posting name ex. new grad 23' position
    private String posting;
    // ex. google, facebook
    private String company;
    // ex. looking for new grads, with no experience
    private String description;
    // job listing still active ? either return true or false
    private boolean active;

    public Job(String id, String posting, String company, String description, boolean active) {
        this.id = id;
        this.posting = posting;
        this.company = company;
        this.description = description;
        this.active = active;
    }

    public Job(){

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
