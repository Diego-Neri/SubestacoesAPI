package com.subestacoes.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Hello")

public class HelloController {
//test o endpoint kk
    @GetMapping
    public String sayHello() {
        return "Hello World";
    }

}
