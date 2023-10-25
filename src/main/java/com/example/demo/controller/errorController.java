package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController implements ErrorController {

    @GetMapping("/error")
    public String redirectPage(){
        return "index.html";
    }

}
