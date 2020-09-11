package com.boot.controller;

import com.boot.model.User;
import com.boot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        Map<String, String> map = new HashMap<>();
        model.addAttribute("user", user);
        if (user.getPassword() != null && !user.getPassword2().equals(user.getPassword())) {
            map.put("password2Error", "Пароли не совпадают.");
            model.addAttribute("map", map);
            return "registration";
        }
        if (bindingResult.hasErrors()) {
            final Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            map.putAll(errors);
            model.addAttribute("map", map);
            return "registration";
        }
        if (!this.users.addUser(user)) {
            map.put("usernameError", "Логин занят");
            model.addAttribute("map", map);
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/active/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = this.users.activateUser(code);
        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Активационный код принят");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Активационный код не найден");
        }
        return "login";
    }
}
