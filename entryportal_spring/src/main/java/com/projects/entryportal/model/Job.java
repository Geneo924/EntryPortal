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

}
