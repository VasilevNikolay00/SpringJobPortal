package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.entity.JobActivity;
import com.recruitDemo.recruitDemo.entity.JobSeekerApply;
import com.recruitDemo.recruitDemo.entity.RecruiterProfile;
import com.recruitDemo.recruitDemo.entity.User;
import com.recruitDemo.recruitDemo.repository.RecruiterProfileRepository;
import com.recruitDemo.recruitDemo.repository.UserRepository;
import com.recruitDemo.recruitDemo.services.RecruiterProfileService;
import com.recruitDemo.recruitDemo.util.FileUploadUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/recruiter-profile")
public class RecruiterController {
    private final UserRepository userRepository;
    private final RecruiterProfileService recruiterProfileService;

    public RecruiterController(UserRepository userRepository, RecruiterProfileService recruiterProfileService) {
        this.userRepository = userRepository;
        this.recruiterProfileService = recruiterProfileService;
    }

    @GetMapping("/")
    public String recruiterProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            User user =  userRepository.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user."));
            Optional<RecruiterProfile> recruiterProfile = recruiterProfileService.getOne(user.getUserId());

            recruiterProfile.ifPresent(profile -> model.addAttribute("profile", profile));
        }

        return "recruiter_profile";
    }

    @PostMapping("/addNew")
    public String addNewRecruiterProfile(RecruiterProfile recruiterProfile, @RequestParam("image")MultipartFile multipartFile,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            User user =  userRepository.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user."));
            recruiterProfile.setUserId(user);
            recruiterProfile.setUserAccountId(user.getUserId());
        }

        model.addAttribute("profile",recruiterProfile);

        String fileName="";

        if(!Objects.equals(multipartFile.getOriginalFilename(), "")){
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            recruiterProfile.setProfilePhoto(fileName);
        }

        RecruiterProfile saveUser = recruiterProfileService.addNew(recruiterProfile);

        String uploadDir = "photos/recruiter/"+ recruiterProfile.getUserAccountId();

        try {
            FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/dashboard/";
    }



}
