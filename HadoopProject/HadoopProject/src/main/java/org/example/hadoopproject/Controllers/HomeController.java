package org.example.hadoopproject.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;


public class HomeController {
    @RequestMapping("/")
    public String getHomePage(){
        return "index";
    }
}
