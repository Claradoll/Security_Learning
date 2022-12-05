package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "Hello";
    }
}
