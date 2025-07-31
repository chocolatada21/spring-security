package com.chocolatada.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/notSecured")
    public String notSecured() {
        return "notSecured";
    }
    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
