package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.Changes;
import org.launchcode.myInventory.models.Item;
import org.launchcode.myInventory.models.Player;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.launchcode.myInventory.models.data.ItemDao;
import org.launchcode.myInventory.models.data.PlayerDao;
import org.launchcode.myInventory.models.data.TaskDao;
import org.launchcode.myInventory.models.forms.AddPlayerItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "players")
public class playerController {
    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ChangeDao changeDao;

    @Autowired
    private TaskDao taskDao;

    //make this the landing page
    @RequestMapping(value = "")
    public String index(Model model) {

        List<Player> players = new ArrayList<>();
        for (Player player : playerDao.findAll()) {
            players.add(player);
        }
        Collections.sort(players);

        model.addAttribute("title", "Players");
        model.addAttribute("players", players);
        return "players/index";
    }

    @RequestMapping(value = "add")
    public String displayAddPlayer(Model model) {
        model.addAttribute("title", "Add Player");
        model.addAttribute("player", new Player());
        return "players/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String proccessAddPlayer(Model model, @ModelAttribute @Valid Player player, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Player");
            return "players/add";
        }
        playerDao.save(player);
        Changes newChange = new Changes("players", player.getName(), "",
                "added", player.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        return "redirect:/players/view/" + player.getId();
    }

    @RequestMapping(value = "view/{playerId}")
    public String viewPlayer(Model model, @PathVariable int playerId) {

        Player player = playerDao.findOne(playerId);

        model.addAttribute("title", player.getName());
        model.addAttribute("items", player.getItems());
        model.addAttribute("player", player);
        return "players/view";
    }
    //NEED to be able to sort by size after category
    @RequestMapping(value = "add-remove-item/{playerId}")
    public String addPlayerItem(Model model, @PathVariable int playerId) {

        Player player = playerDao.findOne(playerId);
        Iterable<Item> items = itemDao.findAll();
        ArrayList<Item> itemsIn = new ArrayList<>();
        for(Item item : items) {
            if(item.isInventory()) {
                itemsIn.add(item);
            }
        }
        Collections.sort(itemsIn);
        AddPlayerItemForm form = new AddPlayerItemForm(itemsIn, player);
        model.addAttribute("form", form);
        model.addAttribute("hasItems", player.getItems());

        return "players/add-remove-item";
    }

    @RequestMapping(value = "remove")
    public String displayRemoveForm(Model model) {

        Iterable<Player> players = playerDao.findAll();
        for(Player player : players) {
            if(player.getItems().size() > 0 ) {
                player.setSafe("No");
            }else {
                player.setSafe("Yes");
            }
        }
        model.addAttribute("players", players);
        model.addAttribute("title", "Remove Players");

        return "players/remove";
    }

    //*When you remove a player their items go back into the inventory
    @RequestMapping(value = "remove-player/{playerId}")
    public String processRemoveItems(@PathVariable int playerId) {
        Player player = playerDao.findOne(playerId);
        for(Item item : player.getItems()){
            item.setisInventory(true);
        }
        Changes newChange = new Changes("players", player.getName(), "",
                "removed", player.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        playerDao.delete(player);
        return "redirect:/players/remove";
    }

    @RequestMapping(value = "remove-item-from-player/{playerId}/{itemId}", method = RequestMethod.GET)
    public String removeItemfromPlayer(@PathVariable int playerId, @PathVariable int itemId) {
        Player thePlayer = playerDao.findOne(playerId);
        Item theItem = itemDao.findOne(itemId);
        thePlayer.removeItem(theItem);
        theItem.setisInventory(true);
        playerDao.save(thePlayer);

        Changes newChange = new Changes("players", thePlayer.getName(), theItem.getCategory().getName(),
                "removed", theItem.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);

        return "redirect:/players/add-remove-item/" + playerId;
    }

    @RequestMapping(value = "add-item-to-player/{playerId}/{itemId}")
    public String addItemtoPlayer(@PathVariable int playerId, @PathVariable int itemId) {

        Player thePlayer = playerDao.findOne(playerId);
        Item theItem = itemDao.findOne(itemId);
        theItem.setisInventory(false);
        thePlayer.addItem(theItem);
        playerDao.save(thePlayer);

        Changes newChange = new Changes("players", thePlayer.getName(), theItem.getCategory().getName(),
                "added", theItem.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);

        return "redirect:/players/add-remove-item/" + playerId;//make it so that there is only one item and you cannot duplicate
    }

    @RequestMapping(value = "task")
    public String displayTasks(Model model) {
        model.addAttribute("tasks", taskDao.findAll());
        model.addAttribute("title", "Tasks");

        return "players/tasks";
    }

    //just make links that help navigate places
}
