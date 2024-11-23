package com.recruitDemo.recruitDemo.controller;

import com.recruitDemo.recruitDemo.entity.User;
import com.recruitDemo.recruitDemo.entity.UserType;
import com.recruitDemo.recruitDemo.services.UserService;
import com.recruitDemo.recruitDemo.services.UserTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserTypeService userTypeService;
    private final UserService userService;

    @Autowired
    public UserController(UserTypeService userTypeService, UserService userService) {
        this.userTypeService = userTypeService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UserType> userTypes = userTypeService.getAll();
        model.addAttribute("getAllTypes",userTypes);
        model.addAttribute("user",new User());

        return "register";
    }

    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/";
    }

    @PostMapping("/register/new")
    public String newRegister(@Valid User user,Model model){
        Optional<User> optionalUser = userService.getUserByEmail(user.getEmail());

        if(optionalUser.isPresent()){
            model.addAttribute("error", "Email already exists.");
            List<UserType> userTypes = userTypeService.getAll();
            model.addAttribute("getAllTypes",userTypes);
            model.addAttribute("user",new User());
            return "redirect:/dashboard/";
        }
        user = userService.addNew(user);
        System.out.println(user.toString());
        return "redirect:/dashboard/";
    }

}
