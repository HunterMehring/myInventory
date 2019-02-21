package org.launchcode.myInventory.controllers;

import org.launchcode.myInventory.models.*;
import org.launchcode.myInventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class homeController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ChangeDao changeDao;

    @RequestMapping(value = "")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("title", "MyInventory");
        return "index";
    }

    @RequestMapping(value = "/home")
    public String home(Model model, Authentication authentication) {
        //do a conditional statement to show when you want the starter button to show

        // why is the button not showing up. this loop is not working...
        if(categoryDao.findAllByUserId(((User)authentication.getPrincipal()).getId()).size() > 0){
            model.addAttribute("condition", "0");
            model.addAttribute("title", "MyInventory");
            return "home";
        }
        model.addAttribute("condition", "1");
        model.addAttribute("title", "MyInventory");

        return "home";
    }

    // move this somewhere secure
    @RequestMapping(value = "/home/starter")
    public String addStarterData(Authentication authentication, Model model) {

        // basically if they go to this link they will add starting data and be
        // redirected to the home page. maybe with a message saying that starter data has been added
        //more in home/index html

        List<Category> categories = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        List<Changes> changes = new ArrayList<>();

        //adding categories
        Category category1 = new Category("jacket", ((User) authentication.getPrincipal()).getId());
        categories.add(category1);

        Changes change1 = new Changes("category", category1.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change1);

        Category category2 = new Category("fleece", ((User) authentication.getPrincipal()).getId());
        categories.add(category2);

        Changes change2 = new Changes("fleece", category2.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change2);

        Category category3 = new Category("white jersey", ((User) authentication.getPrincipal()).getId());
        categories.add(category3);

        Changes change3 = new Changes("category", category3.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change3);

        Category category4 = new Category("grey jersey", ((User) authentication.getPrincipal()).getId());
        categories.add(category4);

        Changes change4 = new Changes("category", category4.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change4);

        Category category5 = new Category("white pants", ((User) authentication.getPrincipal()).getId());
        categories.add(category5);

        Changes change5 = new Changes("category", category5.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change5);

        Category category6 = new Category("grey pants", ((User) authentication.getPrincipal()).getId());
        categories.add(category6);

        Changes change6 = new Changes("category", category6.getName(),
                "added", ((User)authentication.getPrincipal()).getId());
        changes.add(change6);

        //adding items
        Item item1 = new Item(category1, 1, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item1);

        Changes change7 = new Changes("item", item1.getCategory().getName(),
                "added", item1.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change7);

        Item item2 = new Item(category1, 2, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item2);

        Changes change8 = new Changes("item", item2.getCategory().getName(),
                "added", item2.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change8);

        Item item3 = new Item(category1, 3, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item3);

        Changes change9 = new Changes("item", item3.getCategory().getName(),
                "added", item3.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change9);

        Item item4 = new Item(category1, 4, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item4);

        Changes change10 = new Changes("item", item4.getCategory().getName(),
                "added", item4.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change10);

        Item item5 = new Item(category1, 5, "XL", "dirty", ((User)authentication.getPrincipal()).getId());
        items.add(item5);

        Changes change11 = new Changes("item", item5.getCategory().getName(),
                "added", item5.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change11);

        Item item6 = new Item(category1, 6, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item6);

        Changes change12 = new Changes("item", item6.getCategory().getName(),
                "added", item6.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change12);

        Item item7 = new Item(category1, 7, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item7);

        Changes change13 = new Changes("item", item7.getCategory().getName(),
                "added", item7.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change13);

        Item item8 = new Item(category1, 8, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item8);

        Changes change14 = new Changes("item", item8.getCategory().getName(),
                "added", item8.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change14);

        Item item9 = new Item(category1, 9, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item9);

        Changes change15 = new Changes("item", item9.getCategory().getName(),
                "added", item9.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change15);

        Item item10 = new Item(category1, 10, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item10);

        Changes change16 = new Changes("item", item10.getCategory().getName(),
                "added", item10.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change16);

        Item item11 = new Item(category1, 11, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item11);

        Changes change17 = new Changes("item", item11.getCategory().getName(),
                "added", item11.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change17);

        Item item12 = new Item(category1, 12, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item12);

        Changes change18 = new Changes("item", item12.getCategory().getName(),
                "added", item12.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change18);

        Item item13 = new Item(category2, 1, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item13);

        Changes change19 = new Changes("item", item13.getCategory().getName(),
                "added", item13.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change19);

        Item item14 = new Item(category2, 2, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item14);

        Changes change20 = new Changes("item", item14.getCategory().getName(),
                "added", item14.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change20);

        Item item15 = new Item(category2, 3, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item15);

        Changes change21 = new Changes("item", item15.getCategory().getName(),
                "added", item15.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change21);

        Item item16 = new Item(category2, 4, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item16);

        Changes change22 = new Changes("item", item16.getCategory().getName(),
                "added", item16.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change22);

        Item item17 = new Item(category2, 5, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item17);

        Changes change23 = new Changes("item", item17.getCategory().getName(),
                "added", item17.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change23);

        Item item18 = new Item(category2, 6, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item18);

        Changes change24 = new Changes("item", item18.getCategory().getName(),
                "added", item18.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change24);

        Item item19 = new Item(category2, 7, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item19);

        Changes change25 = new Changes("item", item19.getCategory().getName(),
                "added", item19.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change25);

        Item item20 = new Item(category2, 8, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item20);

        Changes change26 = new Changes("item", item20.getCategory().getName(),
                "added", item20.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change26);

        Item item21 = new Item(category2, 9, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item21);

        Changes change27 = new Changes("item", item21.getCategory().getName(),
                "added", item21.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change27);

        Item item22 = new Item(category2, 10, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item22);

        Changes change28 = new Changes("item", item22.getCategory().getName(),
                "added", item22.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change28);

        Item item23 = new Item(category3, 1, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item23);

        Changes change29 = new Changes("item", item23.getCategory().getName(),
                "added", item23.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change29);

        Item item24 = new Item(category3, 2, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item24);

        Changes change30 = new Changes("item", item24.getCategory().getName(),
                "added", item24.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change30);

        Item item25 = new Item(category3, 3, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item25);

        Changes change31 = new Changes("item", item25.getCategory().getName(),
                "added", item25.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change31);

        Item item26 = new Item(category3, 4, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item26);

        Changes change32 = new Changes("item", item26.getCategory().getName(),
                "added", item26.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change32);

        Item item27 = new Item(category3, 5, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item27);

        Changes change33 = new Changes("item", item27.getCategory().getName(),
                "added", item27.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change33);

        Item item28 = new Item(category3, 6, "XL", "Stain", ((User)authentication.getPrincipal()).getId());
        items.add(item28);

        Changes change34 = new Changes("item", item28.getCategory().getName(),
                "added", item28.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change34);

        Item item29 = new Item(category3, 7, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item29);

        Changes change35 = new Changes("item", item29.getCategory().getName(),
                "added", item29.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change35);

        Item item30 = new Item(category3, 8, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item30);

        Changes change36 = new Changes("item", item30.getCategory().getName(),
                "added", item30.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change36);

        Item item31 = new Item(category3, 9, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item31);

        Changes change37 = new Changes("item", item31.getCategory().getName(),
                "added", item31.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change37);

        Item item32 = new Item(category3, 10, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item32);

        Changes change38 = new Changes("item", item32.getCategory().getName(),
                "added", item32.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change38);

        Item item33 = new Item(category4, 1, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item33);

        Changes change39 = new Changes("item", item33.getCategory().getName(),
                "added", item33.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change39);

        Item item34 = new Item(category4, 2, "M", "", ((User)authentication.getPrincipal()).getId());
        items.add(item34);

        Changes change40 = new Changes("item", item34.getCategory().getName(),
                "added", item34.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change40);

        Item item35 = new Item(category4, 3, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item35);

        Changes change41 = new Changes("item", item35.getCategory().getName(),
                "added", item35.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change41);

        Item item36 = new Item(category4, 4, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item36);

        Changes change42 = new Changes("item", item36.getCategory().getName(),
                "added", item36.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change42);

        Item item37 = new Item(category4, 5, "L", "", ((User)authentication.getPrincipal()).getId());
        items.add(item37);

        Changes change43 = new Changes("item", item37.getCategory().getName(),
                "added", item37.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change43);

        Item item38 = new Item(category4, 6, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item38);

        Changes change44 = new Changes("item", item38.getCategory().getName(),
                "added", item38.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change44);

        Item item39 = new Item(category4, 7, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item39);

        Changes change45 = new Changes("item", item39.getCategory().getName(),
                "added", item39.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change45);

        Item item40 = new Item(category4, 8, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item40);

        Changes change46 = new Changes("item", item40.getCategory().getName(),
                "added", item40.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change46);

        Item item41 = new Item(category4, 9, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item41);

        Changes change47 = new Changes("item", item41.getCategory().getName(),
                "added", item41.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change47);

        Item item42 = new Item(category4, 10, "XL", "", ((User)authentication.getPrincipal()).getId());
        items.add(item42);

        Changes change48 = new Changes("item", item42.getCategory().getName(),
                "added", item42.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change48);

        Item item43 = new Item(category5, 1, "32-30", "", ((User)authentication.getPrincipal()).getId());
        items.add(item43);

        Changes change49 = new Changes("item", item43.getCategory().getName(),
                "added", item43.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change49);

        Item item44 = new Item(category5, 2, "32-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item44);

        Changes change50 = new Changes("item", item44.getCategory().getName(),
                "added", item44.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change50);

        Item item45 = new Item(category5, 3, "34-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item45);

        Changes change51 = new Changes("item", item45.getCategory().getName(),
                "added", item45.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change51);

        Item item46 = new Item(category5, 4, "34-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item46);

        Changes change52 = new Changes("item", item46.getCategory().getName(),
                "added", item46.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change52);

        Item item47 = new Item(category5, 5, "34-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item47);

        Changes change53 = new Changes("item", item47.getCategory().getName(),
                "added", item47.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change53);

        Item item48 = new Item(category5, 6, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item48);

        Changes change54 = new Changes("item", item48.getCategory().getName(),
                "added", item48.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change54);

        Item item49 = new Item(category5, 7, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item49);

        Changes change55 = new Changes("item", item49.getCategory().getName(),
                "added", item49.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change55);

        Item item50 = new Item(category5, 8, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item50);

        Changes change56 = new Changes("item", item50.getCategory().getName(),
                "added", item50.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change56);

        Item item51 = new Item(category5, 9, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item51);

        Changes change57 = new Changes("item", item51.getCategory().getName(),
                "added", item51.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change57);

        Item item52 = new Item(category5, 10, "36-36", "", ((User)authentication.getPrincipal()).getId());
        items.add(item52);

        Changes change58 = new Changes("item", item52.getCategory().getName(),
                "added", item52.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change58);

        Item item53 = new Item(category6, 1, "32-30", "", ((User)authentication.getPrincipal()).getId());
        items.add(item53);

        Changes change59 = new Changes("item", item53.getCategory().getName(),
                "added", item53.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change59);

        Item item54 = new Item(category6, 2, "32-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item54);

        Changes change60 = new Changes("item", item54.getCategory().getName(),
                "added", item54.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change60);

        Item item55 = new Item(category6, 3, "34-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item55);

        Changes change61 = new Changes("item", item55.getCategory().getName(),
                "added", item55.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change61);

        Item item56 = new Item(category6, 4, "34-32", "", ((User)authentication.getPrincipal()).getId());
        items.add(item56);

        Changes change62 = new Changes("item", item56.getCategory().getName(),
                "added", item56.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change62);

        Item item57 = new Item(category6, 5, "34-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item57);

        Changes change63 = new Changes("item", item57.getCategory().getName(),
                "added", item57.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change63);

        Item item58 = new Item(category6, 6, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item58);

        Changes change64 = new Changes("item", item58.getCategory().getName(),
                "added", item58.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change64);

        Item item59 = new Item(category6, 7, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item59);

        Changes change65 = new Changes("item", item59.getCategory().getName(),
                "added", item59.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change65);

        Item item60 = new Item(category6, 8, "36-34", "", ((User)authentication.getPrincipal()).getId());
        items.add(item60);

        Changes change66 = new Changes("item", item60.getCategory().getName(),
                "added", item60.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change66);

        Item item61 = new Item(category6, 9, "36-34", "missing", ((User)authentication.getPrincipal()).getId());
        items.add(item61);

        Changes change67 = new Changes("item", item61.getCategory().getName(),
                "added", item61.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change67);

        Item item62 = new Item(category6, 10, "36-36", "", ((User)authentication.getPrincipal()).getId());
        items.add(item62);

        Changes change68 = new Changes("item", item62.getCategory().getName(),
                "added", item62.getNumber(), ((User)authentication.getPrincipal()).getId());
        changes.add(change68);

        // adding tasks
        Task task1 = new Task("spring payment", ((User) authentication.getPrincipal()).getId());
        tasks.add(task1);

        Changes change69 = new Changes("task", task1.getName(),
                "added");
        change69.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change69);

        Task task2 = new Task("prof letters", ((User) authentication.getPrincipal()).getId());
        tasks.add(task2);

        Changes change70 = new Changes("task", task2.getName(),
                "added");
        change70.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change70);

        //adding players//////////////////////////////////////////
        Player player1 = new Player("david wright", 5, "SR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player1);

        Changes change71 = new Changes("players", player1.getName(), "",
                "added", player1.getNumber());
        change71.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change71);

        Player player2 = new Player("yadier molina", 4, "JR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player2);

        Changes change72 = new Changes("players", player2.getName(), "",
                "added", player2.getNumber());
        change72.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change72);

        Player player3 = new Player("mike trout", 24, "SO", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player3);

        Changes change73 = new Changes("players", player3.getName(), "",
                "added", player3.getNumber());
        change73.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change73);

        Player player4 = new Player("mookie betts", 50, "FR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player4);

        Changes change74 = new Changes("players", player4.getName(), "",
                "added", player4.getNumber());
        change74.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change74);

        Player player5 = new Player("nolan arenado", 28, "SO", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player5);

        Changes change75 = new Changes("players", player5.getName(), "",
                "added", player5.getNumber());
        change75.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change75);

        Player player6 = new Player("jacob degrom", 48, "SO", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player6);

        Changes change76 = new Changes("players", player6.getName(), "",
                "added", player6.getNumber());
        change76.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change76);

        Player player7 = new Player("clayton kershaw", 22, "JR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player7);

        Changes change77 = new Changes("players", player7.getName(), "",
                "added", player7.getNumber());
        change77.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change77);

        Player player8 = new Player("edwin díaz", 39, "FR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player8);

        Changes change78 = new Changes("players", player8.getName(), "",
                "added", player8.getNumber());
        change78.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change78);

        Player player9 = new Player("willie mays", 24, "SR", "",
                ((User) authentication.getPrincipal()).getId());
        players.add(player9);

        Changes change79 = new Changes("players", player9.getName(), "",
                "added", player9.getNumber());
        change79.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change79);

        //giving items to players
        player1.addItem(item1);
        item1.setisInventory(false);

        Changes change80 = new Changes("players", player1.getName(), item1.getCategory().getName(),
                "given", item1.getNumber());
        change80.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change80);


        player1.addItem(item13);
        item13.setisInventory(false);
        Changes change81 = new Changes("players", player1.getName(), item13.getCategory().getName(),
                "given", item13.getNumber());
        change81.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change81);

        player1.addItem(item23);
        item23.setisInventory(false);

        Changes change82 = new Changes("players", player1.getName(), item23.getCategory().getName(),
                "given", item23.getNumber());
        change82.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change82);

        player1.addItem(item33);
        item33.setisInventory(false);

        Changes change83 = new Changes("players", player1.getName(), item33.getCategory().getName(),
                "given", item33.getNumber());
        change83.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change83);

        player1.addItem(item43);
        item43.setisInventory(false);

        Changes change84 = new Changes("players", player1.getName(), item43.getCategory().getName(),
                "given", item43.getNumber());
        change84.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change84);

        player1.addItem(item53);
        item53.setisInventory(false);

        Changes change85 = new Changes("players", player1.getName(), item53.getCategory().getName(),
                "given", item53.getNumber());
        change85.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change85);

        player2.addItem(item2);
        item2.setisInventory(false);

        Changes change86 = new Changes("players", player2.getName(), item2.getCategory().getName(),
                "given", item2.getNumber());
        change86.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change86);

        player2.addItem(item14);
        item14.setisInventory(false);

        Changes change87 = new Changes("players", player2.getName(), item14.getCategory().getName(),
                "given", item14.getNumber());
        change87.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change87);

        player2.addItem(item24);
        item24.setisInventory(false);

        Changes change88 = new Changes("players", player2.getName(), item24.getCategory().getName(),
                "given", item24.getNumber());
        change88.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change88);

        player2.addItem(item34);
        item34.setisInventory(false);

        Changes change89 = new Changes("players", player2.getName(), item34.getCategory().getName(),
                "given", item34.getNumber());
        change89.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change89);

        player2.addItem(item44);
        item44.setisInventory(false);


        Changes change90 = new Changes("players", player2.getName(), item44.getCategory().getName(),
                "given", item44.getNumber());
        change90.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change90);

        player2.addItem(item54);
        item54.setisInventory(false);

        Changes change91 = new Changes("players", player2.getName(), item54.getCategory().getName(),
                "given", item54.getNumber());
        change91.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change91);

        player3.addItem(item3);
        item3.setisInventory(false);

        Changes change92 = new Changes("players", player3.getName(), item3.getCategory().getName(),
                "given", item3.getNumber());
        change92.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change92);

        player3.addItem(item15);
        item35.setisInventory(false);

        Changes change93 = new Changes("players", player3.getName(), item15.getCategory().getName(),
                "given", item15.getNumber());
        change93.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change93);

        player3.addItem(item25);
        item25.setisInventory(false);

        Changes change94 = new Changes("players", player3.getName(), item25.getCategory().getName(),
                "given", item25.getNumber());
        change94.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change94);

        player3.addItem(item35);
        item35.setisInventory(false);

        Changes change95 = new Changes("players", player3.getName(), item35.getCategory().getName(),
                "given", item35.getNumber());
        change95.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change95);

        player3.addItem(item45);
        item45.setisInventory(false);

        Changes change96 = new Changes("players", player3.getName(), item45.getCategory().getName(),
                "given", item45.getNumber());
        change96.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change96);

        player3.addItem(item55);
        item55.setisInventory(false);

        Changes change97 = new Changes("players", player3.getName(), item55.getCategory().getName(),
                "given", item55.getNumber());
        change97.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change97);

        player4.addItem(item4);
        item4.setisInventory(false);

        Changes change98 = new Changes("players", player4.getName(), item4.getCategory().getName(),
                "given", item4.getNumber());
        change98.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change98);

        player4.addItem(item16);
        item16.setisInventory(false);

        Changes change99 = new Changes("players", player4.getName(), item16.getCategory().getName(),
                "given", item16.getNumber());
        change99.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change99);

        player4.addItem(item26);
        item26.setisInventory(false);

        Changes change100 = new Changes("players", player4.getName(), item26.getCategory().getName(),
                "given", item26.getNumber());
        change100.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change100);

        player4.addItem(item36);
        item36.setisInventory(false);

        Changes change101 = new Changes("players", player4.getName(), item36.getCategory().getName(),
                "given", item36.getNumber());
        change101.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change101);

        player4.addItem(item46);
        item46.setisInventory(false);

        Changes change102 = new Changes("players", player4.getName(), item46.getCategory().getName(),
                "given", item46.getNumber());
        change102.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change102);

        player4.addItem(item56);
        item56.setisInventory(false);

        Changes change103 = new Changes("players", player4.getName(), item56.getCategory().getName(),
                "given", item56.getNumber());
        change103.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change103);

        player5.addItem(item5);
        item5.setisInventory(false);

        Changes change104 = new Changes("players", player5.getName(), item5.getCategory().getName(),
                "given", item5.getNumber());
        change104.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change104);

        player5.addItem(item17);
        item17.setisInventory(false);

        Changes change105 = new Changes("players", player5.getName(), item17.getCategory().getName(),
                "given", item17.getNumber());
        change105.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change105);

        player5.addItem(item27);
        item27.setisInventory(false);

        Changes change106 = new Changes("players", player5.getName(), item27.getCategory().getName(),
                "given", item27.getNumber());
        change106.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change106);

        player5.addItem(item37);
        item37.setisInventory(false);

        Changes change107 = new Changes("players", player5.getName(), item37.getCategory().getName(),
                "given", item37.getNumber());
        change107.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change107);

        player5.addItem(item47);
        item47.setisInventory(false);

        Changes change108 = new Changes("players", player5.getName(), item47.getCategory().getName(),
                "given", item47.getNumber());
        change108.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change108);

        player5.addItem(item57);
        item57.setisInventory(false);

        Changes change109 = new Changes("players", player5.getName(), item57.getCategory().getName(),
                "given", item57.getNumber());
        change109.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change109);

        player6.addItem(item6);
        item6.setisInventory(false);

        Changes change110 = new Changes("players", player6.getName(), item6.getCategory().getName(),
                "given", item6.getNumber());
        change110.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change110);

        player6.addItem(item18);
        item18.setisInventory(false);

        Changes change111 = new Changes("players", player6.getName(), item18.getCategory().getName(),
                "given", item18.getNumber());
        change111.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change111);

        player6.addItem(item28);
        item28.setisInventory(false);

        Changes change112 = new Changes("players", player6.getName(), item28.getCategory().getName(),
                "given", item28.getNumber());
        change112.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change112);

        player6.addItem(item38);
        item38.setisInventory(false);

        Changes change113 = new Changes("players", player6.getName(), item38.getCategory().getName(),
                "given", item38.getNumber());
        change113.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change113);

        player6.addItem(item48);
        item48.setisInventory(false);

        Changes change114 = new Changes("players", player6.getName(), item48.getCategory().getName(),
                "given", item48.getNumber());
        change114.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change114);

        player6.addItem(item58);
        item58.setisInventory(false);

        Changes change115 = new Changes("players", player6.getName(), item58.getCategory().getName(),
                "given", item58.getNumber());
        change115.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change115);

        player7.addItem(item7);
        item7.setisInventory(false);

        Changes change116 = new Changes("players", player7.getName(), item7.getCategory().getName(),
                "given", item7.getNumber());
        change116.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change116);

        player7.addItem(item19);
        item19.setisInventory(false);

        Changes change117 = new Changes("players", player7.getName(), item19.getCategory().getName(),
                "given", item19.getNumber());
        change117.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change117);

        player7.addItem(item29);
        item29.setisInventory(false);

        Changes change118 = new Changes("players", player7.getName(), item29.getCategory().getName(),
                "given", item29.getNumber());
        change118.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change118);

        player7.addItem(item39);
        item39.setisInventory(false);

        Changes change119 = new Changes("players", player7.getName(), item39.getCategory().getName(),
                "given", item39.getNumber());
        change119.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change119);

        player7.addItem(item49);
        item49.setisInventory(false);

        Changes change120 = new Changes("players", player7.getName(), item49.getCategory().getName(),
                "given", item49.getNumber());
        change120.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change120);

        player7.addItem(item59);
        item59.setisInventory(false);

        Changes change121 = new Changes("players", player7.getName(), item59.getCategory().getName(),
                "given", item59.getNumber());
        change121.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change121);

        player8.addItem(item8);
        item8.setisInventory(false);

        Changes change122 = new Changes("players", player8.getName(), item8.getCategory().getName(),
                "given", item8.getNumber());
        change122.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change122);

        player8.addItem(item20);
        item20.setisInventory(false);

        Changes change123 = new Changes("players", player8.getName(), item20.getCategory().getName(),
                "given", item20.getNumber());
        change123.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change123);

        player8.addItem(item30);
        item30.setisInventory(false);

        Changes change124 = new Changes("players", player8.getName(), item30.getCategory().getName(),
                "given", item30.getNumber());
        change124.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change124);

        player8.addItem(item40);
        item40.setisInventory(false);

        Changes change125 = new Changes("players", player8.getName(), item40.getCategory().getName(),
                "given", item40.getNumber());
        change125.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change125);

        player8.addItem(item50);
        item50.setisInventory(false);

        Changes change126 = new Changes("players", player8.getName(), item50.getCategory().getName(),
                "given", item50.getNumber());
        change126.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change126);

        player8.addItem(item60);
        item60.setisInventory(false);

        Changes change127 = new Changes("players", player8.getName(), item60.getCategory().getName(),
                "given", item60.getNumber());
        change127.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change127);

        player9.addItem(item9);
        item9.setisInventory(false);

        Changes change128 = new Changes("players", player9.getName(), item9.getCategory().getName(),
                "given", item9.getNumber());
        change128.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change128);

        player9.addItem(item21);
        item21.setisInventory(false);

        Changes change129 = new Changes("players", player9.getName(), item21.getCategory().getName(),
                "given", item21.getNumber());
        change129.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change129);

        player9.addItem(item31);
        item31.setisInventory(false);

        Changes change130 = new Changes("players", player9.getName(), item31.getCategory().getName(),
                "given", item31.getNumber());
        change130.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change130);

        player9.addItem(item41);
        item41.setisInventory(false);

        Changes change131 = new Changes("players", player9.getName(), item41.getCategory().getName(),
                "given", item41.getNumber());
        change131.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change131);

        player9.addItem(item51);
        item51.setisInventory(false);

        Changes change132 = new Changes("players", player9.getName(), item51.getCategory().getName(),
                "given", item51.getNumber());
        change132.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change132);

        player9.addItem(item61);
        item61.setisInventory(false);

        Changes change133 = new Changes("players", player9.getName(), item61.getCategory().getName(),
                "given", item61.getNumber());
        change133.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change133);
//
        //giving players tasks
        player1.addTask(task1);

        Changes change134 = new Changes("task",task1.getName(),
                "completed");
        change134.setName(player1.getName());
        change134.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change134);

        player2.addTask(task1);

        Changes change135 = new Changes("task",task1.getName(),
                "completed");
        change135.setName(player2.getName());
        change135.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change135);

        player8.addTask(task2);

        Changes change136 = new Changes("task",task2.getName(),
                "completed");
        change136.setName(player8.getName());
        change136.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change136);

        player9.addTask(task2);

        Changes change137 = new Changes("task",task2.getName(),
                "completed");
        change137.setName(player9.getName());
        change137.setUserId(((User)authentication.getPrincipal()).getId());
        changes.add(change137);

        //categories works, players doesnt. its probably with player items


        for (Category category : categories){
            categoryDao.save(category);
        }

        for (Task task : tasks) {
            taskDao.save(task);
        }

        for(Item item : items) {
            itemDao.save(item);
        }
        for (Changes change: changes) {
            changeDao.save(change);
        }


        for (Player player : players) {
            playerDao.save(player);
        }

        return "redirect:/home";

    }

}
