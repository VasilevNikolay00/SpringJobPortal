package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.entity.ApiKey;
import com.recruitDemo.recruitDemo.entity.RecruiterProfile;
import com.recruitDemo.recruitDemo.entity.User;
import com.recruitDemo.recruitDemo.repository.UserRepository;
import com.recruitDemo.recruitDemo.services.ApiKeysService;
import com.recruitDemo.recruitDemo.services.UserService;
import com.recruitDemo.recruitDemo.util.FileUploadUtil;
import com.recruitDemo.recruitDemo.util.GenerateApiKeys;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Objects;


@Controller
@RequestMapping("/api-key")
public class ApiKeyController {
    private final UserService userService;
    private final ApiKeysService apiKeysService;

    public ApiKeyController(UserService userService, ApiKeysService apiKeysService) {
        this.userService = userService;
        this.apiKeysService = apiKeysService;
    }

    @PostMapping("/addNew")
    public String addNew(ApiKey apiKey){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            User user =  userService.getUserByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user."));
            apiKey.setUserId(user);
            apiKey.setCreationDate(new Date());
            apiKey.setAccess("Full");
            apiKey.setApiKey(GenerateApiKeys.generateApiKey());
            apiKeysService.addNew(apiKey);
        }

        return "redirect:/dashboard/";
    }

    @PostMapping("/delete")
    public String delete(ApiKey apiKey){
        apiKeysService.delete(apiKey);
        return "redirect:/dashboard/";
    }



}
