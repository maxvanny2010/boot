package com.boot.controller;

import com.boot.model.User;
import com.boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * RegistrationController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService users;

    public RegistrationController(final UserService users) {
        this.users = users;
    }

    @GetMapping
    public String link() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        if (!this.users.addUser(user)) {
            model.addAttribute("message", "login is busy");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/active/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = this.users.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Активационный код принят");
        } else {
            model.addAttribute("message", "Активационный код не найден");
        }
        return "login";
    }
}
