package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/changes")
public class changesController {
    @Autowired
    private ChangeDao changeDao;

    @GetMapping
    public String index(Model model, Authentication authentication) {

        List<Changes> changes = new ArrayList<>();
        for (Changes change : changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())){
            //categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId())
            changes.add(change);
        }
        Collections.reverse(changes);// all in reverse order to have most current changes on top
        model.addAttribute("changes", changes);
        model.addAttribute("title", "All Changes");

        return "changes/index";
    }

    @RequestMapping("/categories")
    public String categoryChanges(Model model, Authentication authentication) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (change.getType().equals("category")) {
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Category Changes");
        return "changes/categories";
    }

    @RequestMapping("/items")
    public String itemChanges(Model model, Authentication authentication) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (change.getType().equals("item")) {
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Inventory Changes");
        return "changes/items";
    }

    @RequestMapping("/player-items")
    public String playerItemChanges(Model model, Authentication authentication) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (change.getType().equals("players") && !change.getItem().equals("")){
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Player-Items Changes");
        return "changes/player-items";
    }

    @RequestMapping("/players")
    public String playerChanges(Model model, Authentication authentication) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (change.getType().equals("players") && change.getItem().equals("")) {
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Player Changes");
        return "changes/players";
    }

    // add one for tasks
    @RequestMapping("/tasks")
    public String taskChanges(Model model, Authentication authentication) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change : changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if(change.getType().equals("task")){
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Task Changes");
        return "changes/tasks";
    }
}
