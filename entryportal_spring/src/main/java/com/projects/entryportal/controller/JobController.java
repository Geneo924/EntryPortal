package com.projects.entryportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.projects.entryportal.model.Job;
import com.projects.entryportal.repository.JobRepository;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/jobs")
    public ResponseEntity <List <Job>> getAllJobs(@RequestParam(required = false) String title){}

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") String id){}

    @PostMapping("/jobs")
    public ResponseEntity<Job> postJob(@RequestBody Job job){}

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updatedJob(@PathVariable("id") String id, @RequestBody Job job){}

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Job> deleteJob(@PathVariable("id") String id){}
    




    
}
