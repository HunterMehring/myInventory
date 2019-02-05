package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.Changes;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/changes")
public class changesController {
    @Autowired
    private ChangeDao changeDao;

    @RequestMapping("")
    public String index(Model model) {

        List<Changes> changes = new ArrayList<>();
        for (Changes change : changeDao.findAll()){
            changes.add(change);
        }
        Collections.reverse(changes);// all in reverse order to have most current changes on top
        model.addAttribute("changes", changes);
        model.addAttribute("title", "All Changes");

        return "changes/index";
    }

    @RequestMapping("/categories")
    public String categoryChanges(Model model) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAll()) {
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
    public String itemChanges(Model model) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAll()) {
            if (change.getType().equals("inventory")) {
                changes.add(change);
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("title", "Inventory Changes");
        return "changes/items";
    }

    @RequestMapping("/player-items")
    public String playerItemChanges(Model model) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAll()) {
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
    public String playerChanges(Model model) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change: changeDao.findAll()) {
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
    public String taskChanges(Model model) {

        ArrayList<Changes> changes = new ArrayList<>();

        for (Changes change : changeDao.findAll()) {
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
