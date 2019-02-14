package org.launchcode.myInventory.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/hello")
@RestController
public class HelloResource {

    @GetMapping("all")
    public String hello(){
        return "hello Youtube";
    }

    @GetMapping("secured/all")
    public String securedHello() {
        return "Secured Hello";
    }
}
