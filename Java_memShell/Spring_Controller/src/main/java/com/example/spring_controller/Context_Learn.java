package com.example.spring_controller;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Context_Learn {

    @ResponseBody
    @RequestMapping("/context")
    public void Context(HttpServletRequest request, HttpServletResponse response) throws IOException {

            String springVersion = SpringVersion.getVersion();
            System.out.println(springVersion);
            String springBootVersion = SpringBootVersion.getVersion();
            System.out.println(springBootVersion);


    }
}
