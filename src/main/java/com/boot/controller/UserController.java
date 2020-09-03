package com.boot.controller;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.repos.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * UserController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/3/2020
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserRepository users;

    public UserController(final UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", this.users.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable final User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSaveEditForm(
            @RequestParam("username") final String username,
            @RequestParam("userId") final User user,
            @RequestParam Map<String, String> form,
            Model model) {
        user.setUsername(username);
        final Set<String> roles = Arrays
                .stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        form.keySet()
                .stream()
                .filter(roles::contains)
                .forEach(key -> user.getRoles().add(Role.valueOf(key)));
        this.users.save(user);
        return "redirect:/user";
    }
}
