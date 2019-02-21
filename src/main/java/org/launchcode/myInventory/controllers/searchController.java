package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.*;
import org.launchcode.myInventory.models.forms.SearchItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("search")
public class searchController {

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private ChangeDao changeDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;


    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("title", "Search Items");
        model.addAttribute("item", new Item());
        model.addAttribute("error", " ");
        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));

        return "search/index";
    }

    // there is a null somewhere
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processSearch(@ModelAttribute @Valid SearchItemForm searchItemForm,
                                Errors errors,
                                Model model,
                                @RequestParam int categoryId,
                                Authentication authentication) {

        if (errors.hasErrors()){
            model.addAttribute("title", "Search");
            model.addAttribute("item", searchItemForm);
            model.addAttribute("error", "");
            model.addAttribute("categories",
                    categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
            return "redirect:/search/index";
        }

        Category cat = categoryDao.findOne(categoryId);
        SearchItemForm newSearchItemForm = new SearchItemForm(searchItemForm.getNumber(), searchItemForm.getSize());

        for (Item aItem : itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId())){
            if(aItem.getCategory().getName() != null && cat.getName() != null) {
                if (aItem.getCategory().getName().equals(cat.getName())) {
                    if(aItem.getNumber() != null && newSearchItemForm.getNumber() != null) {
                        if (aItem.getNumber().equals(newSearchItemForm.getNumber())) {
                            return "redirect:/search/view/" + aItem.getId(); //something is wrong with this line
                        }
                    }
                }
            }
        }

        model.addAttribute("error", "sorry, we could'nt find that item or it does not exist");
        model.addAttribute("title", "Search");
        model.addAttribute("item", searchItemForm);
        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        return "search/index";

    }

    @RequestMapping(value = "/view/{itemId}")
    public String searchView(Model model, @PathVariable int itemId, Authentication authentication) {
        Item item = itemDao.findOne(itemId);
        ArrayList<Changes> changes = new ArrayList<>();

        for (Player player : playerDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            for (Item item1 : player.getItems()) {
                if (item1.getId() == item.getId()) {
                    model.addAttribute("player", player);
                }
            }
        }

        for (Changes change : changeDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (change.getType() != null) {
                if (change.getItem().equals(item.getCategory().getName())) {
                    if(change.getNumber() != null ) {
                        if (change.getNumber().equals(item.getNumber())) {
                            changes.add(change);
                        }
                    }
                }
            }
        }
        Collections.reverse(changes);
        model.addAttribute("changes", changes);
        model.addAttribute("item", item);
        model.addAttribute("title", "Search Result");

        return "/search/view";
    }
}
