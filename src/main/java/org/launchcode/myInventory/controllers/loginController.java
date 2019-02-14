package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ChangeDao changeDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private TaskDao taskDao;

    @GetMapping
    public String index(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("user", new User());
        return "login/index";
    }

    @PostMapping
    public String processLogin(){

        return "redirect:/";
    }
}
