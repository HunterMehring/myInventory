package org.launchcode.myInventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "MyInventory");
        return "index";
    }
}
