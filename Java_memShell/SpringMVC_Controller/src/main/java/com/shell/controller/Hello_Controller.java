package com.shell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello_Controller {

    @ResponseBody
    @RequestMapping("/hello")
    public String Hello(){
        return "Hello";
    }
}
