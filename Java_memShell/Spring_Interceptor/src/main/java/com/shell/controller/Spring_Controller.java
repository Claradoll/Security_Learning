package com.shell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Spring_Controller {

    @ResponseBody
    @RequestMapping("/login")
    public String Login(){
        return "Success!";
    }
}
