package com.example.itprom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    private String main() {
        return "index";
    }

    @GetMapping("/employee")
    private String employee() {
        return "employee";
    }

    @GetMapping("/profession")
    private String profession() {
        return "profession";
    }

    @GetMapping("/department")
    private String department() {
        return "department";
    }
}
