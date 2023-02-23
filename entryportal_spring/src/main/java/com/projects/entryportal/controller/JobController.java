package com.projects.entryportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projects.entryportal.model.Job;
import com.projects.entryportal.repository.JobRepository;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/jobs")
    public ResponseEntity <List <Job>> getAllJobs(@RequestParam(required = false) String posting){
        try{
            List<Job> jobs = new ArrayList<Job>();
            // if not looking for a specific job return all of postings
            if(posting == null){
                jobRepository.findAll().forEach(jobs::add);
            }
            else{
                jobRepository.findByPostingContaining(posting).forEach(jobs::add);
            }
            if(jobs.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(jobs,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") String id){
        Optional<Job> jobData = jobRepository.findById(id);

        if(jobData.isPresent()){
            return new ResponseEntity<>(jobData.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> postJob(@RequestBody Job job){
        try{
            Job _job = jobRepository.save(new Job(job.getPosting(), job.getCompany(),job.getDescription(), job.isActive()));
            return new ResponseEntity<>(_job, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updatedJob(@PathVariable("id") String id, @RequestBody Job job){
        Optional<Job> jobData = jobRepository.findById(id);

        if(jobData.isPresent()){
            Job _job = jobData.get();
            _job.setPosting(job.getPosting());
            _job.setCompany(job.getCompany());
            _job.setDescription(job.getDescription());
            _job.setActive(job.isActive());
            return new ResponseEntity<>(jobRepository.save(_job),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // will implement in the future
//    @DeleteMapping("/jobs/{id}")
//    public ResponseEntity<Job> deleteJob(@PathVariable("id") String id){
//
//    }
//




    
}
