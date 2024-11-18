package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.dto.JobActivityDTO;
import com.recruitDemo.recruitDemo.services.JobActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class JobActivityRestController {

    private final JobActivityService jobActivityService;

    @Autowired
    public JobActivityRestController(JobActivityService jobActivityService) {
        this.jobActivityService = jobActivityService;
    }

    @GetMapping("")
    public String baseResponse(){
        return "Job portal written using Java Spring Boot.";
    }

    @GetMapping("/{id}")
    public JobActivityDTO getJobByID(@PathVariable("id") int id){
        return jobActivityService.getOneRest(id);
    }
}
