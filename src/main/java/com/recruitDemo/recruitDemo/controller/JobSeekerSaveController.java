package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.entity.*;
import com.recruitDemo.recruitDemo.services.JobActivityService;
import com.recruitDemo.recruitDemo.services.JobSeekerProfileService;
import com.recruitDemo.recruitDemo.services.JobSeekerSaveService;
import com.recruitDemo.recruitDemo.services.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class JobSeekerSaveController {

    private final UserService userService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobActivityService jobActivityService;
    private final JobSeekerSaveService jobSeekerSaveService;

    public JobSeekerSaveController(UserService userService, JobSeekerProfileService jobSeekerProfileService, JobActivityService jobActivityService, JobSeekerSaveService jobSeekerSaveService) {
        this.userService = userService;
        this.jobActivityService = jobActivityService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobSeekerSaveService = jobSeekerSaveService;
    }

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, JobSeekerSave jobSeekerSave){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            User user = userService.findByEmail(currentUsername);
            Optional<JobSeekerProfile> seekerProfile = jobSeekerProfileService.getOne(user.getUserId());
            JobActivity jobActivity = jobActivityService.getOne(id);
            if(seekerProfile.isPresent() && jobActivity!=null){
                jobSeekerSave.setJob(jobActivity);
                jobSeekerSave.setUserId(seekerProfile.get());
            }else{
                throw new RuntimeException("User not found!");
            }
            jobSeekerSaveService.addNew(jobSeekerSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-jobs/")
    public String savedJobs(Model model){

        List<JobActivity> jobActivityList = new ArrayList<>();
        Object currentUserProfile = userService.getCurrentUserProfile();
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);

        for(JobSeekerSave save : jobSeekerSaveList){
            jobActivityList.add(save.getJob());
        }

        model.addAttribute("jobPost", jobActivityList);
        model.addAttribute("user", currentUserProfile);
        return "saved-jobs";
    }
}
