package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.dto.JobActivityDTO;
import com.recruitDemo.recruitDemo.dto.JobLocationDTO;
import com.recruitDemo.recruitDemo.dto.JobPostCreateDTO;
import com.recruitDemo.recruitDemo.dto.SearchJobActivityDTO;
import com.recruitDemo.recruitDemo.services.JobActivityService;
import com.recruitDemo.recruitDemo.services.JobLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobActivityRestController {

    private final JobActivityService jobActivityService;
    private final JobLocationService jobLocationService;


    @Autowired
    public JobActivityRestController(JobActivityService jobActivityService, JobLocationService jobLocationService) {
        this.jobActivityService = jobActivityService;
        this.jobLocationService = jobLocationService;
    }

    @GetMapping("")
    public String baseResponse(){
        return "Job portal written using Java Spring Boot.";
    }

    @GetMapping("/{id}")
    public JobActivityDTO getJobByID(@PathVariable("id") int id){
        return jobActivityService.getOneRest(id);
    }

    @GetMapping("/all")
    public List<JobActivityDTO> getAllJobs(){
        return jobActivityService.getAllRest();
    }

    @GetMapping("/")
    public List<JobActivityDTO> searchByBody(@Validated @RequestBody SearchJobActivityDTO request){

        return jobActivityService.search(request);
    }

    @GetMapping("/location")
    public JobLocationDTO requestLocation(@Validated @RequestBody JobLocationDTO request){
        return jobLocationService.getJobLocationDTO(request);
    }

   @DeleteMapping("/{id}")
   public void deleteJobById(@PathVariable("id") int id) {
       jobActivityService.removeJob(id);
   }

   @PostMapping("/{id}")
   public void updateJob(@PathVariable("id") int id, @Validated @RequestBody JobActivityDTO request) {
       jobActivityService.addNew(request,id);
   }

   @PostMapping("/")
   public void createJob(@Validated @RequestBody JobPostCreateDTO request) {
        jobActivityService.addNew(request);
    }

}
