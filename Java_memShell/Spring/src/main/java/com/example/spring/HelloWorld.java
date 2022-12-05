package com.example.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

    @ResponseBody
    @GetMapping ("/hello")
    public String Hello(){
        return "Hello World!";
    }
}