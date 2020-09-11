package com.boot.controller;

import com.boot.model.User;
import com.boot.model.dto.CaptchaResponseDto;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    private final UserService users;
    private final RestTemplate rest;
    @Value("${recaptcha.secret}")
    private String secret;

    public RegistrationController(final UserService users, final RestTemplate rest) {
        this.users = users;
        this.rest = rest;
    }

    @GetMapping
    public String link() {
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          @RequestParam("g-recaptcha-response") String captchaResponse,
                          Model model) {
        Map<String, String> map = new HashMap<>();
        final String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        final CaptchaResponseDto response = this.rest.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        final boolean isResponse = Objects.isNull(response) || !response.isSuccess();
        if (isResponse) {
            map.put("captchaError", "Заполни каптчу");
            map.put("messageType", "danger");
        }
        model.addAttribute("user", user);
        if (user.getPassword() != null && !user.getPassword2().equals(user.getPassword())) {
            map.put("password2Error", "Пароли не совпадают.");
            model.addAttribute("map", map);
            return "registration";
        }
        if (bindingResult.hasErrors() || isResponse) {
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
