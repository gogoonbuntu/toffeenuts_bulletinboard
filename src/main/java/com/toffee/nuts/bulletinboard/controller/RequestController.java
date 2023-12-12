package com.toffee.nuts.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        return "hello";
    }

    @GetMapping("CreateNewBoard")
    public String CreateNewBoard(Model model) {
        model.addAttribute("data", "Hello!");
        return "yes";
    }
}
