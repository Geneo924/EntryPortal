package com.projects.entryportal.repository;
import com.projects.entryportal.model.Job;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String>{

    List<Job> findByPostingContaining(String post);
    /*
    can add these later...
    findByJobDescription
    findByActive
    findByNewlyPosted

     */
}

