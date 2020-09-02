package com.boot.controller;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Objects;

/**
 * RegistrationController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Controller()
@RequestMapping("/registration")
public class RegistrationController {
    private final UserRepository users;

    public RegistrationController(final UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public String link() {
        return "registration";
    }

    @PostMapping
    public String enter(
            User users, Model model
         /*   @RequestParam(name = "username") String name,
            @RequestParam(name = "password") String password,*/
    ) {
        final User usr = this.users.findByUsername(users.getUsername());
        if (Objects.isNull(usr)) {
            final User user = new User();
            user.setUsername(users.getUsername());
            user.setPassword(users.getPassword());
            user.setEnabled(true);
            user.setRoles(Collections.singleton(Role.USER));

            final String path = "redirect:/login";
            try {
                this.users.save(user);
            } catch (Exception e) {
                return path;
            }
            return path;
        }
        model.addAttribute("message", "login is busy");
        return "registration";

    }

}
