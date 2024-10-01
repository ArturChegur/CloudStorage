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

    @GetMapping("/auth")
    public String getAuthPage(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserModel userModel) {
        try {
            authService.register(userModel);
            return "redirect:/auth?success";
        } catch (Exception e) {
            return "redirect:/auth?error=user_exists";
        }
    }
}
