package org.chegur.nimbus.controller;

import lombok.RequiredArgsConstructor;
import org.chegur.nimbus.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth")
    public String getAuthPage() {
        return "auth";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        try {
            authService.register(email, username, password);
            return "redirect:/auth?success";
        } catch (Exception e) {
            return "redirect:/auth?error=user_exists";
        }

        //todo maybe throw new exception instead of redirecting
    }
}
