package com.projects.entryportal.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jobs")
public class Jobs {
    @Id
    private String id;
    private String posting;
    private String company;
    private String description;
    private boolean active;

}
