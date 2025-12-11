package com.smartect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "index";
    }

    // @@ Spring Security
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/board")
    public String board(){ return "board";}
    @GetMapping("/about-us")
    public String about(){ return "about";}
}
