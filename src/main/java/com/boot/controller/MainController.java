package com.boot.controller;

import com.boot.model.Message;
import com.boot.model.User;
import com.boot.repos.MessageRepository;
import com.boot.util.Util;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * GreetingController.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 9/2/2020
 */
@Controller
public class MainController {
    private final MessageRepository repo;

    public MainController(final MessageRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model,
                       @RequestParam(name = "filter", required = false, defaultValue = "") String filter) {
        Iterable<Message> messages;
        if (!Objects.isNull(filter) && !filter.isEmpty()) {
            messages = this.repo.findByTag(filter);
        } else {
            messages = this.repo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        message.setAuthor(user);
        if (bindingResult.hasErrors()) {
            final Map<String, String> map = ControllerUtils.getErrors(bindingResult);
            model.addAttribute("map", map);
            model.addAttribute("message", message);
        } else {
            if (Objects.nonNull(file) && !file.isEmpty()) {
                final String resultFileName = Util.getPhoto(file);
                message.setFilename(resultFileName);
            }
            this.repo.save(message);

        }
        final Iterable<Message> messages = this.repo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
