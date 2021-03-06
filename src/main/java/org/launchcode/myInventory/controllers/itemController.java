package org.launchcode.myInventory.controllers;


import org.launchcode.myInventory.models.Category;
import org.launchcode.myInventory.models.Changes;
import org.launchcode.myInventory.models.Item;
import org.launchcode.myInventory.models.User;
import org.launchcode.myInventory.models.data.CategoryDao;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.launchcode.myInventory.models.data.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "item")
public class itemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ChangeDao changeDao;

    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {

        ArrayList<Item> inItems = new ArrayList<>();
        ArrayList<Item> outItems = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        for (Item item : itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            //itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId())
            items.add(item);
        }
        Collections.sort(items);

        for (Item item : items) {
            if (item.isInventory()) {
                inItems.add(item);
            } else {
                outItems.add(item);
            }
        }

        model.addAttribute("inItems", inItems);
        model.addAttribute("outItems", outItems);
        model.addAttribute("items", itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        model.addAttribute("title", "Inventory");

        return "item/index";
    }

    @RequestMapping(value = "add")
    public String displayAdditem(Model model, Authentication authentication) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        return "item/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItem(Model model,
                                    @ModelAttribute @Valid Item item,
                                    Errors errors,
                                    @RequestParam int categoryId,
                                    Authentication authentication){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories",
                    categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
            model.addAttribute("error", "");
            return "/item/add";
        }


        Category cat = categoryDao.findOne(categoryId);
        Item newItem = new Item(cat, item.getNumber(), item.getSize().toUpperCase(), item.getOther(),
                ((User) authentication.getPrincipal()).getId());

        for (Item aItem : itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId())){
            if (aItem.getCategory().getName().equals(newItem.getCategory().getName())){
                if(aItem.getNumber().equals(newItem.getNumber())){
                    model.addAttribute("title", "Add Item");
                    model.addAttribute("categories",
                            categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
                    model.addAttribute("error",
                            "an item of that category and number already exists");
                    return "/item/add";
                }
            }
        }

        itemDao.save(newItem);
        Changes newChange = new Changes("item", newItem.getCategory().getName(),
                "added", newItem.getNumber(), ((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);
        return "redirect:";
    }

    @RequestMapping(value = "remove-item/{itemId}")
    public String processRemoveItems(@PathVariable int itemId, Authentication authentication) {
        Item item = itemDao.findOne(itemId);
        Changes newChange = new Changes("item", item.getCategory().getName(),
                "removed", item.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);
        itemDao.delete(item);
        /*Must remove item from player before removing it. this functionality should be rarely used. */
        return "redirect:/item";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String displayEditForm( Model model, @PathVariable int id, Authentication authentication) {

        Item item = itemDao.findOne(id);//
        model.addAttribute(item);
        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        model.addAttribute("title", "Edit Item ");
        return "item/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int id,
                                  @ModelAttribute @Valid Item newItem,
                                  Errors errors, Model model, Authentication authentication) {
        Item aItem = itemDao.findOne(id);

        if (errors.hasErrors()) {
            model.addAttribute(aItem);
            model.addAttribute("categories",
                    categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
            model.addAttribute("title", "Edit Item");
            return "redirect:/item/edit/" + id;
        }
        aItem.setOther(newItem.getOther());
        itemDao.save(aItem);
        Changes newChange = new Changes("item", aItem.getCategory().getName(),
                "comment edited", aItem.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        changeDao.save(newChange);
        return "redirect:";

    }

}
