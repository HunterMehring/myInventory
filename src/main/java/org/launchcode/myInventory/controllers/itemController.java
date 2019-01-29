package org.launchcode.myInventory.controllers;


import org.launchcode.myInventory.models.Category;
import org.launchcode.myInventory.models.Changes;
import org.launchcode.myInventory.models.Item;
import org.launchcode.myInventory.models.data.CategoryDao;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.launchcode.myInventory.models.data.ItemDao;
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
@RequestMapping(value = "item")
public class itemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ChangeDao changeDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        // then sort by category(done), then size
        // change names of in inventory and out of inventory


        ArrayList<Item> inItems = new ArrayList<>();
        ArrayList<Item> outItems = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        for (Item item : itemDao.findAll()) {
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
        model.addAttribute("title", "Inventory");

        return "item/index";
    }

    @RequestMapping(value = "add")
    public String displayAdditem(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories", categoryDao.findAll());
        return "item/add";
    }
    //make it so that errors actually pass correctly and that if you enter a item with the same
    // category and number it gives you an error
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItem(Model model,
                                    @ModelAttribute @Valid Item item,
                                    Errors errors,
                                    @RequestParam int categoryId){
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", categoryDao.findAll());
            return "/item/add";
        }

        //add functionality to add multiple items

        Category cat = categoryDao.findOne(categoryId);
        Item newItem = new Item(cat, item.getNumber(), item.getSize().toUpperCase(), item.getOther());
        itemDao.save(newItem);
        Changes newChange = new Changes("inventory", newItem.getCategory().getName(),
                "added", newItem.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        return "redirect:";
    }

    @RequestMapping(value = "remove-item/{itemId}")
    public String processRemoveItems(@PathVariable int itemId) {
        Item item = itemDao.findOne(itemId);
        Changes newChange = new Changes("inventory", item.getCategory().getName(),
                "removed", item.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        itemDao.delete(item);
        /*Must remove item from player before removing it. this functionality should be rarely used. */
        return "redirect:/item";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String displayEditForm( Model model, @PathVariable int id) {

        Item item = itemDao.findOne(id);//
        model.addAttribute(item);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Edit Item ");
        return "item/edit";
    }

    //made it so that you can only edit the comments on an item
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int id,
                                  @ModelAttribute @Valid Item newItem,
                                  Errors errors, Model model) {
        Item aItem = itemDao.findOne(id);

        if (errors.hasErrors()) {
            model.addAttribute(aItem);
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Edit Item");
            return "redirect:/item/edit/" + id;
        }
        aItem.setOther(newItem.getOther());
        itemDao.save(aItem);
        Changes newChange = new Changes("inventory", aItem.getCategory().getName(),
                "edited", aItem.getNumber());
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        return "redirect:";

    }

}
