package com.boot.controller;

import com.boot.model.Role;
import com.boot.model.User;
import com.boot.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

/**
 * UserController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/3/2020
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService users;

    public UserController(final UserService users) {
        this.users = users;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", this.users.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable final User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSaveEditForm(
            @RequestParam("username") final String username,
            @RequestParam("userId") final User user,
            @RequestParam Map<String, String> form,
            Model model) {
        this.users.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email) {
        this.users.updateUser(user, password, email);
        return "redirect:/user/profile";
    }

    @GetMapping("subscribe/{user}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user) {
        this.users.subscribe(currentUser, user);
        return String.format("redirect:/user-messages/%d", user.getId());
    }

    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user) {
        this.users.unsubscribe(currentUser, user);
        return String.format("redirect:/user-messages/%d", user.getId());
    }

    @GetMapping("{type}/{user}/list")
    public String userList(Model model,
                           @PathVariable User user,
                           @PathVariable String type) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);
        if (Objects.equals("subscriptions", type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }
        return "subscriptions";
    }
}
