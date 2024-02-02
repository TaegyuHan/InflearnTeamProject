package com.example.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthController {
    @GetMapping("/loginForm")
    public String home() {
        return "loginForm.html";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "privatePage.html";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage.html";
    }

    @GetMapping("/logOut")
    public String logOut() {
        return "logOut.html";
    }
}
