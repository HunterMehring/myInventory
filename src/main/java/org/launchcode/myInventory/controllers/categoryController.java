package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.CategoryDao;
import org.launchcode.myInventory.models.data.ChangeDao;
import org.launchcode.myInventory.models.data.ItemDao;
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

@Controller
@RequestMapping("category")
public class categoryController {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ChangeDao changeDao;

    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {

        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        model.addAttribute("title", "Categories");
        return "category/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("title", "Add Category");
        model.addAttribute("other", ""); // this needs to change

        return "/category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors, Authentication authentication) {
        if(errors.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("title", "Add Category");
            model.addAttribute("other", "");

            return "/category/add";
        }

        String categoryName = category.getName().toLowerCase();
        for (Category aCategory : categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            if (aCategory.getName().equals(categoryName)) {
                model.addAttribute("category", category);
                model.addAttribute("title", "Add Category");
                model.addAttribute("other", "you already have that category");

                return "/category/add";
            }
        }

        // use constructor here
        category.setName(categoryName.toLowerCase());
        category.setUserId(((User)authentication.getPrincipal()).getId());
        categoryDao.save(category);
        Changes newChange = new Changes("category", category.getName(), "added");
        newChange.setMyDate(newChange.getMyDate()); // might not be necessary
        newChange.setUserId(((User)authentication.getPrincipal()).getId());
        //to be able to make longer strings
        changeDao.save(newChange);
        return "redirect:/item/";
    }
    //so the user doesn't accidentally remove a category (adds an extra click)
    @RequestMapping(value = "remove")
    public String displayRemove(Model model, Authentication authentication) {
        model.addAttribute("categories",
                categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()));
        model.addAttribute("title", "Remove Categories");

        return "category/remove";
    }

    @RequestMapping(value = "remove-category/{categoryId}")
    public String removeCategory(@PathVariable int categoryId, Authentication authentication) {
        for (Item item : itemDao.findAllByUserId(((User)authentication.getPrincipal()).getId())) {
            for (Player player : item.getPlayers()){
                player.removeItem(item);
            }
            if(item.getCategory().getId() == categoryId){
                itemDao.delete(item);
            }
        }
        Category category = categoryDao.findOne(categoryId);
        Changes newChange = new Changes("category", category.getName(), "removed");
        newChange.setMyDate(newChange.getMyDate());
        changeDao.save(newChange);
        categoryDao.delete(categoryId);
        return "redirect:/category";
    }
}
