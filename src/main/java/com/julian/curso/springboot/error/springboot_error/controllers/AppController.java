package com.julian.curso.springboot.error.springboot_error.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/app")
    public String index(){
        // int valor = 100/0;
        int valor = Integer.parseInt("10XF");
        System.out.println(valor);
        return "OK 200";
    }
}
