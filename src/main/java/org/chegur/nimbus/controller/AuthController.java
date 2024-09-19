package org.chegur.nimbus.controller;

import lombok.RequiredArgsConstructor;
import org.chegur.nimbus.model.UserModel;
import org.chegur.nimbus.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel userModel, Model model) {
        try {
            authService.register(userModel);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "register";
        }
    }
}
