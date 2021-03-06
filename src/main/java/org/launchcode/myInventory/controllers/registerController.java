package org.launchcode.myInventory.controllers;


import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.*;
import org.launchcode.myInventory.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Password;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class registerController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserDao userDao;


    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Register");
        return "register/index";
    }

    @PostMapping
    public String processAddUser(@ModelAttribute @Valid User user, Errors errors, /*@RequestParam String confPassword*/
                                 Model model){
        if (errors.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("title", "Register");
            return "register/index";
        }
        for(User aUser : userDao.findAll()){
            if (aUser.getName().equals(user.getName())) {
                model.addAttribute("error", "that name is already in use");
                model.addAttribute("user", user);
                model.addAttribute("title", "Register");
                return "register/index";
            }
        }
        //add a confirm password
        User newUser = new User(user.getName(), user.getPassword());
        customUserDetailsService.registerNewUserAccount(newUser);

        return "redirect:/login/";
    }

}
