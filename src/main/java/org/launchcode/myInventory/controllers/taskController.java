package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.Changes;
import org.launchcode.myInventory.models.Player;
import org.launchcode.myInventory.models.Task;
import org.launchcode.myInventory.models.User;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.launchcode.myInventory.models.data.PlayerDao;
import org.launchcode.myInventory.models.data.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;


@Controller
@RequestMapping("tasks")
public class taskController {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private ChangeDao changeDao;

    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("title", "Tasks");
        model.addAttribute("tasks", taskDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));

        return "tasks/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model) {
        Task task = new Task();
        model.addAttribute("title", "Add Task");
        model.addAttribute("task", task);


        return "tasks/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid Task task, Errors errors, Model model,
                                 Authentication authentication) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Tasks");
            model.addAttribute("task", task);
            return "tasks/add";
        }
        task.setUserId(((User)authentication.getPrincipal()).getId());
        taskDao.save(task);
       Changes newChange = new Changes("task", task.getName(),
                "added");
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);

        return "redirect:/tasks/"; //redirect to that new task
    }

    @RequestMapping(value = "/remove")
    public String remove(Model model, Authentication authentication) {
        model.addAttribute("title", "Tasks");
        model.addAttribute("tasks", taskDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));

        return "tasks/remove";
    }

    //remove all players from task first to make sure an error doesnt happen when there are still players assigned
    @RequestMapping(value = "remove/{taskId}")
    public String processRemoveTask(@PathVariable int taskId, Authentication authentication) {
        Task task = taskDao.findOne(taskId);
        for (Player player : playerDao.findAll()) {
            for (Task aTask : player.getTasks()) {
                if (aTask.getName().equals(task.getName())) {
                    player.removeTask(aTask);
                    playerDao.save(player);
                    break;
                }
            }
        }

        Changes newChange = new Changes("task", task.getName(),
                "removed");
        newChange.setMyDate(newChange.getMyDate());
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);
        taskDao.delete(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "add-remove-players/{taskId}")
    public String addRemoveTask(@PathVariable int taskId, Model model, Authentication authentication) {
        Task task = taskDao.findOne(taskId);
        ArrayList<Player> completedPlayers = new ArrayList<>();
        ArrayList<Player> incompletePlayers = new ArrayList<>();

        for (Player player : playerDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            incompletePlayers.add(player);
            if(player.getTasks().size() > 0) {
                for (Task aTask : player.getTasks()) {
                    if (aTask.getName().equals(task.getName())) {
                        completedPlayers.add(player);
                        incompletePlayers.remove(player);
                        break;
                    }
                }
            }
        }

        model.addAttribute("completedPlayers",completedPlayers);
        model.addAttribute("incompletePlayers", incompletePlayers);
        model.addAttribute("task", task);
        model.addAttribute("title", task.getName());
        return "tasks/add-remove";
    }

    @RequestMapping(value = "add-task-player/{playerId}/{taskId}")
    public String addTaskPlayer(@PathVariable int playerId, @PathVariable int taskId, Authentication authentication) {
        Player player = playerDao.findOne(playerId);
        Task task = taskDao.findOne(taskId);
        player.addTask(task);
        playerDao.save(player);
        Changes newChange = new Changes("task",task.getName(),
                "completed");
        newChange.setName(player.getName());
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);

        return "redirect:/tasks/add-remove-players/" + taskId;
    }

    //make this available only after 2 clicks
    @RequestMapping(value = "remove-task-player/{playerId}/{taskId}")
    public String removeTaskPlayer(@PathVariable int playerId, @PathVariable int taskId, Authentication authentication){
        Player player = playerDao.findOne(playerId);
        Task task = taskDao.findOne(taskId);
        player.removeTask(task);
        playerDao.save(player);
        Changes newChange = new Changes("task", task.getName(),
                "uncompleted");
        newChange.setName(player.getName());
        newChange.setMyDate(newChange.getMyDate());
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);

        return "redirect:/tasks/add-remove-players/" + taskId;
    }
}
