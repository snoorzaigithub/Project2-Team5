package com.example.project2.demo.controller;

import com.example.project2.demo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showCustomerLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCustomer(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        if (loginService.isValidCustomer(username, password)) {
            model.addAttribute("username", username);
            return "home";
        }

        model.addAttribute("error", "Invalid customer username or password.");
        return "login";
    }

    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        if (loginService.isValidAdmin(username, password)) {
            model.addAttribute("username", username);
            return "admin-dashboard";
        }

        model.addAttribute("error", "Invalid admin username or password.");
        return "admin-login";
    }
}