package ru.alishev.springcourse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/start")
public class StartController {

    @GetMapping()
    public String start(){
        return "people/start";
    }
}
