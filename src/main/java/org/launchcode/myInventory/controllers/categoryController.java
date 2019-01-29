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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.lang.Object;

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
    public String index(Model model) {

        model.addAttribute("categories", categoryDao.findAll());
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
    public String add(Model model, @ModelAttribute @Valid Category category, Errors errors) {
        if(errors.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("title", "Add Category");
            model.addAttribute("other", "");

            return "/category/add";
        }
        for (Category aCategory : categoryDao.findAll()) {
            if (aCategory.getName().equals(category.getName())) {
                model.addAttribute("category", category);
                model.addAttribute("title", "Add Category");
                model.addAttribute("other", "you already have that category");

                return "/category/add";
            }
        }

        String categoryName = Character.toUpperCase(category.getName().charAt(0)) + category.getName().substring(1);
        category.setName(categoryName);
        categoryDao.save(category);
        Changes newChange = new Changes("category", category.getName(), "added");
        newChange.setMyDate(newChange.getMyDate()); // might not be necessary
        //to be able to make longer strings
        changeDao.save(newChange);
        return "redirect:/category/";
    }
    //so the user doesn't accidentally remove a category (adds an extra click)
    @RequestMapping(value = "remove")
    public String displayRemove(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Remove Categories");

        return "category/remove";
    }

    @RequestMapping(value = "remove-category/{categoryId}")
    public String removeCategory(@PathVariable int categoryId) {
        for (Item item : itemDao.findAll()) {
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
